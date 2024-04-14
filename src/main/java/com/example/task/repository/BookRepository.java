package com.example.task.repository;

import com.example.task.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.id NOT IN (SELECT br.book.id FROM Borrowing br WHERE br.returnDate IS NULL)")
    List<Book> findAvailableBooks();
}
