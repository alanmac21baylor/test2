package cs5324.demo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
public class StudentTest {

    @Autowired
    private TestEntityManager em;
    private Student student;

    @BeforeEach
    void setup() {
        student = new Student();
        student.setName("Bob Smith");
    }

    @Test
    @DisplayName("Persist Student")
    void persistStudent() {
        assertNull(student.getId());
        Student savedStudent = em.persistAndFlush(student);

        assertNotNull(savedStudent.getId());
    }
}
