package com.perennialsys.bookshop.exceptions;

/**
 * This exception class is used to throw exception when the user is trying to return the book which is not taken by him
 */
public class BookNotTaken extends Exception {
    public BookNotTaken(String message) {
        super(message);
    }

}