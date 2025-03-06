package ru.hogwarts.school5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school5.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
