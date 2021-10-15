package org.example;

public class Application {

    public static void main(String[] args){

        System.out.println("adam@wp.pl".matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$"));

    }
}