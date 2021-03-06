package model;

public class User {
    private String firstName;
    private String lastName;
    private long albumID;

    public User() {
    }

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

    public long getAlbumID() {
        return albumID;
    }

    public void setAlbumID(long albumID) {
        this.albumID = albumID;
    }

    public User firstName(final String firstName) {
        this.firstName = firstName;
        return this;
    }

    public User lastName(final String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User albumID(final long albumID) {
        this.albumID = albumID;
        return this;
    }


}
