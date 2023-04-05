package cs5324.demo.service;

import cs5324.demo.model.Assignment;
import cs5324.demo.model.AssignmentType;
import cs5324.demo.model.Course;
import cs5324.demo.model.Student;
import cs5324.demo.model.Grade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
@SpringBootTest
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private AssignmentService assignmentService;

    private Student student;
    private Course course;
    private Assignment assignment;
    private Grade grade;

    @BeforeEach
    void setup() {
        student = new Student();
        student.setName("Bob Smith");

        assignment = new Assignment();
        assignment.setName("Demo Project");
        assignment.setAssignmentType(AssignmentType.FINAL);

        course = new Course();
        course.setName("CS5324");

        grade = new Grade();
        grade.setGrade(65.3F);
    }

    @Test
    @DisplayName("Create Course")
    @Order(1)
    void createCourse() {
        assertNull(course.getId());
        Course saved = courseService.addCourse(course);
        assertNotNull(saved.getId());
    }

    @Test
    @DisplayName("Get course by Id")
    @Order(2)
    void getCourseById() {
        assertNull(course.getId());
        Course saved = courseService.addCourse(course);
        assertNotNull(saved.getId());

        Optional<Course> tmp = courseService.getCourseById(saved.getId());
        assertTrue(tmp.isPresent());
        assertTrue(saved.equals(tmp.get()));
    }

    @Test
    @DisplayName("Add Student to Course")
    @Order(3)
    void addStudent() {
        assertNull(course.getId());
        Course savedCourse = courseService.addCourse(course);
        assertNotNull(savedCourse.getId());

        assertNull(student.getId());
        Student savedStudent = studentService.addStudent(student);
        assertNotNull(savedStudent.getId());

        Course updatedCourse = courseService.addStudent(savedCourse, savedStudent);
        assertNotNull(updatedCourse);
        assertNotNull(updatedCourse.getStudents());
        assertTrue(updatedCourse.getStudents().contains(savedStudent));
    }

    @Test
    @DisplayName("Add Assignment to Course")
    @Order(4)
    void addAssignment() {
        assertNull(course.getId());
        Course savedCourse = courseService.addCourse(course);
        assertNotNull(savedCourse.getId());

        assertNull(assignment.getId());
        Assignment savedAssignment = assignmentService.addAssignment(assignment);
        assertNotNull(savedAssignment.getId());

        Course updatedCourse = courseService.addAssignment(savedCourse, savedAssignment);
        assertNotNull(updatedCourse);
        assertNotNull(updatedCourse.getStudents());
        assertTrue(updatedCourse.getAssignments().contains(savedAssignment));
    }

//    @Test
//    @DisplayName("Add Grade to Course")
}
