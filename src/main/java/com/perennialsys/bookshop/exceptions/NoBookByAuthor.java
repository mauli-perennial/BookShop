package com.perennialsys.bookshop.exceptions;

/**
 * This is used to throw the exception when there is no book by author when user searches for book in The library.
 */
public class NoBookByAuthor extends Exception {
    public NoBookByAuthor(String message) {
        super(message);
    }
}
