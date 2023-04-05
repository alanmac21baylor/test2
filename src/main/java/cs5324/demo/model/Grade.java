package cs5324.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(name ="student_assignment_grade_unique", columnNames = {"assignment_id" , "student_id"})})
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(
            name = "assignment_id",
            referencedColumnName = "id"
    )
    private Assignment assignment;

    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id"

    )
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "id"
    )
    private Course course;

    @NotNull
    private Float grade;

    public Long getId() {
        return id;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grade grade = (Grade) o;

        return Objects.equals(id, grade.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
