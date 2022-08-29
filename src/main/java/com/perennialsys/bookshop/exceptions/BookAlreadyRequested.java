package com.perennialsys.bookshop.exceptions;

public class BookAlreadyRequested extends Exception {
    public BookAlreadyRequested(String message){
        super(message);
    }
}
