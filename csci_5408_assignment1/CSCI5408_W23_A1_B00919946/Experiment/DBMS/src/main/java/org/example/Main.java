package org.example;

import java.io.IOException;

public class Main {
    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        UserInput userInput = new UserInput();
        userInput.takingUserInput();
        System.out.println();
    }
}