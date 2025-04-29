package com.library.controller;

import com.library.entity.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/category")
    public List<Book> getByCategory(@RequestParam String category) {
        return bookService.getBooksByCategory(category);
    }

    @GetMapping("/status")
    public List<Book> getByStatus(@RequestParam String status) {
        return bookService.getBooksByStatus(status);
    }

    @GetMapping("/name")
    public List<Book> getByName(@RequestParam String name) {
        return bookService.getBooksByName(name);
    }

    @GetMapping("/author")
    public List<Book> getByAuthor(@RequestParam String author) {
        return bookService.getBooksByAuthor(author);
    }

    @PutMapping("/status/{bookId}")
    public Book updateStatus(@PathVariable Long bookId, @RequestParam String status) {
        return bookService.updateBookStatus(bookId, status);
    }
}
