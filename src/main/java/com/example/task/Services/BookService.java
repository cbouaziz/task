package com.example.task.Services;

import com.example.task.model.Book;
import com.example.task.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findAvailableBooks();
    }
}
