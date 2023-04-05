package cs5324.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "COURSE_STUDENTS",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    @JsonIgnoreProperties({"courses"})
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.MERGE)
    private Set<Assignment> assignments = new HashSet<>();

    @OneToMany(mappedBy = "course")
    private Set<Grade> grades = new HashSet<>();

    public Boolean addStudent(Student student) {
        return this.students.add(student);
    }

    public Boolean addGrade(Grade grade) {
        // student enrolled in course
        if (!this.students.contains(grade.getStudent())) {
            log.atError().log("Student not enrolled in course");
            return false;
        }

        // assignment is part of this course
        if (!this.assignments.contains(grade.getAssignment())) {
            log.atError().log("Assignment not in course");
            return false;
        }

        return grades.add(grade);
    }

    public Boolean addAssignment(Assignment assignment) {
        return this.assignments.add(assignment);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Set<Assignment> getAssignments() {
        return assignments;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
