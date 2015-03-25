/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a5brockcooper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Brock
 */
public class PalindromePrinter {

    private HashMap palindromeMap = new HashMap();
    public void add(String palindrome) {
        palindromeMap.put(palindrome, palindrome.length());
    }

    public void print() {
        // code snippets taken from:  http://www.tutorialspoint.com/java/java_hashmap_class.htm
        // column code taken from: http://stackoverflow.com/questions/699878/is-there-an-easy-way-to-output-two-columns-to-the-console-in-java
        // Get a set of the entries
        TreeMap<String, Integer> sortedMap = SortByValue(palindromeMap);
        Set set = sortedMap.entrySet();
        // Get an iterator
        Iterator i = set.iterator();
        // Display elements
        
        System.out.printf("%-30.30s  %-30.30s%n", "String", "Length");
        
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            System.out.printf("%-30.30s  %-30.30s%n", me.getKey(), me.getValue());
        }
        System.out.println();
    }
    
    public static TreeMap<String, Integer> SortByValue 
			(HashMap<String, Integer> map) {
		ValueComparator vc =  new ValueComparator(map);
		TreeMap<String,Integer> sortedMap = new TreeMap<String,Integer>(vc);
		sortedMap.putAll(map);
		return sortedMap;
	}
}
