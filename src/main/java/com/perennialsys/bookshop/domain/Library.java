package com.perennialsys.bookshop.domain;

import com.perennialsys.bookshop.exceptions.*;
import com.perennialsys.bookshop.model.IssueEntry;
import com.perennialsys.bookshop.repository.Shelve;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class gives the singleton Instance of Library which is used to maintain and manipulate the members,shelves,issueEntry.
 */
public class Library {
    private static Library instance;
    /**
     * This parameter is set of members to be added to the library.
     */
    private final Set<User> members;
    /**
     * This Parameter is related to book shelve in library.
     */
    private final Shelve catalogue;
    /**
     * This parameter is used to mark and delete the entry of issued book
     */
    private final List<IssueEntry> issueEntry;
    private final Map<Book, List<User>> request;


    public Library() {
        members = new HashSet<>();
        catalogue = Shelve.getInstance();
        issueEntry = new ArrayList<>();
        request = new HashMap<>();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    /**
     * This method is used to add the members in the Library.
     *
     * @param member Takes user object to be added as input parameter.
     * @author Mauli Satav
     */
    public void addMember(User member) throws UserNameAlreadyExist {
        User checkUserName = members.stream().filter(user -> user.checkUserName().equals(member.checkUserName())).findFirst().orElse(null);
        if (checkUserName == null) {
            members.add(member);
        } else throw new UserNameAlreadyExist("User name is already taken.");
    }

    /**
     * This method is used for authenticating the user based on his username and password.
     *
     * @param memberCheck Takes user object to authenticate the user while login.
     * @return member or null It will return either user object or null value.
     * @author Mauli Satav.
     */
    public User authenticateUser(User memberCheck) {
        for (User member : members) {
            if (member.equals(memberCheck)) {
                return member;
            }
        }
        return null;
    }

    /**
     * This method is used to issue book from library and mark book status as TAKEN
     *
     * @param book   Takes book object to be issued as one of the input parameters.
     * @param member Takes user object who is going to issue book as one of the input parameter.
     * @param due    takes due as no of days user will take to return the book.
     * @throws BookTaken
     * @author Mauli Satav
     */
    public void issueBook(Book book, User member, int due) throws BookTaken {
        if (book.isAvailable()) {
            issueEntry.add(new IssueEntry(book, member, LocalDate.now(), due));
            book.issueBook();
        } else throw new BookTaken("The book is taken.");

    }

    /**
     * This method is used for returning the books to the Library and delete the entry from the IssueEntry.
     *
     * @param book takes the book object parameter as input parameter.
     * @throws BookNotTaken  throws when we try to return book which is not taken away.
     * @throws NoSuchRequest
     * @author Mauli Satav
     */
    public void returnBook(Book book) throws BookNotTaken, NoSuchRequest {
        if (book.isTaken()) {
            IssueEntry entry = issueEntry.stream().filter(entrys -> entrys.getIssuedBook().equals(book)).findFirst().orElse(null);
            book.returnBook();
            List<User> myMember = request.get(book);
            if (!myMember.isEmpty()) {
                for (User member : myMember) {
                    issueEntry.add(new IssueEntry(book, member, LocalDate.now(), 4));
                    book.issueBook();
                    myMember.remove(member);
                    break;
                }
            } else {
                throw new NoSuchRequest("No any request to book");
            }
            issueEntry.remove(entry);
        } else throw new BookNotTaken("The book is not taken");
    }

    /**
     * This Method is used for requesting the book from the library
     *
     * @param book   takes the book object to be requested as input parameter
     * @param member Takes the user object to be requested as input parameter
     * @throws BookNotTaken throws when we try to request book which is available
     * @author Mauli Satav
     */
    public void requestBook(Book book, User member) throws BookNotTaken {
        if (book != null && member != null) {
            if (book.isTaken()) {
                if (request.containsKey(book)) {
                    request.get(book).add(member);
                    System.out.println("book request added successfully");
                } else {
                    List<User> myList = new ArrayList<>();
                    myList.add(member);
                    request.put(book, myList);
                }
            } else {
                throw new BookNotTaken(" book is already available");
            }
        }
    }

    /**
     * This method will return the all the entries from the issueEntry.
     *
     * @return returns the list of issue entries of books which is due after current Date.
     * @throws EmptyCatalog will throw exception when entry is empty.
     * @author Mauli satav
     */
    public List<IssueEntry> getIssueEntry() throws EmptyCatalog {
        return issueEntry.stream().filter(issueEntry1 -> issueEntry1.getDueOn().isAfter(LocalDate.now())).collect(Collectors.toList());
    }
}

