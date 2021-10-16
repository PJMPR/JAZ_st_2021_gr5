package org.example.tests;

import org.example.annotations.*;

public class SampleObject {

    @NotNull
    private String name;


    @NotNull
    @Regex(pattern = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$", message = "email should be in correct format")
    private String email;

    @Range(min=0, max=10)
    private int number;

    public SampleObject(String name, String email, int number) {
        this.name = name;
        this.email = email;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
