/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simons.squest2.world;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 *
 * @author Javier
 */
public class RandomEnemyList {
    final static List<Enemy> enemies = new ArrayList<>();
    static{
        Enemy e = new Enemy(2, 2, "Beaver", new Image(new File("C:/res/beaver.png").toURI().toString()), 25);
        Attack[] a = new Attack[2];
        a[0] = new Attack("Swipe", 5, false, 0.9);
        a[1] = new Attack("Build Dam", 5, true, 1);
        e.setAttacks(a);
        enemies.add(e);
        
        Enemy e2 = new Enemy(2, 2, "Counter Terrorist", new Image(new File("C:/res/ct.png").toURI().toString()), 30);
        Attack[] a2 = new Attack[3];
        a2[0] = new Attack("Push A", 8, false, 0.95);
        a2[1] = new Attack("P90 Rush", 15, false, 0.5);
        a2[2] = new Attack("Ace", 25, false, 0.2);
        e2.setAttacks(a2);
        enemies.add(e2);
        
        Enemy e3 = new Enemy(2, 2, "Sneaky Beaver", new Image(new File("C:/res/sneakybeaver.png").toURI().toString()), 40);
        Attack[] a3 = new Attack[2];
        a3[0] = new Attack("Snipe", 10, false, 0.9);
        a3[1] = new Attack("Build Sniper Tower", 10, true, 1);
        e3.setAttacks(a3);
        enemies.add(e3);
        
        Enemy e4 = new Enemy(2, 2, "Justin Beaver", new Image(new File("C:/res/justinbeaver.png").toURI().toString()), 40);
        Attack[] a4 = new Attack[2];
        a4[0] = new Attack("Sing", 10, false, 0.9);
        a4[1] = new Attack("Call Fans", 10, true, 1);
        e4.setAttacks(a4);
        enemies.add(e4);
    }
    
    public static Enemy getEnemy(){
        double d = Math.random();
        int i = (int) (d * enemies.size());
        try {
            return (Enemy) enemies.get(i).clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(RandomEnemyList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
