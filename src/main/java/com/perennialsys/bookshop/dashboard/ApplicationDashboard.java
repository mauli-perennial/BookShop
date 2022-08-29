package com.perennialsys.bookshop.dashboard;

import com.perennialsys.bookshop.domain.Book;
import com.perennialsys.bookshop.domain.Library;
import com.perennialsys.bookshop.domain.User;
import com.perennialsys.bookshop.exceptions.WrongCredentials;
import com.perennialsys.bookshop.main.TestBook;
import com.perennialsys.bookshop.model.IssueEntry;
import com.perennialsys.bookshop.repository.Shelve;
import com.perennialsys.bookshop.validations.ValidationUtils;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * This class is related to the dashboard of application.
 *
 * @author Mauli Satav <mauli.satav@perennialsys.com>
 */
public class ApplicationDashboard {
    public static final Logger log = Logger.getLogger(String.valueOf(TestBook.class));
    public static final ResourceBundle bundle = ResourceBundle.getBundle("menu", Locale.CANADA_FRENCH);
    private static final Scanner sc = new Scanner(System.in);
    private final Shelve catalogue = Shelve.getInstance();
    private final Library library = Library.getInstance();

    public void applicationDashboard() {

        boolean exit = true;
        while (exit) {
            try {
                log.info("Please Choose the following options given below");
                String menu = String.format("%s %n %s %n %s ", bundle.getString("login"), bundle.getString("register"), bundle.getString("logout"));
                log.info(menu);
                String option = sc.next();
                switch (option) {
                    case "R":
                        log.info(bundle.getString("displayName"));
                        String displayName = sc.next();
                        try {
                            ValidationUtils.validateName(displayName);
                        } catch (Exception e) {
                            continue;
                        }
                        log.info(bundle.getString("userName"));
                        String userName = sc.next();
                        try {
                            ValidationUtils.validateName(userName);
                        } catch (Exception e) {
                            continue;
                        }
                        log.info(bundle.getString("password"));
                        String password = sc.next();
                        try {
                            ValidationUtils.checkPassword(password);
                        } catch (Exception e) {
                            continue;
                        }
                        library.addMember(new User(userName, password, displayName));
                        break;
                    case "L":
                        log.info(bundle.getString("userName"));
                        String loginUserName = sc.next();
                        log.info(bundle.getString("password"));
                        String loginPassword = sc.next();
                        User loginMember = library.authenticateUser(new User(loginUserName, loginPassword));
                        if (loginMember != null) {
                            libraryUserDashboard(loginMember);
                        } else {
                            throw new WrongCredentials("Enter valid credentials");
                        }
                        break;
                    case "E":
                        exit = false;
                        break;
                    default:
                        log.info(bundle.getString("wrongChoice"));
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void libraryUserDashboard(User member) {

        boolean logout = false;
        int bookId;
        Book bookById;
        while (!logout) {
            try {
                String menu = String.format("Please choose the below Options %n %s %n %s %n %s %n %s %n %s %n %s %n %s %n %s", bundle.getString("add"), bundle.getString("info"), bundle.getString("authors"), bundle.getString("issue"), bundle.getString("returnBook"), bundle.getString("request"),  bundle.getString("allEntry"), bundle.getString("exits"));
                log.info(menu);
                int option = Integer.parseInt(sc.nextLine());

                switch (option) {
                    case 1:
                        log.info(bundle.getString("bookName"));
                        String title = sc.nextLine();
                        log.info(bundle.getString("authorName"));
                        String authors = sc.nextLine();
                        catalogue.add(new Book(title, authors, member));
                        break;

                    case 2:
                        log.info(bundle.getString("bookName"));
                        String bookName = sc.nextLine();
                        log.info(catalogue.searchByTitle(bookName).toString());
                        break;

                    case 3:
                        log.info(bundle.getString("authorName"));
                        String authorsName = sc.nextLine();
                        log.info(catalogue.searchByAuthor(authorsName).toString());
                        break;

                    case 4:
                        log.info(bundle.getString("bookName"));
                        String bookTitle = sc.nextLine();
                        Optional<Book> bookList = catalogue.searchByTitle(bookTitle);
                        List<Book> muBook = bookList.stream().collect(Collectors.toList());
                        System.out.println(muBook);
                        System.out.println("Enter the Id of the Book.");
                        bookById = muBook.get(Integer.parseInt(sc.nextLine()));
                        System.out.println("Enter the number of days to return book.");
                        int days = Integer.parseInt(sc.nextLine());
                        library.issueBook(bookById, member, days);
                        break;

                    case 5:
                        log.info(bundle.getString("bookName"));
                        String book = sc.nextLine();
                        Optional<Book> bookLists = catalogue.searchByTitle(book);
                        List<Book> myBook = bookLists.stream().collect(Collectors.toList());
                        System.out.println(myBook);
                        System.out.println("Enter the Id of the Book.");
                        bookById = myBook.get(Integer.parseInt(sc.nextLine()));
                        library.returnBook(bookById);
                        break;


                    case 6:
                        log.info(bundle.getString("bookName"));
                        String bookrequest = sc.nextLine();
                        System.out.println(catalogue.searchByTitle(bookrequest));
                        log.info(bundle.getString("id"));
                        int id = Integer.parseInt(sc.nextLine());
                        Book requestBook = catalogue.searchByBookId(id);
                        if (requestBook != null && requestBook.isTaken()) {
                            library.requestBook(requestBook, member);
                        }
                        break;

                    case 7:
                        System.out.println(" all issue entries with due date after current date ");
                        for (IssueEntry entry : library.getIssueEntry()) {
                            log.info("book with details  " + entry.getIssuedBook() + " is with " + entry.getBorrower());
                        }

                        break;

                    case 9:
                        logout = true;
                        break;

                    default:
                        System.out.println("Invalid option choosen by you");
                        break;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
