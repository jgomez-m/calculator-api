package com.truenorth.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordTest {

    @Test
    public void testRecordEntity() {
        Operation operation = new Operation();
        operation.setId(1L);
        operation.setType(Operation.Type.ADDITION);
        operation.setCost(5);

        User user = new User();
        user.setId(1L);
        user.setUsername("john@example.com");
        user.setPassword("password123");
        user.setStatus(User.Status.ACTIVE);

        Record record = new Record();
        record.setId(1L);
        record.setOperation(operation);
        record.setUser(user);
        record.setAmount(10);
        record.setUserBalance(100);
        record.setOperationResponse("Result: 20");

        Assertions.assertEquals(1L, record.getId());
        Assertions.assertEquals(operation, record.getOperation());
        Assertions.assertEquals(user, record.getUser());
        Assertions.assertEquals(10, record.getAmount());
        Assertions.assertEquals(100, record.getUserBalance());
        Assertions.assertEquals("Result: 20", record.getOperationResponse());
    }
}

