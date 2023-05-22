package com.truenorth.service;

import com.truenorth.entity.User;
import com.truenorth.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId(1L);
        user.setUsername("john@example.com");
        user.setPassword("password123");
        user.setStatus(User.Status.ACTIVE);
        user.setBalance(1000.0d);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getById(1L);

        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("john@example.com", result.getUsername());
        Assertions.assertEquals("password123", result.getPassword());
        Assertions.assertEquals(User.Status.ACTIVE, result.getStatus());
        Assertions.assertEquals(1000.0d, result.getBalance());

        verify(userRepository, times(1)).findById(1L);
    }
}
