package com.example.learning;

public class Users {
    private String Name;
    private String LastName;
    private String Email;
    private String Password;
public Users(){}

    public Users(String name, String lastName, String email, String password) {
        Name = name;
        LastName = lastName;
        Email = email;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }




}
