package org.example.model;

public class Person {

    private String name;
    private String surname;
    private int age;
    private double income;
    private Gender gender;

    public Person(String name, String surname, int age, double income, Gender gender) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.income = income;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}

