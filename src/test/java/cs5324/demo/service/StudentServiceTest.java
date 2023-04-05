package cs5324.demo.service;

import cs5324.demo.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import jakarta.persistence.EntityManager;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;
    private Student student;

    @BeforeEach
    void setup() {
        student = new Student();
        student.setName("Jim Bob");
    }

    @Test
    @DisplayName("Create Student")
    void createStudent() {
        assertNull(student.getId());
        Student saved = studentService.addStudent(student);
        assertNotNull(saved.getId());
    }

    @Test
    @DisplayName("Get Student by ID")
    void getStudentById() {
        Student saved = studentService.addStudent(student);
        Optional<Student> tmp = studentService.getStudentById(saved.getId());
        assertTrue(tmp.isPresent());

        assertTrue(saved.equals(tmp.get()));
    }
}
