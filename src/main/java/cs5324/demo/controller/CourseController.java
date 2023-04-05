package cs5324.demo.controller;

import cs5324.demo.model.Assignment;
import cs5324.demo.model.Course;
import cs5324.demo.service.AssignmentService;
import cs5324.demo.service.CourseService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/course", produces = "application/json")
public class CourseController {

    private CourseService courseService;
    private AssignmentService assignmentService;

    public CourseController(CourseService courseService, @Lazy AssignmentService assignmentService) {
        this.courseService = courseService;
        this.assignmentService = assignmentService;
    }

    @GetMapping
    public Iterable<Course> getCourses() {
        return courseService.findAll();
    }

    @PostMapping
    public ResponseEntity<Course> addCourse(Course course) {
        try {
            return new ResponseEntity(courseService.addCourse(course), HttpStatus.CREATED);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "assignment")
    public ResponseEntity<Course> addAssignment(@RequestParam(name = "courseId") Long courseId, @RequestParam(name = "assignmentId") Long assignmentId) {
        Optional<Course> course = courseService.getCourseById(courseId);
        Optional<Assignment> assignment = assignmentService.getAssignmentById(assignmentId);

        if (course.isEmpty()) {
            return new ResponseEntity("Invalid course id: " + courseId, HttpStatus.BAD_REQUEST);
        }

        if (assignment.isEmpty()) {
            return new ResponseEntity("Invalid assignment id: " + assignmentId, HttpStatus.BAD_REQUEST);
        }

        try {
            return new ResponseEntity(courseService.addAssignment(course.get(), assignment.get()), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
