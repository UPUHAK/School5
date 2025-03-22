package ru.hogwarts.school5.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school5.model.Avatar;
import ru.hogwarts.school5.model.Student;
import ru.hogwarts.school5.repository.AvatarRepository;
import ru.hogwarts.school5.repository.StudentRepository;
import ru.hogwarts.school5.service.AvatarService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarServiceImpl implements AvatarService {

    private static final Logger logger = LoggerFactory.getLogger(AvatarServiceImpl.class);

    private final AvatarRepository avatarRepository;

    private final StudentRepository studentRepository;

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    public AvatarServiceImpl(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        logger.info("Was invoked method for upload avatar");

        try {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> {
                        logger.error("There is not student with id = {}", studentId);
                        return new EntityNotFoundException("Student not found");
                    });

            Path avatarPath = saveToDisk(studentId, avatarFile);
            saveToDatabase(student, avatarPath, avatarFile);
        } catch (IOException e) {
            logger.error("Error during avatar upload for studentId={}", studentId, e);
            throw e;
        }
    }

    @Override
    public Avatar findAvatarById(Long avatarId) {
        logger.info("Was invoked method for find avatar by id");
        return avatarRepository.findById(avatarId).orElseThrow();
    }

    @Override
    public List<Avatar> getPaginatedAvatars(int pageNumber, int pageSize) {
        logger.info("Was invoked method for get paginated avatars");
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Avatar> avatarPage = avatarRepository.findAll(pageable);
        return avatarPage.getContent();
    }

    private Path saveToDisk(Long studentId, MultipartFile avatarFile) throws IOException {
        logger.info("Was invoked method for save avatar to disk");
        Path filePath = Path.of(avatarsDir, "avatar" + studentId + "." +
                getExtensions(avatarFile.getOriginalFilename()));

        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        int BUFFER_SIZE = 1024;
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, BUFFER_SIZE);
                BufferedOutputStream bos = new BufferedOutputStream(os, BUFFER_SIZE)
        ) {
            bis.transferTo(bos);
        }

        return filePath;
    }

    private void saveToDatabase(Student student, Path avatarPath, MultipartFile avatarFile) throws IOException {
        logger.info("Was invoked method for save avatar to database");
        Avatar avatar = findAvatar(student.getId());

        avatar.setStudent(student);
        avatar.setFilePath(avatarPath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());

        avatarRepository.save(avatar);
    }

    private Avatar findAvatar(Long studentId) {
        logger.info("Was invoked method for find avatar");
        return avatarRepository.findByStudent_id(studentId).orElse(
                new Avatar()
        );
    }

    private String getExtensions(String fileName) {
        logger.info("Was invoked method for get extensions");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
