package com.truenorth.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testUserEntity() {
        User user = new User(1L, "john@example.com", "password123", User.Status.ACTIVE);

        Assertions.assertEquals(1L, user.getId());
        Assertions.assertEquals("john@example.com", user.getUsername());
        Assertions.assertEquals("password123", user.getPassword());
        Assertions.assertEquals(User.Status.ACTIVE, user.getStatus());
    }
}
