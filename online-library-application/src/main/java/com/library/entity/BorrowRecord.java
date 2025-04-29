package com.library.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "borrow_record")
public class BorrowRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    private LocalDate borrowDate;

    private LocalDate returnDate;

    private boolean returned;

    // Private constructor
    private BorrowRecord() {
    }

    // --- Getters and Setters ---
    public Long getId() { return id; }

    public User getUser() { return user; }

    public Book getBook() { return book; }

    public LocalDate getBorrowDate() { return borrowDate; }

    public LocalDate getReturnDate() { return returnDate; }

    public boolean isReturned() { return returned; }

    public void setId(Long id) { this.id = id; }

    public void setUser(User user) { this.user = user; }

    public void setBook(Book book) { this.book = book; }

    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }

    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public void setReturned(boolean returned) { this.returned = returned; }

    // --- Manual Builder class ---
    public static class Builder {
        private final BorrowRecord record;

        public Builder() {
            this.record = new BorrowRecord();
        }

        public Builder user(User user) {
            this.record.setUser(user);
            return this;
        }

        public Builder book(Book book) {
            this.record.setBook(book);
            return this;
        }

        public Builder borrowDate(LocalDate borrowDate) {
            this.record.setBorrowDate(borrowDate);
            return this;
        }

        public Builder returnDate(LocalDate returnDate) {
            this.record.setReturnDate(returnDate);
            return this;
        }

        public Builder returned(boolean returned) {
            this.record.setReturned(returned);
            return this;
        }

        public BorrowRecord build() {
            return this.record;
        }
    }
}
