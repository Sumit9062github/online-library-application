package com.library.controller;

import com.library.entity.BorrowRecord;
import com.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping("/take")
    public BorrowRecord borrowBook(@RequestParam Long userId,
                                   @RequestParam Long bookId,
                                   @RequestParam int days) {
        return borrowService.borrowBook(userId, bookId, days);
    }

    @PostMapping("/return")
    public BorrowRecord returnBook(@RequestParam Long userId,
                                   @RequestParam Long bookId) {
        return borrowService.returnBook(userId, bookId);
    }
}
