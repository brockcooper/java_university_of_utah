/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4brockcooper;

//import java.util.Arrays;

/**
 *
 * @author Brock
 */
public class App {
    
    public static void main(String[] args) {
        Menu appMenu = new Menu();
        long start = 0;
        long end = 0;
        long runningTime;
        int lengthOfArrayToSortInput = appMenu.getLengthOfArrayToSort();
        int[] intArrayToSort = generateIntegerArray(lengthOfArrayToSortInput);

        String sortAlgorithmChoiceInput = appMenu.getSortAlgorithmChoice().toLowerCase();
        
        IntegerArraySort sortAlgorithm = new IntegerArraySort();
        
        if (sortAlgorithmChoiceInput.equals("bubble")){
            start = System.nanoTime();
                sortAlgorithm.bubbleSort(intArrayToSort);
            end = System.nanoTime();
        } else if (sortAlgorithmChoiceInput.equals("selection")){
            start = System.nanoTime();
                sortAlgorithm.selectionSort(intArrayToSort);
            end = System.nanoTime();
        } else {
            System.out.println("Invalid input. Please try again");
        }
        runningTime = end - start;
        System.out.println("Running time of " + sortAlgorithm + " sort with array of length " + lengthOfArrayToSortInput + " is : " + runningTime);
    }
    
    public static int[] generateIntegerArray(int n) {
        int[] array = new int[n];
        int arrayInput = n - 1;
        for(int i=0; i<n; i++ ){
            array[i] = arrayInput;
            arrayInput = arrayInput - 1;
        }
        return array;
    }
    
}
