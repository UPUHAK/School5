package ru.hogwarts.school5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school5.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByAgeBetween(int from, int to);
}
