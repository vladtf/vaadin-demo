package com.live.vladislav.Models;

public class UserModel {
    private String FirstName;
    private String LastName;
    private String Email;

    public UserModel(String firstName, String lastName, String email)
    {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }

    public UserModel(){};

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
