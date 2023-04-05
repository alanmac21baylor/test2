package cs5324.demo.service;

import cs5324.demo.model.*;
import cs5324.demo.model.Grade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GradeServiceTest {

    @Autowired
    private GradeService gradeService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private AssignmentService assignmentService;
    @Autowired
    private StudentService studentService;

    private Student student;
    private Assignment assignment;
    private Course course;
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
    @DisplayName("Create Grade")
    @Transactional
    void createGrade() {
        // create assignment
        assertNull(assignment.getId());
        Assignment savedAssignment = assignmentService.addAssignment(assignment);
        assertNotNull(savedAssignment.getId());

        // create student
        Student savedStudent = studentService.addStudent(student);
        Optional<Student> tmp = studentService.getStudentById(savedStudent.getId());
        assertTrue(tmp.isPresent());

        // create course, adding in both assignment and student
        assertNull(course.getId());
        Course savedCourse = courseService.addCourse(course);
        savedCourse.addAssignment(savedAssignment);
        savedCourse.addStudent(savedStudent);
        assertNotNull(savedCourse.getId());

        // update grade
        grade.setStudent(savedStudent);
        grade.setAssignment(savedAssignment);
        grade.setCourse(savedCourse);

        assertTrue(savedStudent.addGrade(grade));
        studentService.updateStudent(savedStudent);

        // to insert a grade, both assignment and student have to already be in the course
        assertTrue(savedCourse.addGrade(grade));
        courseService.updateCourse(savedCourse);

        Grade savedGrade = gradeService.addGrade(grade);
        assertNotNull(savedGrade);
        assertEquals(savedGrade.getGrade(), grade.getGrade());
        assertEquals(savedGrade.getAssignment(), savedAssignment);
        assertEquals(savedGrade.getStudent(), savedStudent);
        assertEquals(savedGrade.getCourse(), savedCourse);
    }
}
