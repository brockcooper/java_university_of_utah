/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4brockcooper;

import java.util.Arrays;

/**
 *
 * @author Brock
 */
public class IntegerArraySort implements IntegerArraySortInterface {
    @Override
    public void bubbleSort(int[] arrayToSort){
        int j;
        boolean swapped = true;   // set flag to true to begin first pass
        int temp;   //holding variable

     while ( swapped )
     {
            swapped= false;    //set flag to false awaiting a possible swap
            for( j=0;  j < arrayToSort.length - 1;  j++ )
            {
                   if ( arrayToSort[j] > arrayToSort[j+1] )   // change to > for ascending sort
                   {
                        temp = arrayToSort[ j ];                //swap elements
                        arrayToSort[j] = arrayToSort[j+1];
                        arrayToSort[j+1] = temp;
                        swapped = true;              //shows a swap occurred  
                  } 
            } 
      } 
     //System.out.println(Arrays.toString(arrayToSort));
    }
    @Override
    public void selectionSort(int[] arrayToSort){
        int i, j, first, temp;  
        for ( i = arrayToSort.length - 1; i > 0; i-- ) {
          first = 0;   //initialize to subscript of first element
          for(j = 1; j <= i; j ++)   //locate smallest element between positions 1 and i.
          {
               if( arrayToSort[ j ] > arrayToSort[ first ] )         
                 first = j;
          }
          temp = arrayToSort[ first ];   //swap smallest found with element in position i.
          arrayToSort[ first ] = arrayToSort[ i ];
          arrayToSort[ i ] = temp; 
        }
        //System.out.println(Arrays.toString(arrayToSort));
    }
    
    // Code leveraged from http://mathbits.com/MathBits/Java/arrays/SelectionSort.htm and http://mathbits.com/MathBits/Java/arrays/Bubble.htm
}
