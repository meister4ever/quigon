package gameCreation;

import java.util.Vector;

public class Game {
    private Vector<Room> rooms;
    private String gameName;
    public static String gameDescription;

    Game(){
        rooms= new Vector<Room>();
    }
    public void setName(String gameName) { this.gameName = gameName; }
    public void setGameDescription(String gameDescription) { this.gameDescription = gameDescription; }

    public String receiveCommands(String command){
        return "doing Something";
    }
    public String getName(){return gameName;}
    public void setRooms(Vector<Room> rooms) { this.rooms = rooms; }
    public Vector<Room> getRooms(){
        return rooms;
    }
}
