package com.example.task.Services;


import com.example.task.dto.UserDto;
import com.example.task.model.User;
import com.example.task.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user1, user2;

    @Before
    public void setUp() {
        user1 = new User();
        user1.setId(1L);
        user1.setName("User1");
        user1.setTerminated(false);

        user2 = new User();
        user2.setId(2L);
        user2.setName("User2");
        user2.setTerminated(true);
    }

    @Test
    public void testGetAllUsersWhoHaveBorrowedBooks() {
        when(userRepository.findUsersWithBorrowings()).thenReturn(Arrays.asList(user1, user2));

        final List<UserDto> result = userService.getAllUsersWhoHaveBorrowedBooks();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findUsersWithBorrowings();
        assertEquals("User1", result.get(0).getName());
        assertFalse(result.get(0).isTerminated());
        assertTrue(result.get(1).isTerminated());
    }

    @Test
    public void testGetNonTerminatedUsersWithNoBorrowings() {
        when(userRepository.findNonTerminatedUsersWithNoBorrowings()).thenReturn(Arrays.asList(user1));

        final List<User> result = userService.getNonTerminatedUsersWithNoBorrowings();

        assertEquals(1, result.size());
        verify(userRepository, times(1)).findNonTerminatedUsersWithNoBorrowings();
        assertFalse(result.get(0).isTerminated());
    }

    @Test
    public void testGetUsersByBorrowDate() {
        final Date borrowDate = new Date();
        when(userRepository.findUsersByBorrowDate(borrowDate)).thenReturn(Arrays.asList(user1, user2));

        final List<User> result = userService.getUsersByBorrowDate(borrowDate);

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findUsersByBorrowDate(borrowDate);
    }
}
