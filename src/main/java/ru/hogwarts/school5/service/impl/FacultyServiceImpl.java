package ru.hogwarts.school5.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school5.model.Faculty;
import ru.hogwarts.school5.repository.FacultyRepository;
import ru.hogwarts.school5.service.FacultyService;

import java.util.Comparator;
import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    private static final Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty create(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty read(Long id) {
        logger.info("Was invoked method for read faculty");
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Faculty update(Long id, Faculty faculty) {
        logger.info("Was invoked method for update faculty");
        Faculty faculty1 = facultyRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        faculty1.setName(faculty.getName());
        faculty1.setColor(faculty.getColor());
        return facultyRepository.save(faculty1);
    }

    @Override
    public void delete(Long id) {
        logger.info("Was invoked method for delete faculty");
        facultyRepository.deleteById(id);
    }

    @Override
    public List<Faculty> getAllByColor(String color) {
        logger.info("Was invoked method for get all faculties by color");
        return facultyRepository.findAll()
                .stream()
                .filter(it -> it.getColor().equals(color))
                .toList();
    }

    @Override
    public List<Faculty> findByNameIgnoreCaseOrColorIgnoreCase(String name, String color) {
        logger.info("Was invoked method for find faculty by name ignore case or color ignore case");
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    @Override
    public String getLongestFacultyName() {
        return facultyRepository.findAll()
                .stream()
                .map(Faculty::getName)
                .max(Comparator.comparing(String::length))
                .orElse("");
    }
}
