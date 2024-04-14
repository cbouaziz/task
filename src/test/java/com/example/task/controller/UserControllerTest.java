package com.example.task.controller;

import com.example.task.Services.UserService;
import com.example.task.dto.UserDto;
import com.example.task.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user1, user2;
    private UserDto userDto1, userDto2;

    @BeforeEach
    public void setUp() {
        user1 = new User();
        user2 = new User();
        userDto1 = new UserDto(1L, "User One", false);
        userDto2 = new UserDto(2L, "User Two", false);
    }

    @Test
    public void testGetAllUsersWhoHaveBorrowedBooks() {
        when(userService.getAllUsersWhoHaveBorrowedBooks()).thenReturn(Arrays.asList(userDto1, userDto2));

       final List<UserDto> result = userController.getAllUsersWhoHaveBorrowedBooks();

        verify(userService, times(1)).getAllUsersWhoHaveBorrowedBooks();
        assertEquals(2, result.size());
        assertEquals("User One", result.get(0).getName());
    }

    @Test
    public void testGetNonTerminatedUsersWithNoBorrowings() {
        when(userService.getNonTerminatedUsersWithNoBorrowings()).thenReturn(Arrays.asList(user1, user2));

        final List<User> result = userController.getNonTerminatedUsersWithNoBorrowings();

        verify(userService, times(1)).getNonTerminatedUsersWithNoBorrowings();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetUsersByBorrowDate() {
        final Date testDate = new Date();
        when(userService.getUsersByBorrowDate(testDate)).thenReturn(Arrays.asList(user1, user2));

        final List<User> result = userController.getUsersByBorrowDate(testDate);

        verify(userService, times(1)).getUsersByBorrowDate(testDate);
        assertEquals(2, result.size());
    }
}
