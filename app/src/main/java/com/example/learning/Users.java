package com.example.learning;

public class Users {
    private String Name;
    private String LastName;
    private String Email;
    private String Password;
    private String Username;

public Users(){}

    public Users(String name, String lastName, String email, String password, String username) {
        Name = name;
        LastName = lastName;
        Email = email;
        Password = password;
        Username= username;


    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
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
