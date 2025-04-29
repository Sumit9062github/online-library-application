package com.library.repository;

import com.library.entity.BorrowRecord;
import com.library.entity.User;
import com.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    List<BorrowRecord> findByUser(User user);
    List<BorrowRecord> findByBook(Book book);
    Optional<BorrowRecord> findByUserAndBookAndReturnedFalse(User user, Book book);
}
