package com.perennialsys.bookshop.domain;

import com.perennialsys.bookshop.util.AutoIdGenerator;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * This class store the details of the book which is added by the user.
 *
 * @author Mauli Satav <mauli.satav@perennialsys.com>
 */
public final class Book {

    private final long id;
    /**
     * bookName is a mandatory parameter. It is used to store the name of a book.
     */
    private final String bookName;
    /**
     * author is a mandatory parameter. It is used to store the multiple author of the book
     */
    private final Set<String> authors;
    /**
     * owner is used to store the user, who is the owner of a book.
     */
    private final User owner;
    /**
     * status is used to store the available and unavailable status of the book.
     */
    private BookStatuses status;

    public Book(String bookName, Set<String> authors, User owner) {
        id = AutoIdGenerator.next(this.getClass().getSimpleName());
        this.bookName = bookName;
        this.authors = authors;
        this.owner = owner;
        status = BookStatuses.AVAILABLE;
    }

    public Book(String bookName, String author, User owner) {
        id = AutoIdGenerator.next(this.getClass().getSimpleName());
        this.bookName = bookName;
        authors = new HashSet<>();
        authors.add(author);
        this.owner = owner;
        status = BookStatuses.AVAILABLE;
    }

    public Book(String bookName, String[] authors, User owner) {
        id = AutoIdGenerator.next(this.getClass().getSimpleName());
        this.bookName = bookName;
        this.authors = new HashSet<>();
        for (String author : authors) {
            this.authors.add(author);
        }
        this.owner = owner;
        status = BookStatuses.AVAILABLE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return id == book.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public void returnBook() {
        status = BookStatuses.AVAILABLE;
    }

    public long getId() {
        return id;
    }

    public void issueBook() {
        status = BookStatuses.TAKEN;
    }

    public boolean isAvailable() {
        return status == BookStatuses.AVAILABLE;
    }

    public boolean isTaken() {
        return status == BookStatuses.TAKEN;
    }

    /**
     * This method will return the boolean when user searches book in the library by certain name of the book.
     * @param searchTerm
     * @return
     */
    public boolean hasTitleLike(String searchTerm) {
        return bookName.startsWith(searchTerm);
    }

    /**
     * This method will return the boolean when user searches book in library by its authors and when such book present in the Library
     * @param searchTerm
     * @return
     */
    public boolean hasAuthorLike(String searchTerm) {
        Optional<String> searchResult = authors.stream().filter(term -> term.startsWith(searchTerm)).findAny();
        return searchResult.isPresent();
    }

    @Override
    public String toString() {
        return "{" + bookName + "\t" + authors + "\t" + owner + "\t" + status + id +"}";
    }

    /**
     * This enum is defined to show various books statuses in the library.
     * @author Mauli satav.
     */
    public enum BookStatuses {
        AVAILABLE, TAKEN, DISCONTINUED
    }
}
