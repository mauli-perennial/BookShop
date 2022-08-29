package com.perennialsys.bookshop.validations;

import com.perennialsys.bookshop.exceptions.BooKNameException;
import com.perennialsys.bookshop.exceptions.PasswordFormatException;
import com.perennialsys.bookshop.exceptions.WrongBookNameFormat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class belongs to the validation of password, bookName , displayName.
 */
public class ValidationUtils {
    private static final String BOOK_NAME = "^[a-zA-Z0-9]$";
    private static final Pattern patternBook = Pattern.compile(BOOK_NAME);
    private static final String PASSWORD_PATTERN = "^[A-Za-z]\\w{5,29}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    private static final String NAME = "^[a-zA-Z]$";
    private static final Pattern patternName = Pattern.compile(NAME);

    public static void validateBookName(String name) throws  WrongBookNameFormat {
        Matcher matcher = patternBook.matcher(name);
        if (!matcher.matches()) {
            throw new WrongBookNameFormat("Invalid book name format");
        }
    }

    public static void validateName(String name) throws BooKNameException {
        Matcher matcher = patternName.matcher(name);
        if (matcher.matches()) {
            throw new BooKNameException(" wrong book name format");
        }
    }
    private static boolean isValid(String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public static void checkPassword(String password) throws PasswordFormatException {
        if (isValid(password)) {
            throw new PasswordFormatException("password format wrong");
        }
    }
}

