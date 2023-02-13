package com.javawebservices.viktorvallmarkwebservice;

public class numberBean {

    private int numOfGuesses;

    private int guess;

    private String name;

    public numberBean(String name)
    {
        this.name = name;
    }
    public numberBean(){}

    public int getNumOfGuesses() {
        return numOfGuesses;
    }

    public void incNumOfGuesses() {
        this.numOfGuesses ++;
    }
    public void setNumOfGuesses(int numOfGuesses){ this.numOfGuesses = numOfGuesses;}

    public String getName()
    {
        return name;
    }
    public void setName(String name){this.name = name;}

    public int getGuess() {
        return guess;
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }

}
