package com.kenzie.app;

// import necessary libraries
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


import com.fasterxml.jackson.core.JsonProcessingException;


public class Main {
    /* Java Fundamentals Capstone project:
       - Define as many variables, properties, and methods as you decide are necessary to
       solve the program requirements.
       - You are not limited to only the class files included here
       - You must write the HTTP GET call inside the CustomHttpClient.sendGET(String URL) method
         definition provided
       - Your program execution must run from the main() method in Main.java
       - The rest is up to you. Good luck and happy coding!

     */

    // Method to ask user how many players are going to play and the game itself //

    public static String gameMethod(int userChoice) throws JsonProcessingException {
        int count = 0;
        int userScore1 = 0;
        int userScore2 = 0;

        int userSingleScore = 0;

        // if statement when user selects 2 player mode

        if (userChoice == 2) {

            // using a while loop to loop 10 times before exiting
            while (count < 10) {
                count += 1;

                CustomHttpClient myHttpClient = new CustomHttpClient();

                int randomNum1 = ThreadLocalRandom.current().nextInt(1, 100 + 1);
                int randomNum2 = ThreadLocalRandom.current().nextInt(1, 100 + 1);


            //////////////////////////////// PLAYER 1 //////////////////////////////////////////////


                // Building the URL to incorporate the random number generator
                StringBuilder sb1 = new StringBuilder();
                sb1.append("https://jservice.kenzie.academy/api/clues/");
                sb1.append(randomNum1);
                String url1 = sb1.toString();



                // Using my methods to read from the JSON data
                System.out.println("Player 1's turn!");
                String cluesResponseBody1 = myHttpClient.sendGET(url1);
                System.out.println(myHttpClient.formatCluesDTO(cluesResponseBody1));


                // scanner to read user input
                Scanner scanner = new Scanner(System.in);
                String userResponse1 = scanner.nextLine();


                // Using my custom method & if statement to check for accurate user response
                String answerCheckString = myHttpClient.answerCheck(cluesResponseBody1, userResponse1);
                String compareString = "Congrats! Your Answer was correct!";


                if (answerCheckString.equalsIgnoreCase(compareString)) {
                    userScore1 += 1;
                    System.out.println(answerCheckString);
                    System.out.println("Player 1's score: " + userScore1);
                } else System.out.println(answerCheckString);



                ///////////////////////////////// PLAYER 2 ///////////////////////////////////////


                // Building the URL to incorporate the random number generator
                StringBuilder sb2 = new StringBuilder();
                sb2.append("https://jservice.kenzie.academy/api/clues/");
                sb2.append(randomNum2);
                String url2 = sb2.toString();


                // Using my methods to read from the JSON data
                System.out.println("Player 2's Turn");
                String cluesResponseBody2 = myHttpClient.sendGET(url2);
                System.out.println(myHttpClient.formatCluesDTO(cluesResponseBody2));

                // scanner to read user input
                String userResponse2 = scanner.nextLine();

                // Using my custom method & if statement to check for accurate user response
                String answerCheckString2 = myHttpClient.answerCheck(cluesResponseBody2, userResponse2);

                if (answerCheckString2.equalsIgnoreCase(compareString)) {
                    userScore2 += 1;
                    System.out.println(answerCheckString2);
                    System.out.println("Player 2's score: " + userScore2);
                } else System.out.println(answerCheckString2);
            }
            // Exit Message
            return  "Thanks for playing!" + "\n" + "Player 1's final score was: " + userScore1 +
                    "\n" + "Player 2's final score was: " + userScore2;




            //////////////////////////////////////// SINGLE USER /////////////////////////////////////////

        } else
            while (count < 10) {
            count += 1;

            CustomHttpClient myHttpClient = new CustomHttpClient();

            int randomNum = ThreadLocalRandom.current().nextInt(1, 100 + 1);

            // Building the URL to incorporate the random number generator
            StringBuilder sb = new StringBuilder();
            sb.append("https://jservice.kenzie.academy/api/clues/");
            sb.append(randomNum);
            String url = sb.toString();


            // Using my methods to read from the JSON data
            String cluesResponseBody = myHttpClient.sendGET(url);
            System.out.println(myHttpClient.formatCluesDTO(cluesResponseBody));


            // scanner to read user input
            Scanner scanner = new Scanner(System.in);
            String userResponse = scanner.nextLine();


            // Using my custom method & if statement to check for accurate user response
            String answerCheckString = myHttpClient.answerCheck(cluesResponseBody, userResponse);
            String compareString = "Congrats! Your Answer was correct!";


            if (answerCheckString.equalsIgnoreCase(compareString)) {
                userSingleScore += 1;
                System.out.println(answerCheckString);
                System.out.println("User Score: " + userSingleScore);
            } else System.out.println(answerCheckString);
        }
        // Exit Message
        return  "Thanks for playing!" + "\n" + "Your final score was: " + userSingleScore;

    }

    public static void main(String[] args) throws JsonProcessingException {
        //Write main execution code here

            System.out.println("Hello welcome to the game!" + "\n" + "Input the number of players: 1 or 2");

            Scanner scanner = new Scanner(System.in);

            int userChoice = scanner.nextInt();

            System.out.println(gameMethod(userChoice));

        }
    }


