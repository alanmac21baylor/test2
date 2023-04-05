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
public class AssignmentTest {

    @Autowired
    private TestEntityManager em;
    private Course course;
    private Assignment assignment;


    @BeforeEach
    void setup() {
        course = new Course();
        course.setName("CS5324");
        course = em.persist(course);

        assignment = new Assignment();
        assignment.setName("Demo Project");
        assignment.setAssignmentType(AssignmentType.QUIZ);
    }

    @Test
    @DisplayName("Persist Assignment")
    void persistAssignment() {
        assertNull(assignment.getId());
        Assignment saved = em.persistAndFlush(assignment);
        assertNotNull(saved.getId());
    }
}
