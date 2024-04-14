package com.example.task.Services;


import com.example.task.model.Book;
import com.example.task.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testGetAvailableBooks() {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Book1");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Book2");

        when(bookRepository.findAvailableBooks()).thenReturn(Arrays.asList(book1, book2));

        final List<Book> availableBooks = bookService.getAvailableBooks();

        assertNotNull(availableBooks);
        assertEquals(2, availableBooks.size());
        verify(bookRepository, times(1)).findAvailableBooks();
    }
}
