package ru.hogwarts.school5.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school5.model.Student;
import ru.hogwarts.school5.service.AvatarService;
import ru.hogwarts.school5.service.StudentService;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("students")
public class StudentController {

    private final StudentService studentService;

    private final AvatarService avatarService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return studentService.create(student);
    }

    @GetMapping("{id}")
    public Student read(@PathVariable Long id) {
        return studentService.read(id);
    }

    @PutMapping("{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        return studentService.update(id, student);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }

    @GetMapping
    public List<Student> getAllByAge(@RequestParam int age) {
        return studentService.getAllByAge(age);
    }

    @GetMapping("findByAgeBetween")
    public List<Student> findByAgeBetween(int from, int to) {
        return studentService.findByAgeBetween(from, to);
    }

    @PostMapping(value = "/{studentId}/avatar", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(
            @PathVariable Long studentId,
            @RequestParam MultipartFile avatar
    ) throws IOException {
        avatarService.uploadAvatar(studentId, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping("getAllStudentsNameStartsWithA")
    public List<String> getAllStudentsNameStartsWithA() {
        return studentService.getAllStudentsNameStartsWithA();
    }

    @GetMapping("getAverageAgeStudentWithStream")
    public Double getAverageAgeStudentWithStream() {
        return studentService.getAverageAgeStudentWithStream();
    }

    @GetMapping("print-parallel")
    public void printParallel() {
        studentService.printParallel();
    }

    @GetMapping("print-synchronized")
    public void printSynchronized() {
        studentService.printSynchronized();
    }

}
