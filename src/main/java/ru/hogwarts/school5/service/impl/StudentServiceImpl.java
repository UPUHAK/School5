package ru.hogwarts.school5.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school5.model.Student;
import ru.hogwarts.school5.repository.StudentRepository;
import ru.hogwarts.school5.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student create(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    @Override
    public Student read(Long id) {
        logger.info("Was invoked method for read student");
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student update(Long id, Student student) {
        logger.info("Was invoked method for update student");
        Student student1 = studentRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        student1.setName(student.getName());
        student1.setAge(student.getAge());

        return studentRepository.save(student1);
    }

    @Override
    public void delete(Long id) {
        logger.info("Was invoked method for delete student");
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAllByAge(int age) {
        logger.info("Was invoked method for get all students by age");
        return studentRepository.findAll()
                .stream()
                .filter(it -> it.getAge() == age)
                .toList();
    }

    @Override
    public List<Student> findByAgeBetween(int from, int to) {
        logger.info("Was invoked method for find all students by age between");
        return studentRepository.findByAgeBetween(from, to);
    }

    @Override
    public int getStudentsCount() {
        logger.info("Was invoked method for get students count");
        return studentRepository.getStudentsCount();
    }

    @Override
    public int getAverageAgeStudent() {
        logger.info("Was invoked method for get average age of students");
        return studentRepository.getAverageAgeStudent();
    }

    @Override
    public List<Student> getLastFiveStudents() {
        logger.info("Was invoked method for get last five students");
        return studentRepository.getLastFiveStudents();
    }

    @Override
    public List<String> getAllStudentsNameStartsWithA() {
        return studentRepository.findAll()
                .stream()
                .map(Student::getName)
                .filter(it -> it.startsWith("A"))
                .map(String::toUpperCase)
                .sorted()
                .toList();
    }

    @Override
    public Double getAverageAgeStudentWithStream() {
        return studentRepository.findAll()
                .stream()
                .collect(Collectors.averagingInt(Student::getAge));
    }

    @Override
    public void printParallel() {

        List<Student> students = studentRepository.findAll();

        System.out.println(students.get(0).getName());
        System.out.println(students.get(1).getName());

        new Thread(() -> {
            System.out.println(students.get(2).getName());
            System.out.println(students.get(3).getName());
        }).start();

        new Thread(() -> {
            System.out.println(students.get(4).getName());
            System.out.println(students.get(5).getName());
        }).start();
    }

    @Override
    public void printSynchronized() {

        List<Student> students = studentRepository.findAll();

        printStudentName(students.get(0));
        printStudentName(students.get(1));

        new Thread(() -> {
            printStudentName(students.get(2));
            printStudentName(students.get(3));
        }).start();

        new Thread(() -> {
            printStudentName(students.get(4));
            printStudentName(students.get(5));
        }).start();
    }

    private synchronized void printStudentName(Student student) {
        System.out.println(student.getName());
    }
}
