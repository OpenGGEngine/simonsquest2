/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simons.squest2.world;

import javafx.scene.image.Image;

/**
 *
 * @author Javier
 */
public class Enemy {
    int x,y;
    String enemyType;
    Image image;
    
    public Enemy(int x, int y, String name, Image image){
        this.x = x;
        this.y = y;
        this.enemyType = name;
        this.image = image;
    }
}
