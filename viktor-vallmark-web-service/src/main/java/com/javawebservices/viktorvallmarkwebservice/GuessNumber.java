package com.javawebservices.viktorvallmarkwebservice;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GuessNumber {

    private numberBean numBean;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    private final int computerGuess;


    /**
     * 
     */
    public GuessNumber(String name)
    {
        this.numBean = new numberBean(name);
        this.computerGuess = ThreadLocalRandom.current().nextInt(MIN_NUMBER, MAX_NUMBER+1);
    }

    public GuessNumber()
    {
        this.numBean = new numberBean();
        this.computerGuess = ThreadLocalRandom.current().nextInt(MIN_NUMBER, MAX_NUMBER+1);
    }

    public numberBean getNumBean() {
        return numBean;
    }
    public int getComputerGuess()
    {
        return computerGuess;
    }

    public String playGameGuess(int playerGuess){
        String result = "";
        int numberOfGuesses = 0;
        int compGuess = getComputerGuess();
        if (playerGuess == compGuess)
        {
            numberOfGuesses +=1;
            result = compGuess + "Your guess is correct. Congratulations! You guessed correct in: " + Integer.toString(numberOfGuesses);
        }
        else if (playerGuess < compGuess)
        {
            numberOfGuesses+=1;
            result = compGuess + "Your guess is smaller than the number the computer chose. You have tried " + Integer.toString(numberOfGuesses) + " times.";
        }
        else
        {
            numberOfGuesses+=1;
            result = compGuess + "Your guess is larger than the number the computer chose. You have tried " + Integer.toString(numberOfGuesses)+ " times.";
        }
        return result;
    }
}

