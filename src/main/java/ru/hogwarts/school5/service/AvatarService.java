package ru.hogwarts.school5.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school5.model.Avatar;

import java.io.IOException;
import java.util.List;

public interface AvatarService {

    void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException;

    Avatar findAvatarById(Long avatarId);

    List<Avatar> getPaginatedAvatars(int pageNumber, int pageSize);
}
