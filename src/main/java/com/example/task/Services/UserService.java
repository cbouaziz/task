package com.example.task.Services;

import com.example.task.dto.UserDto;
import com.example.task.model.User;
import com.example.task.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsersWhoHaveBorrowedBooks() {
        final List<User> users = userRepository.findUsersWithBorrowings();
        return users.stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.isTerminated()))
                .collect(toList());
    }
    public List<User> getNonTerminatedUsersWithNoBorrowings() {
        return userRepository.findNonTerminatedUsersWithNoBorrowings();
    }

    public List<User> getUsersByBorrowDate(final Date borrowDate) {
        return userRepository.findUsersByBorrowDate(borrowDate);
    }
}
