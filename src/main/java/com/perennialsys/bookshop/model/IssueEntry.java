package com.perennialsys.bookshop.model;

import com.perennialsys.bookshop.domain.Book;
import com.perennialsys.bookshop.domain.User;

import java.time.LocalDate;
import java.util.Objects;

/**
 * This class belongs to the mark the entry of issued book by members of library.
 * @author Mauli Satav <mauli.satav@perennialsys.com>
 */
public class IssueEntry {
    /**
     *
     * This parameter is used for book to be issued from library.
     */
    private final Book issuedBook;
    /**
     * This parameter belongs to member who is borrowing book.
     *
     */
    private final User borrower;
    /**
     * This Parameter belongs to the date of borrowing book which is internally managed.
     */
    private final LocalDate issuedOn;
    /**
     * This Parameter belongs to date on which book will be returned by the borrower.
     */
    private final LocalDate dueOn;


    public IssueEntry(Book issuedBook, User borrower, LocalDate issuedOn, int duration) {
        this.issuedBook = issuedBook;
        this.borrower = borrower;
        this.issuedOn = issuedOn;
        this.dueOn = issuedOn.plusDays(duration);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IssueEntry)) return false;
        IssueEntry entry = (IssueEntry) o;
        return Objects.equals(getIssuedBook(), entry.getIssuedBook());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIssuedBook());
    }

    public Book getIssuedBook() {
        return issuedBook;
    }

    public User getBorrower() {
        return borrower;
    }

    public LocalDate getIssuedOn() {
        return issuedOn;
    }

    public LocalDate getDueOn() {
        return dueOn;
    }
}
