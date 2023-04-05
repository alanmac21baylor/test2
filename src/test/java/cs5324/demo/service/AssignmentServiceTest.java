package cs5324.demo.service;

import cs5324.demo.model.Assignment;
import cs5324.demo.model.AssignmentType;
import cs5324.demo.model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AssignmentServiceTest {

    @Autowired
    private AssignmentService assignmentService;
    private Course course;
    private Assignment assignment;

    @BeforeEach
    void setup() {
//        course = new Course();
//        course.setName("CS5324");
//        course = em.persist(course);

        assignment = new Assignment();
        assignment.setName("Demo Project");
        assignment.setAssignmentType(AssignmentType.QUIZ);
    }

    @Test
    @DisplayName("Create Assignment")
    void createAssignment() {
        assertNull(assignment.getId());
        Assignment saved = assignmentService.addAssignment(assignment);
        assertNotNull(saved.getId());
    }

    @Test
    @DisplayName("Get assignment by Id")
    void getAssignmentById() {
        assertNull(assignment.getId());
        Assignment saved = assignmentService.addAssignment(assignment);
        assertNotNull(saved.getId());

        Optional<Assignment> tmp = assignmentService.getAssignmentById(saved.getId());
        assertTrue(tmp.isPresent());
        assertTrue(saved.equals(tmp.get()));
    }
}
