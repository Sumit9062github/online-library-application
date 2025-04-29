package com.library.service;


import com.library.entity.Book;
import com.library.entity.BorrowRecord;
import com.library.entity.User;
import com.library.repository.BookRepository;
import com.library.repository.BorrowRecordRepository;
import com.library.repository.UserRepository;
import com.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BorrowRecord borrowBook(Long userId, Long bookId, int days) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Date validTillDate = user.getMembershipValidTill();

        if (validTillDate == null || validTillDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Membership expired or invalid");
        }

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (!book.getStatus().equalsIgnoreCase("AVAILABLE")) {
            throw new RuntimeException("Book is not available");
        }

        book.setStatus("BORROWED");
        bookRepository.save(book);

        BorrowRecord record = new BorrowRecord.Builder()
                .user(user)
                .book(book)
                .borrowDate(LocalDate.now())
                .returnDate(LocalDate.now().plusDays(days))
                .returned(false)
                .build();

        return borrowRecordRepository.save(record);
    }

    @Override
    public BorrowRecord returnBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        BorrowRecord record = borrowRecordRepository
                .findByUserAndBookAndReturnedFalse(user, book)
                .orElseThrow(() -> new RuntimeException("No active borrow record found"));

        record.setReturned(true);
        book.setStatus("AVAILABLE");

        bookRepository.save(book);
        return borrowRecordRepository.save(record);
    }
}
