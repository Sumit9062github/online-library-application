package com.library.service;

import com.library.entity.Book;

import java.util.List;

public interface BookService {
    Book addBook(Book book);
    List<Book> getAllBooks();
    List<Book> getBooksByCategory(String category);
    List<Book> getBooksByStatus(String status);
    List<Book> getBooksByName(String name);
    List<Book> getBooksByAuthor(String author);
    Book updateBookStatus(Long bookId, String status);
}
