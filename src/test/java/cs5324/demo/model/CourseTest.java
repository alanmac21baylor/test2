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
public class CourseTest {
    @Autowired
    private TestEntityManager em;
    private Course course;
    private Student student;
    private Assignment assignment;
    private Grade grade;

    @BeforeEach
    void setup() {
        student = new Student();
        student.setName("Bob Smith");

        student = em.persist(student);

        assignment = new Assignment();
        assignment.setName("Demo Project");
        assignment.setAssignmentType(AssignmentType.FINAL);
        assignment = em.persist(assignment);

        course = new Course();
        course.setName("CS5324");
    }

    @Test
    @DisplayName("Pesist course")
    void persistCourse() {
        assertNull(course.getId());
        Course savedCourse = em.persistAndFlush(course);
        assertNotNull(course.getId());
    }
}
