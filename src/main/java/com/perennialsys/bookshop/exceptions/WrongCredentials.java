package com.perennialsys.bookshop.exceptions;

public class WrongCredentials extends Exception{
    public WrongCredentials(String message){
        super(message);
    }
}
