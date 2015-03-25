/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3brockcooper;

/**
 *
 * @author Brock
 */
public class Player {
    private final String playerName;
    private final char playerPosition;
    
    public Player(String playerName, char playerPosition){
        this.playerName = playerName;
        this.playerPosition = playerPosition;
    }
    
    public String getPlayerName(){
        return playerName;
    }
    
    public char getPlayerPosition(){
        return playerPosition;
    }
    
}
