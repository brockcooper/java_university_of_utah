/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4brockcooper;

/**
 *
 * @author Brock
 */
public class Menu extends MenuBase {
    @Override
    public int getLengthOfArrayToSort(){
       System.out.println("Please enter the length of the array to sort as a NON-NEGATIVE integer");
       lengthOfArrayToSort = console.nextInt();
       return lengthOfArrayToSort;
   }
}
