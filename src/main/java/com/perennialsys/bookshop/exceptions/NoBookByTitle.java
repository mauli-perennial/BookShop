package com.perennialsys.bookshop.exceptions;

/**
 * This is used to throw exception when there is no book by title in the search.
 * @author Mauli satav
 */
public class NoBookByTitle extends Exception{
    public NoBookByTitle(String message) {
        super(message);
    }

}
