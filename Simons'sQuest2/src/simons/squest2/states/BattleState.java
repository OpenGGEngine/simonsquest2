/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simons.squest2.states;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import simons.squest2.Bar;
import simons.squest2.GameVariables;
import simons.squest2.GlobalInfo;
import simons.squest2.Item;
import simons.squest2.Item.ItemType;
import simons.squest2.SimonsSQuest2;
import static simons.squest2.states.MenuState.pointer;
import simons.squest2.world.Enemy;

/**
 *
 * @author Warren
 */
public class BattleState extends State{
    
    final LinearGradient lg = new LinearGradient(0, 0, 1, 1, true, CycleMethod.REPEAT, new Stop(0.0, Color.DODGERBLUE), new Stop(1.0, Color.LIGHTSTEELBLUE));
    Enemy e;
    public boolean myturn = true;
    boolean comp = true;
    public static int indexpointer = 0;
    final Image battlebackground = new Image(new File("C:/res/battlebackground.jpg").toURI().toString());
    final Image pointeri = new Image(new File("C:/res/arrow.png").toURI().toString());
    final Image simon = new Image(new File("C:/res/simon.png").toURI().toString());
    List<String> log = new LinkedList<>();
    Bar bar = new Bar(Color.GREEN, 100, 100, 240, 570, 650, 20);
   // Bar wearbar = new Bar(Color.AQUA, 100, 100, 315, 520, 650, 20);
    Bar enemybar = new Bar(Color.SALMON, 100, 100, 300, 610, 630, 20);
    boolean once = true;
    public static boolean selected = false;
    String s =" default";
    public BattleState(String name) {
        super(name);
    }
    public void setEnemy(Enemy e){
        this.e = e;
        enemybar.setMax(e.health);
    }
    @Override
    public void render(GraphicsContext gc) {
        int x = GlobalInfo.xres;
        int y = GlobalInfo.yres;
        gc.drawImage(battlebackground, 0, 0,x,y-400);
        gc.drawImage(simon, 0,0,300,y-400);
        gc.drawImage(e.image, 600,50,y-500,y-500);
        gc.setFill(Color.SKYBLUE);
        gc.fillRect(0, y-400, x, 400);
        gc.setFill(Color.BLACK);
        gc.setFont(new javafx.scene.text.Font("Comic Sans MS", 25));
        gc.fillText("Health " + GameVariables.playerhealth + "/" + GameVariables.playermaxhealth, 20, y-360);
        gc.fillText("Enemy Health " + e.health + "/" + enemybar.max, 20, y-320);
           
        if(myturn){
            
            gc.fillRect(0, y-300, 1000,2 );
            gc.fillRect(1000, y-400, 2,400 );
            
            int xc = 0, yc = 0;
            for (Item i : Item.items) {
                gc.fillText(i.name, 40 + (xc % 4) * 250, 690 + (yc * 60));
                xc++;
                if ((xc % 4 == 0)) {
                    yc++;
                }
            }
            gc.drawImage(pointeri, 10 + (indexpointer % 4) * 250, 670 + (indexpointer / 4) * 60);
            if(selected){
                Item i = Item.items.get(indexpointer);
                i.use();
                myturn = false;
                if(i.type == ItemType.ITEM){
                    GameVariables.playerhealth = Math.min(GameVariables.playermaxhealth, GameVariables.playerhealth + i.attackpower);
                }else{
                    s = e.damage(i.attackpower);
                }
                if(i.wear == 0){
                    Item.items.remove(i);
                }
                selected = false;
            }
            //gc.drawImage(GameState.arrow, 40 + (indexpointer % 4) * 250, 700 + (indexpointer / 4) * 60);
            //gc.fillText("Weapon Wear: " + items.get(indexpointer).wear + "/" + items.get(indexpointer).maxwear, 40, 670);
            //wearbar.render(gc);
        }else{
            gc.setFont(new javafx.scene.text.Font("Comic Sans MS", 45));
            gc.fillRect(0, y-300, 1300,2 ); 
            
            if(e.alive){
                if(comp){
                    gc.fillText(s, 30, 800);
                    if(selected){
                        selected = false;
                        comp = false;
                    }
                }else{
                    if(once){
                        s = e.attack();
                        once = false;
                    }else{
                        gc.fillText(s, 30, 800);
                        if(selected){
                            selected = false;
                            myturn = true;
                        }
                    }
                 }
            }else{
                gc.fillText(s, 30, 800);
                if(selected){
                    selected = false;
                    SimonsSQuest2.s.setState("GameState");
                }
            }
        }
        
        bar.render(gc);
        enemybar.render(gc);
    }

    @Override
    public void update() {
        if (indexpointer > Item.items.size() - 1) {
            indexpointer = 0;
        } else if (indexpointer < 0) {
            indexpointer = Item.items.size() - 1;
        }
        //log.add(e.attack());
        bar.setValue(GameVariables.playerhealth);
        bar.setMax(GameVariables.playermaxhealth);
        enemybar.setValue(e.health);
    }
    
}
