/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3brockcooper;

import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Brock
 */
public class GameManagementSystem {
    private ArrayList<Game> gameArr = new ArrayList<>();
    public void main() {
        Scanner console = new Scanner(System.in);
        menu();

        int selection = console.nextInt();
        menuSelection(selection);
       // if(selection)
    }

    private static void menu(){
    System.out.println(    "1.	Add a game\n" +
                           "2.	Add a player to the home team roster for a game\n" +
                           "3.	Add a player to the visiting team roster for a game (not implemented)\n" +
                           "4.	View available games\n" +
                           "5.	View players on home team roster for a game\n" +
                           "6.	Exit");
    }
    
    private void menuSelection(int select){
        if(select == 1){
            optionOne();
        } else if (select == 2){
            optionTwo();
        } else if (select == 3){
            System.out.println("Sorry, this option is not yet implemented.  Please try another option.");
        } else if (select == 4){
            optionFour();
        } else if (select == 5){
            optionFive();
        } else if (select ==6){
            System.out.println("Goodbye");
            System.exit(0);
        }
        main();
    }
    
    private void optionOne(){
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter the game number:");
        String gameNumber = console.next();
        System.out.println("Please enter the home team three letter code:");
        String homeTeam = console.next();
        System.out.println("Please enter the visiting team three letter code:");
        String visitTeam = console.next();
        Game currGame = new Game(gameNumber, homeTeam, visitTeam);
        gameArr.add(currGame);
    }
    
    private void optionTwo(){
        areThereGames();
        Game gameNum = findGameNumber();
        addPlayerToGame(gameNum);
        System.out.println("Player successfully added. The game " + gameNum.getGameNumber() + " now has " + gameNum.getRosterSize() + " players on the home roster");
    }
    
    private void optionFour(){
        areThereGames();
        Game game = null;
        for(int i=0; i < gameArr.size(); i++){
            game = gameArr.get(i);
            System.out.println("Game number: " + game.getGameNumber() + ", Description: " + game.getGameDescription() + ", Home roster size: " + game.getRosterSize() + ", Visitor roster size: (not implemented)");
        }
    }
    
    private void optionFive(){
        areThereGames();
        Game gameNum = findGameNumber();
        ArrayList<Player> roster = gameNum.getHomeRoster();
        if(roster.isEmpty()){
            System.out.println("No players have been added to this roster.");
        } else {
            Player player = null;
            for(int i=0; i < roster.size(); i++){
                player = roster.get(i);
                System.out.println("Player Name: " + player.getPlayerName() + ", Position: " + player.getPlayerPosition());
            }
        }
    }
    
    private void areThereGames(){
        if (gameArr.isEmpty()){
            System.out.println("There are no games in the system.");
            main();
        }
    }
    
    private Game findGameNumber(){
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter game number:");
        String gameNumber = console.next();
        Game currGame = getGameObject(gameNumber);
        if(openPosition(currGame)){
            return currGame;
        } else {
            System.out.println("Sorry, the roster is full. Cannot add player.");
            return null;
        }
    }
    
    private Game getGameObject(String gameNum){
        Game currGame = null;
        Boolean isGameFound = false;
        for (int i=0; i < gameArr.size() && isGameFound == false; i++){
            if (gameArr.get(i).getGameNumber().equals(gameNum)){
                currGame = gameArr.get(i);
                isGameFound = true;
            }
        }
        if(isGameFound){
            return currGame;
        } else { 
            System.out.println("Sorry, that game was not found.  Please try again.");
            main();
            return null;
        }
    }
    
    private Boolean openPosition(Game game){
        return game.getRosterSize() <= game.getRosterMaxSize();
    }
    
    private void addPlayerToGame(Game game){
        Scanner console = new Scanner(System.in);
        System.out.println("Player Name:");
        String name = console.next();
        System.out.println("Player Position:");
        char position = console.next().charAt(0);
        game.addPlayer(name, position);
    }
}