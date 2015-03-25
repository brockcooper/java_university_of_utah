/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package a1brockcooper;

import java.net.URL;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.io.IOException;

/**
 *
 * @author JMB
 */
public class A1BrockCooper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String eventText; //variable to store the raw event text
        String eventTextNoHTML;
        
        //get the 3rd face-off in the game
        
        eventText = getNthEventByType(3, "FAC", "http://www.nhl.com/scores/htmlreports/20132014/PL020026.HTM");

        //get the 5th face-off in the game
//       eventText = getNthEventByType(5, "FAC", "http://www.nhl.com/scores/htmlreports/20132014/PL020026.HTM");

        
        //Task 2
        eventTextNoHTML = removeTags(eventText);
        System.out.println("The value of eventTextNoHTML is: " + eventTextNoHTML);
        
        char[] eventTextNoHTMLCharArr = eventTextNoHTML.toCharArray();
        
        //Task 3 
        task3(eventTextNoHTMLCharArr);
        
        //Task 4
        task4(eventTextNoHTMLCharArr);
        
        //Task 5
        int white = task5(eventTextNoHTMLCharArr);
        System.out.println("The number of spaces until we reach the first player’s last name is: " + white);
        
        //Task 6
        String winningTeam = getWinningTeam(eventTextNoHTMLCharArr);
        System.out.println("The value of winningTeam is: " + winningTeam);
        
        String faceoffZone = getFaceoffZone(eventTextNoHTMLCharArr);
        System.out.println("The value of faceoffZone is: " + faceoffZone);
        
        int firstPlayerNumber = getFirstPlayerNumber(eventTextNoHTMLCharArr, eventTextNoHTML);
        System.out.println("The value of firstPlayerNumber is: " + firstPlayerNumber);
        
        String firstPlayerTeam = getFirstPlayerTeam(eventTextNoHTML);
        System.out.println("The value of firstPlayerTeam is: " + firstPlayerTeam);
        
        String firstPlayerLastName = getFirstPlayerLastName(eventTextNoHTML);
        System.out.println("The value of firstPlayerLastName is: " + firstPlayerLastName);
        
        String secondPlayerTeam = getSecondPlayerTeam(eventTextNoHTML);
        System.out.println("The value of secondPlayerTeam is: " + secondPlayerTeam);
        
        int secondPlayerNumber = getSecondPlayerNumber(eventTextNoHTML);
        System.out.println("The value of secondPlayerNumber is: " + secondPlayerNumber);
        
        String secondPlayerLastName = getSecondPlayerLastName(eventTextNoHTML);
        System.out.println("The value of secondPlayerLastName is: " + secondPlayerLastName);
        
        System.out.println("The first player’s number is " + comparison(firstPlayerNumber, secondPlayerNumber) + " the second player’s number.");
        
        System.out.println("The length of the string in the variable secondPlayerLastName is: " + secondPlayerLastName.length());

        //Task 7
        String firstPlayerLastNameFormatted = properCase(firstPlayerLastName);
        System.out.println("The value of firstPlayerLastNameFormatted is: " + firstPlayerLastNameFormatted);
        
        //Task 8
        StringBuilder csv = toCSV(winningTeam, faceoffZone, firstPlayerNumber,
                            firstPlayerTeam, firstPlayerLastName, secondPlayerTeam,
                            secondPlayerNumber, secondPlayerLastName);
        System.out.println("The .csv row is: " + csv);
    }
    /**
     * 
     * @param n
     * @param eventType
     * @param urlText
     * @return 
     * 
     * This code has been modified from 
     * http://stackoverflow.com/questions/238547/how-do-you-programmatically-download-a-webpage-in-java
     * 
     */
    public static String getNthEventByType(int n, String eventType, String urlText){
        
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line = "not found"; //initialize result line to "not found"
        int eventCount = 0;
       
        try {
            url = new URL(urlText);
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while (eventCount < n && (line = br.readLine()) != null) {
                if(line.contains(eventType)) {                    
                    //we know the next line is the face-off details
                    line = br.readLine();
                    eventCount += 1;
                }
            }
        } catch (MalformedURLException mue) {
             mue.printStackTrace();
        } catch (IOException ioe) {
             ioe.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
                // nothing to see here
            }
        }
        return line;
    }
    public static String removeTags(String htmlText){
        int endOfFirstTag = htmlText.indexOf('>');
        int startOfClosingTag = htmlText.indexOf('<', endOfFirstTag);
        String noTags = htmlText.substring(endOfFirstTag + 1, startOfClosingTag);
        return noTags;
    }
    
    public static void task3(char[] myArray){
        System.out.println("The length of the array eventTextNoHTMLCharArr is: " + myArray.length);
        System.out.println("The second character in eventTextNoHTMLCharArr is: " + myArray[1]);
    }
    
    public static void task4(char[] myArray){
        int specialCharCount = 0;
        for(int i = 0; i<myArray.length; i++){
            String character = "" + myArray[i];
            if (character.matches("[^a-zA-Z0-9 ]")) {
                specialCharCount++;
            } 
        }
        System.out.println("The number of special characters in eventTextNoHTMLCharArr is: " + specialCharCount);
            
    }
    
    public static int task5(char[] myArray){
        int whiteSpace = 0;
        Boolean hashSymbol = false;
        int i = 0;
        while (hashSymbol == false){
            String character = "" + myArray[i];
            if (character.matches("[ ]")){
                whiteSpace++;
            }
            if (character.matches("[#]")){
                hashSymbol = true;
            }
            i++;
        }
        
        return (whiteSpace + 1);    
    }
    
    public static String getWinningTeam(char[] faceoff){
        String winningTeam = "";
        for(int i = 0; i<3; i++){
            winningTeam = winningTeam + faceoff[i];
        }
        return winningTeam;
    }
    
    public static String getFaceoffZone(char[] faceoff){
        String zone = "";
        for(int i = 8; i<17; i++){
            zone = zone + faceoff[i];
        }
        return zone;
    }
    
    public static int getFirstPlayerNumber(char[] faceoff, String textFaceoff){
        String playerNumberString = "";
        int i = textFaceoff.indexOf("#") + 1;
        Boolean whiteSpace = false;
        
        while(whiteSpace == false){
            String character = "" + faceoff[i];
            
            if (character.matches("[ ]")) { 
                whiteSpace = true; 
            } else {
                playerNumberString = playerNumberString + character;
            }
            i++;
        }
        
        int playerNumber = Integer.parseInt(playerNumberString);
        return playerNumber;
    }
    
    public static String getFirstPlayerTeam(String textFaceoff){
        int start = textFaceoff.indexOf("-") + 2;
        int end = textFaceoff.indexOf("#") - 1;
        String team = textFaceoff.substring(start, end);
        return team;
    }
    
    public static String getFirstPlayerLastName(String textFaceoff){
        String afterHash = textFaceoff.substring(textFaceoff.indexOf("#"));
        int start = afterHash.indexOf(" ") + 1;
        int end = afterHash.indexOf("vs") - 1;
        String name = afterHash.substring(start, end);
        return name;
    }
    
    public static String getSecondPlayerTeam(String textFaceoff){
        String afterVS = textFaceoff.substring(textFaceoff.indexOf("vs") + 3);
        int end = afterVS.indexOf(" ");
        String name = afterVS.substring(0, end);
        return name;
    }
    
    public static int getSecondPlayerNumber(String textFaceoff){
        String afterSecondHash = textFaceoff.substring(textFaceoff.indexOf("#", textFaceoff.indexOf("#") + 1)+1);
        int end = afterSecondHash.indexOf(" ");
        String playerNumberString = afterSecondHash.substring(0, end);
        int playerNumber = Integer.parseInt(playerNumberString);
        return playerNumber;
    }
    
    public static String getSecondPlayerLastName(String textFaceoff){
        String afterVS = textFaceoff.substring(textFaceoff.indexOf("vs") + 3);
        String afterHash = afterVS.substring(afterVS.indexOf("#"));
        int start = afterHash.indexOf(" ") + 1;
        String name = afterHash.substring(start);
        return name;
    }
    
    public static String comparison(int playerOne, int playerTwo){
        String answer = "";
        if (playerOne == playerTwo){
            answer = "equal to";
        } else if (playerOne < playerTwo){
            answer = "less than";
        } else if (playerOne > playerTwo){
            answer = "greater than";
        }
        return answer;
    }
    
    public static String properCase(String name){
        String lowerPart = name.substring(1);
        String upperPart = name.substring(0, 1);
        String properCaseName = upperPart + lowerPart.toLowerCase();
        return properCaseName;
    }
    
    public static StringBuilder toCSV(String winningTeam, String faceoffZone, int firstPlayerNumber,
            String firstPlayerTeam, String firstPlayerLastName, String secondPlayerTeam,
            int secondPlayerNumber, String secondPlayerLastName){
        StringBuilder csv = new StringBuilder();
        csv.append(winningTeam);
        csv.append(",");
        csv.append(faceoffZone);
        csv.append(",");
        csv.append(firstPlayerNumber);
        csv.append(",");
        csv.append(firstPlayerTeam);
        csv.append(",");
        csv.append(firstPlayerLastName);
        csv.append(",");
        csv.append(secondPlayerTeam);
        csv.append(",");
        csv.append(secondPlayerNumber);
        csv.append(",");
        csv.append(secondPlayerLastName);
        return csv;
    }
    
}
