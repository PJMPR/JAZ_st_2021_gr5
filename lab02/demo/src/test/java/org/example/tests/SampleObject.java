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

}
