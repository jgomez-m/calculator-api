package com.truenorth.controller;

import com.truenorth.entity.Operation;
import com.truenorth.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationController {
    private final OperationService operationService;

    @Autowired
    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping("/{id}")
    public Operation getOperationById(@PathVariable Long id) {
        return operationService.getOperationById(id);
    }

    @GetMapping
    public List<Operation> getAllOperations() {
        return operationService.getOperations();
    }

    @PostMapping
    public Operation createOperation(@RequestBody Operation operation) {
        return operationService.createOperation(operation);
    }

    @PutMapping("/{id}")
    public Operation updateOperation(@PathVariable Long id, @RequestBody Operation operation) {
        return operationService.updateOperation(id, operation);
    }

    @DeleteMapping("/{id}")
    public void deleteOperation(@PathVariable Long id) {
        operationService.deleteOperation(id);
    }
}

