/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a5brockcooper;

import java.util.Scanner;

/**
 *
 * @author Brock
 */
public class A5BrockCooper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        PalindromeChecker palindrome;
        palindrome = new PalindromeChecker();
        PalindromePrinter printer = new PalindromePrinter();
        boolean isPalin;
        String reversed;
        
        
        String line = "0";
        while (!line.isEmpty()){
            System.out.println("Your expression (or return to end):");
            line = console.nextLine();
            isPalin = palindrome.isPalindrome(line);
            if (line.isEmpty()) {
                System.out.println("Bye!");
            } else if(isPalin){
                System.out.println("That is a palindrome.");
                System.out.println("The reversed string is: " + palindrome.reverseString(line));
                printer.add(line);
                printer.print();
                
            } else {
                System.out.println("That is NOT a palindrome.");
            }
        }
    }
    
}
