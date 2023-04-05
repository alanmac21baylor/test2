package cs5324.demo.service.impl;

import cs5324.demo.data.GradeRepository;
import cs5324.demo.model.Grade;
import cs5324.demo.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;

    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public Grade addGrade(Grade grade) {
        return gradeRepository.saveAndFlush(grade);
    }

    @Override
    public Optional<Grade> getGradeById(Long id) {
        return gradeRepository.findById(id);
    }

    @Override
    public Iterable<Grade> getGrades() {
        return gradeRepository.findAll();
    }

    @Override
    public Grade updateGrade(Grade grade) {
        return gradeRepository.saveAndFlush(grade);
    }
}
