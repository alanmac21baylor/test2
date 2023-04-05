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
public class GradeTest {

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
        student = em.persistAndFlush(student);

        assignment = new Assignment();
        assignment.setName("Demo Project");
        assignment.setAssignmentType(AssignmentType.QUIZ);
        assignment = em.persistAndFlush(assignment);

        course = new Course();
        course.setName("CS5324");
        em.persistAndFlush(course);

        course.addAssignment(assignment);
        course.addStudent(student);
        course = em.persistAndFlush(course);

        grade = new Grade();
        grade.setGrade(65.3F);
        grade.setAssignment(assignment);
        grade.setCourse(course);
        grade.setStudent(student);
    }

    @Test
    @DisplayName("Persist Grade")
    void persistGrade() {
        assertNull(grade.getId());
        Grade saved = em.persistAndFlush(grade);
        assertNotNull(saved.getId());
    }
}
