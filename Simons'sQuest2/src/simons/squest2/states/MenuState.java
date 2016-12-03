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
import simons.squest2.Item.ItemType;

/**
 *
 * @author Warren
 */
public class MenuState extends State {

    final Image simon = new Image(new File("C:/res/menupicture.png").toURI().toString());
    final Image pointeri = new Image(new File("C:/res/arrow.png").toURI().toString());
    final LinearGradient lg = new LinearGradient(0, 0, 1, 1, true, CycleMethod.REPEAT, new Stop(0.0, Color.DODGERBLUE), new Stop(1.0, Color.SKYBLUE));
    Bar bar = new Bar(Color.GREEN, GameVariables.playermaxhealth, GameVariables.playerhealth, 560, 40, 650, 20);
    Bar wearbar = new Bar(Color.AQUA, 1, 1, 330, 240, 650, 20);
    public static int pointer = 0;
    public static boolean selected = false;
    
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
        gc.fillText("Item Wear: " + Item.items.get(pointer).wear + "/" + Item.items.get(pointer).wearmax, 20, 260);
        
        
        int xc = 0, yc = 0;
         gc.drawImage(pointeri, 30 + (pointer % 3) * 280, 320 + (pointer / 3) * 60);
        for (Item i : Item.items) {
            
                gc.fillText(i.name  , 60 + (xc % 3) * 280, 340 + (yc * 60));
                xc++;
                if ((xc % 3 == 0)) {
                    yc++;
                }
            
        }
        if(selected){
              Item i = Item.items.get(pointer);
              if(i.type == ItemType.ITEM){
                  i.use();
                  GameVariables.playerhealth = Math.min(GameVariables.playermaxhealth, GameVariables.playerhealth + i.attackpower);
                  if(i.wear == 0){
                      Item.items.remove(i);
                  }
              }
              selected = false;
        }
    }

    @Override
    public void update() {
        if (pointer > Item.items.size() - 1) {
            pointer = 0;
        } else if (pointer < 0) {
            pointer = Item.items.size() - 1;
        }
        bar.setValue(GameVariables.playerhealth);
        wearbar.setMax(Item.items.get(pointer).wearmax);
        wearbar.setValue(Item.items.get(pointer).wear);
        
    }
    
}


