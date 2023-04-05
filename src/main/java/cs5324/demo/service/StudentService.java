package cs5324.demo.service;

import cs5324.demo.model.Student;

import java.util.Optional;

public interface StudentService {

    public Optional<Student> getStudentById(Long id);
    public Iterable<Student> getStudents();
    public Student addStudent(Student student);
    public Student updateStudent(Student student);
}
