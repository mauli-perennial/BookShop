package com.perennialsys.bookshop.repository;


import com.perennialsys.bookshop.domain.Book;
import com.perennialsys.bookshop.exceptions.EmptyCatalog;
import com.perennialsys.bookshop.exceptions.NoBookByAuthor;
import com.perennialsys.bookshop.exceptions.NoBookByTitle;
import com.perennialsys.bookshop.exceptions.NoSuchBookById;

import java.util.*;

/**
 * This class belong to the book shelve in the library which is used to achieve the singleton instance from the class.
 */
public final class Shelve {
    /**
     * This Parameter belongs to the numbers of books to be stored in the library.
     */
    private final Set<Book> bookStore;

    /**
     * This Parameter is used to achieve the singleton instance of the Catalogue
     */
    private static Shelve instance;

    private Shelve(){
        bookStore = new HashSet<>();
    }

    public static Shelve getInstance(){
        if(instance == null){
            instance = new Shelve();
        }
        return instance;
    }

    /**
     * This method is used to add book in the shelve/catalogue
     * @author Mauli Satav <mauli.satav@perennialsys.com>
     * @param book Take book object as A input parameter .
     */
    public void add(Book book){
        bookStore.add(book);
    }

    /**
     * This method is used to search the book from shelves by the name of the book according to user need
     * @param title It will take a book title as input parameter.
     * @return List of books by such titles.
     * @throws NoBookByTitle It will throw the Exception when there is no any book by title
     * @author Mauli Satav <mauli.satav@perennialsys.com>
     */
    public Optional<Book> searchByTitle(String title) throws NoBookByTitle{
        Optional<Book> result =  bookStore.stream().filter(book -> book.hasTitleLike(title)).findAny();
        if(result.isEmpty()){
            throw new NoBookByTitle("Book not found by this Title.");
        }
        return result;
    }

    /**
     * This method is used for search books from the shelve according to authors of the book which returns the books list.
     * @param author
     * @return returns the list of books by matching authors.
     * @throws NoBookByAuthor Exception will be thrown when there is no any book by such id in the library.
     * @author Mauli Satav
     */
    public Optional<Book> searchByAuthor(String author) throws NoBookByAuthor {
        Optional<Book> result =  bookStore.stream().filter(book -> book.hasAuthorLike(author)).findAny();
        if(result.isEmpty()){
            throw new NoBookByAuthor("Book not found by this Title.");
        }
        return result;
    }

    /**
     * This method will return the all books from the library.
     * @return It returns the list of books present in the library .
     * @throws EmptyCatalog Exception will be thrown when there is empty shelve.
     * @author Mauli satav
     */
    public List<Book> getAllBooks() throws EmptyCatalog {
        if(bookStore.isEmpty()){
            throw new EmptyCatalog("No book is there.");
        }
        return new ArrayList<>(bookStore);
    }

    /**
     * This Method is used for searching the book in shelve by its id .
     * @param id It will take a book I'd as a parameter.
     * @return It returns the book object associated with the book I'd.
     * @throws NoSuchBookById It will throw the exception when  there is no any book by such I'd.
     */
    public Book searchByBookId(int id) throws NoSuchBookById {
        Book result =  bookStore.stream().filter(book -> book.getId() == id).findAny().orElse(null);
        if(result==null){
            throw new NoSuchBookById("Book not found by this ID.");
        }
        return result;
    }
}
