package ru.hogwarts.school5.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school5.model.Faculty;
import ru.hogwarts.school5.repository.FacultyRepository;
import ru.hogwarts.school5.service.FacultyService;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty create(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty read(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Faculty update(Long id, Faculty faculty) {
        Faculty faculty1 = facultyRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        faculty1.setName(faculty.getName());
        faculty1.setColor(faculty.getColor());
        return facultyRepository.save(faculty1);
    }

    @Override
    public void delete(Long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public List<Faculty> getAllByColor(String color) {
        return facultyRepository.findAll()
                .stream()
                .filter(it -> it.getColor().equals(color))
                .toList();
    }

    @Override
    public List<Faculty> findByNameIgnoreCaseOrColorIgnoreCase(String name, String color) {
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }
}
