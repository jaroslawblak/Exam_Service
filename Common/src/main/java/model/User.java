package model;

public class User {
    private String firstName;
    private String lastName;
    private int groupNumber;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public User firstName(final String firstName) {
        this.firstName = firstName;
        return this;
    }

    public User lastName(final String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User groupNumber(final int groupNumber) {
        this.groupNumber = groupNumber;
        return this;
    }


}
