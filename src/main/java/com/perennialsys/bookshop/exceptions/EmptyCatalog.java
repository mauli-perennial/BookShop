package com.perennialsys.bookshop.exceptions;

/**
 * This method is used to throw the exception when the catalogue of the book is empty.
 * @author Mauli satav
 */
public class EmptyCatalog extends Exception{
    public EmptyCatalog(String message) {
        super(message);
    }

}
