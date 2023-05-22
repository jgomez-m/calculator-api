package com.truenorth.service;

import com.truenorth.entity.Operation;
import com.truenorth.repository.OperationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class OperationServiceTest {

    @Mock
    private OperationRepository operationRepository;

    private OperationService operationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        operationService = new OperationService(operationRepository);
    }

    @Test
    public void testGetOperationById() {
        Operation operation = new Operation();
        operation.setId(1L);
        operation.setType(Operation.Type.ADDITION);
        operation.setCost(5);

        when(operationRepository.findById(1L)).thenReturn(Optional.of(operation));

        Operation result = operationService.getOperationById(1L);

        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals(Operation.Type.ADDITION, result.getType());
        Assertions.assertEquals(5, result.getCost());

        verify(operationRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllOperations() {
        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation(1L, Operation.Type.ADDITION, 5));
        operations.add(new Operation(2L, Operation.Type.SUBTRACTION, 5));

        when(operationRepository.findAll()).thenReturn(operations);

        List<Operation> result = operationService.getOperations();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(operations, result);

        verify(operationRepository, times(1)).findAll();
    }
}

