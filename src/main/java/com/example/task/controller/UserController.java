package com.example.task.controller;

import com.example.task.Services.UserService;
import com.example.task.dto.UserDto;
import com.example.task.model.User;
import com.example.task.repository.BookRepository;
import com.example.task.repository.BorrowingRepository;
import com.example.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/users")

public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowingRepository borrowingRepository;

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/active-borrowers")
    public List<UserDto> getAllUsersWhoHaveBorrowedBooks() {
        return userService.getAllUsersWhoHaveBorrowedBooks();
    }

    @GetMapping("/nonTerminated")
    public List<User> getNonTerminatedUsersWithNoBorrowings() {
        return userService.getNonTerminatedUsersWithNoBorrowings();
    }

    @GetMapping("/by-borrow-date")
    public List<User> getUsersByBorrowDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date borrowDate) {
        return userService.getUsersByBorrowDate(borrowDate);
    }
}
