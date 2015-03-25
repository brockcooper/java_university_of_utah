/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4brockcooper;

import java.util.Scanner;

/**
 *
 * @author Brock
 */
public class MenuBase {
   protected Scanner console = new Scanner(System.in);
   protected int lengthOfArrayToSort = 1;
   protected String sortAlgorithmChoice= "";
   
   public int getLengthOfArrayToSort(){
       System.out.println("Please enter the length of the array to sort as an integer");
       lengthOfArrayToSort = console.nextInt();
       return lengthOfArrayToSort;
   }
   
   public String getSortAlgorithmChoice(){
       System.out.println("Please enter algorithm choice: bubble or selection");
       sortAlgorithmChoice = console.next();
       return sortAlgorithmChoice;
   }
}
