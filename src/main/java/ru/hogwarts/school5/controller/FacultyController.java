package ru.hogwarts.school5.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school5.model.Faculty;
import ru.hogwarts.school5.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("faculties")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty create(@RequestBody Faculty faculty) {
        return facultyService.create(faculty);
    }

    @GetMapping("{id}")
    public Faculty read(@PathVariable Long id) {
        return facultyService.read(id);
    }

    @PutMapping("{id}")
    public Faculty update(@PathVariable Long id, @RequestBody Faculty faculty) {
        return facultyService.update(id, faculty);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        facultyService.delete(id);
    }

    @GetMapping
    public List<Faculty> getAllByColor(String color) {
        return facultyService.getAllByColor(color);
    }

    @GetMapping("findByNameIgnoreCaseOrColorIgnoreCase")
    public List<Faculty> findByNameIgnoreCaseOrColorIgnoreCase(String query) {
        return facultyService.findByNameIgnoreCaseOrColorIgnoreCase(query, query);
    }

    @GetMapping("getLongestFacultyName")
    public String getLongestFacultyName() {
        return facultyService.getLongestFacultyName();
    }
}
