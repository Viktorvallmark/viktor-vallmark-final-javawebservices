package com.javawebservices.viktorvallmarkwebservice;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GuessNumber {

    private numberBean numBean;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    private final int computerGuess;
    private String name;

    public GuessNumber(String name)
    {
        this.name = name;
        this.computerGuess = ThreadLocalRandom.current().nextInt(MIN_NUMBER, MAX_NUMBER+1);
    }

    public GuessNumber() {

        this.computerGuess = ThreadLocalRandom.current().nextInt(MIN_NUMBER, MAX_NUMBER+1);
    }

    public int getComputerGuess()
    {
        return computerGuess;
    }

    public String getName(){ return name;}
    public void setName(String name){this.name = name;}

    public String playGameGuess(int playerGuess){
        String result;
        numBean = new numberBean(0, playerGuess);
        int compGuess = getComputerGuess();
        if (playerGuess == compGuess)
        {
            numBean.increaseGuesses();
            result =  "Your guess is correct. Congratulations! You guessed correct in: " + numBean.getNumberOfGuesses();
        }
        else if (playerGuess < compGuess)
        {
            numBean.increaseGuesses();
            result = "Your guess is smaller than the number the computer chose. You have tried " + numBean.getNumberOfGuesses() + " times.";
        }
        else
        {
            numBean.increaseGuesses();
            result = "Your guess is larger than the number the computer chose. You have tried " + numBean.getNumberOfGuesses() + " times.";
        }
        return result;
    }
}

