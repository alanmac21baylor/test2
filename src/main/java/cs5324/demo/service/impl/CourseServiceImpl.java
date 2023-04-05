package cs5324.demo.service.impl;

import cs5324.demo.data.CourseRepository;
import cs5324.demo.model.Assignment;
import cs5324.demo.model.Course;
import cs5324.demo.model.Grade;
import cs5324.demo.model.Student;
import cs5324.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Iterable<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course addAssignment(Course course, Assignment assignment) {
        if (false == course.addAssignment(assignment)) {
            return null;
        }

        return courseRepository.saveAndFlush(course);
    }

    public Course addStudent(Course course, Student student) {
        if (false == course.addStudent(student)) {
            return null;
        }

        return courseRepository.saveAndFlush(course);
    }

    public Course addGrade(Course course, Grade grade) {
        if (false == course.addGrade(grade)) {
            return null;
        }

        return courseRepository.saveAndFlush(course);
    }

    @Override
    public Course updateCourse(Course course) {
        return courseRepository.saveAndFlush(course);
    }

}
