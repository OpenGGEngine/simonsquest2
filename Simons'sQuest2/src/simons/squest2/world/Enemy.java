/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simons.squest2.world;

import javafx.scene.image.Image;
import simons.squest2.GameVariables;
import simons.squest2.Item;

/**
 *
 * @author Javier
 */
public class Enemy extends Feature{
    String enemyType;
    public Image image;
    int health;
    String drop;
    boolean alive = true;
    Attack[] attacks = new Attack[4];
    
    public Enemy(int x, int y, String name, Image image, int health, Attack[] attacks){
        this.x = x;
        this.y = y;
        this.enemyType = name;
        this.image = image;
        this.health = health;
        this.attacks = attacks;
    }
    
    public class Attack{
        String name;
        double damage;
        boolean heal;
        double chanceToHit;
    }

    public String encounter(){
        return enemyType + " has been encountered!";
    }
    
    public String attack(){
        double rand = Math.random();
        Attack a;
        if(rand < 0.25){
            a = attacks[0];
        }else if(rand >= 0.25 && rand < 0.5){
            a = attacks[1];
        }else if(rand >= 0.5 && rand < 0.75){
            a = attacks[2];
        }else{
            a = attacks[3];
        }
        
        if(a.heal){
            health += a.damage;
            return (enemyType + " used " + a.name + " to heal " + a.damage + " health points!");
        }
        if(Math.random() < a.chanceToHit){
            GameVariables.playerhealth -= a.damage;
            return (enemyType + " used " + a.name + " to deal " + a.damage + " points of damage!");
        }
        return (enemyType + " missed!");
    }
    
    public String damage(int dmg){
        health -= dmg;
        if(health <= 0)
            return onKill();
        return enemyType + " died!";
    }
    
    public String onKill(){
        alive = false;
        Item.add(name);
        return enemyType + " died and dropped 1 " + name + "!";
    }
}
