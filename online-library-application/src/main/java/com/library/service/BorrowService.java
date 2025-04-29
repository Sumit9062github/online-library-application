package com.library.service;


import com.library.entity.BorrowRecord;

public interface BorrowService {
    BorrowRecord borrowBook(Long userId, Long bookId, int days);
    BorrowRecord returnBook(Long userId, Long bookId);
}
