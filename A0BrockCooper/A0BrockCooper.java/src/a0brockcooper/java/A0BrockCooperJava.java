/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a0brockcooper.java;
/**
 *
 * @author Brock
 */
public class A0BrockCooperJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String dashes = "-"; 
         for (int i=1; dashes.length()<50; i++){
            dashes = "-" + dashes;
        };
        System.out.println("FINAL	1ST    2ND   3RD    OT       SO       T \n"
                            + dashes +"\n"
                            + "SJS    |  1  |  0  |  1  |  0  |  (2 - 2)  |  3  |\n"
                            + dashes +"\n"
                            + "CHI    |  0  |  0  |  2  |  0  |  (0 - 2)  |  2  |\n"
                            + dashes +"\n"
        );
    }
    
}
