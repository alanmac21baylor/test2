package cs5324.demo.service;

import cs5324.demo.model.Grade;

import java.util.Optional;

public interface GradeService {
    public Grade addGrade(Grade grade);
    public Optional<Grade> getGradeById(Long id);
    public Iterable<Grade> getGrades();
    public Grade updateGrade(Grade grade);
}
