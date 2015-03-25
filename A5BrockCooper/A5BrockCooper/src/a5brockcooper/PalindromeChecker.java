/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a5brockcooper;

import java.util.LinkedList;

/**
 *
 * @author Brock
 */
public class PalindromeChecker {
    public static boolean isPalindrome(String word){
        boolean answer = true;
        char[] array = word.toCharArray();
        LinkedList queue = new LinkedList();
        LinkedList stack = new LinkedList();
        
        for(int i=0; i<array.length; i++){
            if(Character.isLetter(array[i])){
                queue.addFirst(array[i]);
                stack.add(array[i]);
            }
        }
        
        while (answer == true && !queue.isEmpty()){
            if(queue.removeLast() != stack.removeLast()){
                answer = false;
            }
        }
        return answer;
    }
    public static String reverseString(String word){
        String reversed = "";
        char[] array = word.toCharArray();
        LinkedList stack = new LinkedList();
        for(int i=0; i<array.length; i++){
                stack.add(array[i]);
        }
        while (!stack.isEmpty()){
            reversed = reversed + stack.removeLast();
        } 
        return reversed;
    }
}
