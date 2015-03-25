/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3brockcooper;
import java.util.*;
/**
 *
 * @author Brock
 */
public class Game {
    private final String gNumber;
    private final String hTeamCode;
    private final String vTeamCode;
    private final int rosterMaxSize = 18;
    private final ArrayList<Player> homeRoster = new ArrayList<>();

    
    public Game(String startGameNumber, String startHomeTeam, String startVisitTeam) {
        gNumber = startGameNumber;
        hTeamCode = startHomeTeam;
        vTeamCode = startVisitTeam;
    }
    
    public void addPlayer(String newPlayer, char position){
        Player player = new Player(newPlayer, position);
        homeRoster.add(player);
        getRosterSize();
    }    
    public String getGameNumber () {
        return gNumber;
    } 
    public String getGameDescription () {
        return vTeamCode + " vs. " + hTeamCode; 
    }  
    public int getRosterMaxSize() {
        return rosterMaxSize;
    }  
    public int getRosterSize() {
        return homeRoster.size();
    } 
    public ArrayList<Player> getHomeRoster(){
        return homeRoster;
    }
    
}
