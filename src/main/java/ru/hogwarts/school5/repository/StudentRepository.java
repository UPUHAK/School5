package ru.hogwarts.school5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school5.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
