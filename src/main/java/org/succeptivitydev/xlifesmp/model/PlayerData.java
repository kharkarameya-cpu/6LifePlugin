package org.succeptivitydev.xlifesmp.model;
import java.util.UUID;
public class PlayerData {
    private final UUID uuid;
    private int lives;

    public PlayerData(UUID uuid , int lives){
        this.uuid = uuid;
        this.lives = lives;
    }
    public UUID getUuid(){
        return uuid;
    }
    public int getLives(){
        return lives;
    }
    public void setLives(int lives){
        this.lives= lives;
    }
    public LifeState getState(){
        if (lives<=0){
            return LifeState.ELIMINATED;
        }
        if (lives==1){
            return LifeState.RED;
        }
        if (lives==2){
            return LifeState.YELLOW;
        }
        return LifeState.GREEN;

    }

}
