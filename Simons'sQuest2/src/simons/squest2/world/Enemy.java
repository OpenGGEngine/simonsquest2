/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simons.squest2.world;

import javafx.scene.image.Image;
import simons.squest2.GameVariables;
import simons.squest2.Item;
import simons.squest2.SimonsSQuest2;
import simons.squest2.states.BattleState;

/**
 *
 * @author Javier
 */
public class Enemy extends Feature implements Cloneable{
    public String enemyType;
    public Image image;
    public int health;
    public int maxhealth;
    String drop;
    public boolean alive = true;
    Attack[] attacks = new Attack[4];
    
     public Enemy(int x, int y, String name, Image image, int health){
        this.x = x;
        this.y = y;
        this.enemyType = name;
        this.image = image;
        this.health = health;
        this.maxhealth = health;
    }
    
    public void setAttacks(Attack[] a){
        this.attacks = a;
    }
    

    public String encounter(){
        BattleState bs = new BattleState("BattleState");
        bs.setEnemy(this);
        SimonsSQuest2.s.addState(bs);
        SimonsSQuest2.s.setState("BattleState");
        return enemyType + " has been encountered!";
    }
    
    public String attack(){
        double rand = Math.random();
        Attack a = attacks[(int) (Math.random() * attacks.length)];
        
        if(a.heal){
            health = (int)Math.min((health + a.damage), maxhealth);
            return (enemyType + " used " + a.name + " to heal " + a.damage + " health points!");
        }
        if(Math.random() < a.chanceToHit){
            GameVariables.playerhealth -= a.damage;
            return (enemyType + " used " + a.name + " to deal " + a.damage + " points of damage!");
        }
        return (enemyType +  " used " + a.name + " and missed!");
    }
    
    public String damage(int dmg){
        health -= dmg;
        if(health <= 0)
            return onKill();
        return enemyType + " took " + dmg + " damage!";
    }
    
    public String onKill(){
        alive = false;
        Item.add(name);
        if(name == null){
            return enemyType + " died!";
        }
        return enemyType + " died and dropped 1 " + name + "!";
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
