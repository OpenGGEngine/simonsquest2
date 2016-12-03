/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simons.squest2.world;

import javafx.scene.image.Image;
import simons.squest2.Item;

/**
 *
 * @author Javier
 */
public class Enemy {
    int x,y;
    String enemyType;
    Image image;
    int health;
    Item drop;
    boolean dead = false;
    
    public Enemy(int x, int y, String name, Image image){
        this.x = x;
        this.y = y;
        this.enemyType = name;
        this.image = image;
    }
    
    public class Attack{
        String name;
        double damage;
        double chanceToHit;
        int amount;
    }
    
    public Item damage(int dmg){
        health -= dmg;
        if(health <= 0)
            return onKill();
        return null;
    }
    
    public Item onKill(){
        dead = false;
        return drop;
    }
}
