package ru.hogwarts.school5.service;

import ru.hogwarts.school5.model.Student;

import java.util.List;

public interface StudentService {

    Student create(Student student);

    Student read(Long id);

    Student update(Long id, Student student);

    void delete(Long id);

    List<Student> getAllByAge(int age);
}
