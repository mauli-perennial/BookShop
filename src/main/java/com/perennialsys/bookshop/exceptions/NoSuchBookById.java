package com.perennialsys.bookshop.exceptions;

public class NoSuchBookById extends Exception{
    public NoSuchBookById(String message){
        super(message);
    }
}
