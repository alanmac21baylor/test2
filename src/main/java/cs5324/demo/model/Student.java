package cs5324.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.coyote.http11.filters.VoidOutputFilter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;

    @NotNull
    private String name;

    @ManyToMany(
            mappedBy = "students"
    )
    private Set<Course> courses = new HashSet<>();

    @OneToMany(
            mappedBy = "student"
    )
    private Set<Grade> grades = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public Boolean addCourse(Course course) {
        return this.courses.add(course);
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public Boolean addGrade(Grade grade) {
        return this.grades.add(grade);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
