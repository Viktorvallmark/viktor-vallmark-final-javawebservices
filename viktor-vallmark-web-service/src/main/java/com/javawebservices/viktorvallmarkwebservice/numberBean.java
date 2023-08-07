package com.javawebservices.viktorvallmarkwebservice;

public class numberBean {

    private int numberOfGuesses;
    private int playerGuess;


    public numberBean (int numberOfGuesses, int playerGuess) {
        this.numberOfGuesses = numberOfGuesses;
        this.playerGuess = playerGuess;
    }

    public void increaseGuesses () {
        numberOfGuesses++;
    }

    public int getNumberOfGuesses(){
        return numberOfGuesses;
    }

    public int getPlayerGuess() {
        return playerGuess;
    }

    public void setPlayerGuess(int guess) {
        this.playerGuess = guess;
    }
}
