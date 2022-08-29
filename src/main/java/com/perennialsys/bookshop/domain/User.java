package com.perennialsys.bookshop.domain;

/**
 * This class holds the information of member of the book sharing application.
 *
 * @author Mauli satav <mauli.satav@perennialsys.com>
 */
public final class User {
    /**
     * This is used to display user's name. It is an optional parameter if not given it is same as username.
     */
    private final String displayName;
    /**
     * Username is a mandatory parameter which is used to identify member uniquely.
     */
    private final String userName;

    private final String password;
    /**
     * Status can be used to deactivate account or soft delete the user.
     */
    private Statuses status;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        displayName = userName;
        status = Statuses.ACTIVE;
    }

    public User(String userName, String password, String displayName) {
        this.userName = userName;
        this.password = password;
        this.displayName = displayName;
        status = Statuses.ACTIVE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User member = (User) o;

        if (!userName.equals(member.userName)) return false;
        if (!password.equals(member.password)) return false;
        return status == Statuses.ACTIVE;
    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public void setActive() {
        status = Statuses.ACTIVE;
    }

    public void setInactive() {
        status = Statuses.INACTIVE;
    }

    public String checkUserName(){
        return this.userName;
    }
    public enum Statuses {
        ACTIVE, INACTIVE
    }
}
