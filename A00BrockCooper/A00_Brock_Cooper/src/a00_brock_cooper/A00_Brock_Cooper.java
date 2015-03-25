/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a00_brock_cooper;

import java.util.Arrays;

/**
 *
 * @author Brock
 */
public class A00_Brock_Cooper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        goalies();
        task_four();
        task_five();
        
    }
    
    public static void goalies() {
        
        float sbobrovsky = 2.38f;
        float cprice = 2.32f;
        float aniemi = 2.39f;
        float jhalak = 2.25f;
        float jharding = 1.65f;
        
        float[] arrGoalies;
        arrGoalies = new float[5];
        arrGoalies[0] = sbobrovsky;
        arrGoalies[1] = cprice;
        arrGoalies[2] = aniemi;
        arrGoalies[3] = jhalak;
        arrGoalies[4] = jharding;
        System.out.println(Arrays.toString(arrGoalies));
        
        for(int i = 0; i<arrGoalies.length; i++){
            if (arrGoalies[i] < 2.0) {
                System.out.println("Excellent Goaltending");
            } else if (arrGoalies[i] < 2.5 ) {
                System.out.println("Strong Goaltending");
            } else if (arrGoalies[i] < 3.0) {
                System.out.println("Fair Goaltending");
            } else {
                System.out.println("Poor Goaltending");
            }
        }
            
    }
    public static void task_four() {
        float excellent = 1/10f;
        float strong = 1/2f;
        float fair = 1/5f;
        int poor_num = 10;
        float percent_remaining = 100 - (100*excellent) - (100*strong) - (100*fair);
        float total = poor_num / (percent_remaining/100);
        int fair_num = (int) ( fair * total);
        System.out.println("The total number of goaltenders is " + total);
        System.out.println("The number of fair goaltenders is " + fair_num);
    }
    public static void task_five() {
        float salary_cap = 64.3f;
        float ave_salary = 2.4f;
        float team_sum = 0f;
        int num_of_players = 0; 
        
        while (team_sum + ave_salary <= salary_cap) {
            team_sum = team_sum + ave_salary;
            num_of_players++;
        }
        System.out.println("The number of players that can be on a team is " + num_of_players);
    }
    
    
}
