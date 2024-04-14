package com.example.task.repository;


import com.example.task.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE EXISTS (SELECT b FROM Borrowing b WHERE b.user = u)")
    List<User> findUsersWithBorrowings();

    @Query(" SELECT u FROM User u WHERE u.terminated = false AND u.borrowings IS EMPTY ")
    List<User> findNonTerminatedUsersWithNoBorrowings();

    @Query("SELECT DISTINCT u FROM User u JOIN u.borrowings b WHERE b.borrowDate = :borrowDate")
    List<User> findUsersByBorrowDate(Date borrowDate);
}
