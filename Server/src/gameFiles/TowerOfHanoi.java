package gameFiles;

import gameCreation.GameBuilder;

public final class TowerOfHanoi extends GameBuilder {
    public TowerOfHanoi() {
        gameName = "TowerOfHanoi";
        gameDescription= "There are tree sticks with plates on this game";
    }

    public void setAmountOfRooms(){
        amountOfRooms=3;
    }
    public void setRooms() {
        fillGraph(0,1);
        fillGraph(0,2);
        fillGraph(1,2);
    }

    public void setItems(){
        fillVector(0,6);
    }

}
