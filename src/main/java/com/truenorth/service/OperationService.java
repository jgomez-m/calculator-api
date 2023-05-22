package com.truenorth.service;

import com.truenorth.entity.Operation;
import com.truenorth.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationService {
    private final OperationRepository operationRepository;

    @Autowired
    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public Operation getOperationById(Long id) {
        return operationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Operation not found with id: " + id));
    }

    public Operation createOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    public Operation updateOperation(Long id, Operation operation) {
        Operation existingOperation = getOperationById(id);
        existingOperation.setType(operation.getType());
        existingOperation.setCost(operation.getCost());
        return operationRepository.save(existingOperation);
    }

    public void deleteOperation(Long id) {
        operationRepository.deleteById(id);
    }

    public List<Operation> getOperations() {
        return (List<Operation>) operationRepository.findAll();
    }
}
