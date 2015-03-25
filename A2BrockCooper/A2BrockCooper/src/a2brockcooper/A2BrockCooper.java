/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2brockcooper;

import java.net.URL;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;

/**
 *
 * @author JMB
 */
public class A2BrockCooper {

    /** 
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        System.out.println("Welcome to the NHL play by play event scraper. The "
                + "system will get events for the specified game for the "
                + "specified event type.");

        Scanner console = new Scanner(System.in);
        String userInput;

        do {
            int gameNumber = 0;
            int tempGameNumber = 0;
            userInput = "";
            
            // Get Game Number
            System.out.println("Please enter a valid game number:");
            while (gameNumber == 0) {
                if (console.hasNextInt()){
                    tempGameNumber = console.nextInt();
                    if (tempGameNumber >= 20001 && tempGameNumber <= 21230){
                        gameNumber = tempGameNumber;
                    } else {
                    System.out.println("Invalid game number. Please try again:");
                    }
                }
                else {
                    userInput = console.next();
                    if (userInput.toLowerCase().equals("exit")) {
                        break;
                    } else {
                        System.out.println("Invalid game number. Please try again:");
                        continue;
                    }                 
                }
            } 
            if (userInput.toLowerCase().equals("exit"))  break;
            
            // Get Event Type
            System.out.println("Please enter a valid event type:");
            String eventType = console.next();
            while (!eventType.toLowerCase().equals("shot")) {
                if (eventType.toLowerCase().equals("exit"))  {
                    userInput = eventType;
                    break;
                    } else {
                        System.out.println("Invalid event type. Please try again:"); 
                        eventType = console.next();
                    }   
                
            } 
            if (userInput.toLowerCase().equals("exit"))  break;
            
            // Get nth Event 
            System.out.println("Please enter the nth " + eventType + " you would like:");
            int nthEvent = -1;
            int tempnthEvent = 0;
             while (nthEvent == -1) {
                if (console.hasNextInt()){
                    tempnthEvent = console.nextInt();
                    if (tempnthEvent > 0){
                        nthEvent = tempnthEvent;
                    } else {
                    System.out.println("Invalid event number. Please try again:");
                    }
                }
                else {
                    userInput = console.next();
                    if (userInput.toLowerCase().equals("exit"))  {
                        break;
                    } else {
                        System.out.println("Invalid event number. Please try again:");
                        continue;
                    }                 
                }
            } 
            if (userInput.toLowerCase().equals("exit"))  break;
            
            //Get actual event
            String getEvent = getNthEventByType(nthEvent, eventType, gameNumber);
            String record = getShotDataFromEventHTML(getEvent);
            
            // Write to file
            int writeStatus = writeRecordToFile(gameNumber, eventType, record);
            System.out.println("The write status is: " + writeStatus);
            
            // Write to File
            int readStatus = printRecordsFromFile(gameNumber, eventType);
            System.out.println("The read status is: " + readStatus);
            
            
        } while (!userInput.toLowerCase().equals("exit")) ;
        System.out.println("Bye");
    }
    
        public static int writeRecordToFile(int gameNumber, String eventType, String record) throws IOException {
        int success = -1;
        
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(
                (gameNumber+"_"+eventType+".csv"), true));
                        
            writer.write(record+"\n");
        
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            success = 0;
        } finally {
            if (writer != null) {
                writer.close();
                success = 1;
            }
        }

        

        //TODO: your logic here
        return success;
    }
        
    public static int printRecordsFromFile(int gameNumber, String eventType) throws IOException {
	// write code to read line by line from file and print to screen.
        // also create a success variable to indicate the status of the 
        // file IO operation
        String csvFile = (gameNumber+"_"+eventType+".csv");
        BufferedReader br = null;
        String line = "";
        int success = -1;
        
        try {
		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
	} catch (IOException ioe) {
		System.out.println(ioe.getMessage());
                success = 0;
	} finally {
		if (br != null) {
			try {
				br.close();
                                success = 1;
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
                                success = 0;
			}
		}
	}
        
        return success;
    }
    
    public static String getNthEventByType(int n, String eventType, int gameNumber) {

        String urlText = "http://www.nhl.com/scores/htmlreports/20132014/PL0"
                + gameNumber + ".HTM";
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
                if (line.contains(eventType.toUpperCase())) {
                    //we know the next line is the face-off details
                    line = br.readLine();
                    eventCount += 1;
                }
            }
        } catch (MalformedURLException mue) {
            System.out.println(mue.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ioe) {
                // nothing to see here
            }
        }
        return line;
    }
    
    public static String getShotDataFromEventHTML(String eventHTML) {
        String result;

        //get shooter team
        int positionOfFirstGreaterThan = eventHTML.indexOf(">", 0);
        String shooterTeam = eventHTML.substring(positionOfFirstGreaterThan + 1, positionOfFirstGreaterThan + 4);

        //get shooter number
        int positionOfFirstHash = eventHTML.indexOf("#", 0);
        int positionOfFirstSpaceAfterFirstHash = eventHTML.indexOf(" ", positionOfFirstHash);
        String shooterPlayerNumber = eventHTML.substring(positionOfFirstHash + 1,
                positionOfFirstSpaceAfterFirstHash);

        //get shooter name
        int positionOfFirstCommaSpaceAfterFirstHash = eventHTML.indexOf(
                ", ", positionOfFirstSpaceAfterFirstHash + 1);
        String shooterLastName = eventHTML.substring(
                positionOfFirstSpaceAfterFirstHash + 1, positionOfFirstCommaSpaceAfterFirstHash);

        //get shot type
        int positionOfSecondCommaSpaceAfterShooterName = eventHTML.indexOf(
                ", ", positionOfFirstCommaSpaceAfterFirstHash + 1);
        String shotType = eventHTML.substring(positionOfFirstCommaSpaceAfterFirstHash + 2, positionOfSecondCommaSpaceAfterShooterName);

        //get zone
        int positionOfThirdCommaSpaceAfterShooterName = eventHTML.indexOf(
                ", ", positionOfSecondCommaSpaceAfterShooterName + 1);
        String zone = eventHTML.substring(positionOfSecondCommaSpaceAfterShooterName + 2, positionOfThirdCommaSpaceAfterShooterName);

        //get length
        int positionOfFirstSpaceAfterThirdCommaSpaceAfterShooterName = eventHTML.indexOf(
                " ", positionOfThirdCommaSpaceAfterShooterName + 2);
        String length = eventHTML.substring(positionOfThirdCommaSpaceAfterShooterName + 2, positionOfFirstSpaceAfterThirdCommaSpaceAfterShooterName);

        result = shooterTeam + "," + shooterPlayerNumber + "," + shooterLastName
                + "," + shotType + "," + zone + "," + length;
        return result;
    }

}