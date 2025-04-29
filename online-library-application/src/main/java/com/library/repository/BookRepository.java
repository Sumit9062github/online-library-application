package com.library.repository;

import com.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByCategory(String category);
    List<Book> findByStatus(String status);
    List<Book> findByNameContainingIgnoreCase(String name);
    List<Book> findByAuthorContainingIgnoreCase(String author);
}
