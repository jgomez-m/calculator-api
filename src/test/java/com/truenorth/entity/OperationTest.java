package com.truenorth.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OperationTest {

    @Test
    public void testOperationEntity() {
        Operation operation = new Operation();
        operation.setId(1L);
        operation.setType(Operation.Type.ADDITION);
        operation.setCost(5);

        Assertions.assertEquals(1L, operation.getId());
        Assertions.assertEquals(Operation.Type.ADDITION, operation.getType());
        Assertions.assertEquals(5, operation.getCost());
    }
}

