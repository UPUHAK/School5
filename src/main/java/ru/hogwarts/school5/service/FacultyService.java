package ru.hogwarts.school5.service;

import ru.hogwarts.school5.model.Faculty;

import java.util.List;

public interface FacultyService {

    Faculty create(Faculty faculty);

    Faculty read(Long id);

    Faculty update(Long id, Faculty faculty);

    void delete(Long id);

    List<Faculty> getAllByColor(String color);

    List<Faculty> findByNameIgnoreCaseOrColorIgnoreCase(String name, String color);
}
