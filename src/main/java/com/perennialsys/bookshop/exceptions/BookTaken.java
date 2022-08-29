package com.perennialsys.bookshop.exceptions;

/**
 * This exception is used to throw the exception when user is trying to issue book which is already taken away by someone
 */
public class BookTaken extends Exception {
    public BookTaken(String message) {
        super(message);
    }

}
