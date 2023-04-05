package cs5324.demo.service;

import cs5324.demo.model.Assignment;

import java.util.Optional;

public interface AssignmentService {
    public Optional<Assignment> getAssignmentById(Long id);
    public Iterable<Assignment> getAssignments();
    public Assignment addAssignment(Assignment assignment);
}
