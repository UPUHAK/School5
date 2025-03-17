package ru.hogwarts.school5.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school5.model.Student;
import ru.hogwarts.school5.repository.StudentRepository;
import ru.hogwarts.school5.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student read(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student update(Long id, Student student) {
        Student student1 = studentRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        student1.setName(student.getName());
        student1.setAge(student.getAge());

        return studentRepository.save(student1);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAllByAge(int age) {
        return studentRepository.findAll()
                .stream()
                .filter(it -> it.getAge() == age)
                .toList();
    }

    @Override
    public List<Student> findByAgeBetween(int from, int to) {
        return studentRepository.findByAgeBetween(from, to);
    }

    @Override
    public int getStudentsCount() {
        return studentRepository.getStudentsCount();
    }

    @Override
    public int getAverageAgeStudent() {
        return studentRepository.getAverageAgeStudent();
    }

    @Override
    public List<Student> getLastFiveStudents() {
        return studentRepository.getLastFiveStudents();
    }
}
