package cs5324.demo.service;

import cs5324.demo.model.Assignment;
import cs5324.demo.model.Course;
import cs5324.demo.model.Grade;
import cs5324.demo.model.Student;

import java.util.Optional;

public interface CourseService {
    public Optional<Course> getCourseById(Long id);
    public Iterable<Course> findAll();
    public Course addCourse(Course course);
    public Course addAssignment(Course course, Assignment assignment);
    public Course addStudent(Course course, Student student);
    public Course addGrade(Course course, Grade grade);
    public Course updateCourse(Course course);
}
