/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simons.squest2.states;

import com.sun.prism.j2d.paint.MultipleGradientPaint;
import java.io.File;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import simons.squest2.Bar;
import simons.squest2.GameVariables;
import simons.squest2.Item;

/**
 *
 * @author Warren
 */
public class MenuState extends State {

    final Image simon = new Image(new File("C:/res/menupic.png").toURI().toString());
    final Image pointeri = new Image(new File("C:/res/arrow.png").toURI().toString());
    final LinearGradient lg = new LinearGradient(0, 0, 1, 1, true, CycleMethod.REPEAT, new Stop(0.0, Color.DODGERBLUE), new Stop(1.0, Color.SKYBLUE));
    Bar bar = new Bar(Color.GREEN, GameVariables.playermaxhealth, GameVariables.playerhealth, 560, 40, 650, 20);
    Bar wearbar = new Bar(Color.AQUA, 1, 1, 330, 240, 650, 20);
    public static int pointer = 0;
    public MenuState(String name) {
        super(name);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(lg);
        gc.fillRect(0, 0, 1300, 950);
        bar.render(gc);
        wearbar.render(gc);
        gc.fillRect(0, 0, 200, 200);
        gc.drawImage(simon, 0, 0, 200, 200);
        gc.setFont(new javafx.scene.text.Font("Comic Sans MS", 30));
        gc.setFill(Color.BLACK);
        gc.fillText("Health " + GameVariables.playerhealth + "/" + GameVariables.playermaxhealth, 250, 60);
        gc.fillText("Attack: " + GameVariables.attackpower, 250, 120);
        gc.fillText("Defense: " + GameVariables.attackpower, 450, 120);
        gc.fillText("Evasion: " + GameVariables.attackpower, 650, 120);
        
        gc.fillRect(0, 210, 1300, 3);
        gc.fillRect(0, 290, 1010, 3);
        gc.fillRect(1010, 210, 3, 900);
        gc.fillText("Item Wear: " + GameVariables.inventory.get(pointer).wear + "/" + GameVariables.inventory.get(pointer).wearmax, 20, 260);
        
        
        int xc = 0, yc = 0;
         gc.drawImage(pointeri, 10 + (pointer % 4) * 250, 320 + (pointer / 4) * 60);
        for (Item i : GameVariables.inventory) {
            
                gc.fillText(i.name + ": " + i.quantity , 50 + (xc % 4) * 230, 340 + (yc * 60));
                xc++;
                if ((xc % 4 == 0)) {
                    yc++;
                }
            
        }
    }

    @Override
    public void update() {
        if (pointer > GameVariables.inventory.size() - 1) {
            pointer = 0;
        } else if (pointer < 0) {
            pointer = GameVariables.inventory.size() - 1;
        }
        bar.setValue(GameVariables.playerhealth);
        wearbar.setMax(GameVariables.inventory.get(pointer).wearmax);
        wearbar.setValue(GameVariables.inventory.get(pointer).wear);
    }

}
