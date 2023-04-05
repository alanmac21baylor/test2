package cs5324.demo.service.impl;

import cs5324.demo.data.AssignmentRepository;
import cs5324.demo.model.Assignment;
import cs5324.demo.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public AssignmentServiceImpl(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public Optional<Assignment> getAssignmentById(Long id) {
        return assignmentRepository.findById(id);
    }

    @Override
    public Iterable<Assignment> getAssignments() {
        return assignmentRepository.findAll();
    }

    @Override
    public Assignment addAssignment(Assignment assignment) {
        return assignmentRepository.saveAndFlush(assignment);
    }
}
