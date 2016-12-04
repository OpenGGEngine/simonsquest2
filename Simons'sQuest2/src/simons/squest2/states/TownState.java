/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simons.squest2.states;

import java.io.File;
import java.util.HashMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import simons.squest2.GameVariables;

/**
 *
 * @author Warren
 */
public class TownState extends State {
    final Image pointeri = new Image(new File("C:/res/arrow.png").toURI().toString());
    final LinearGradient lg = new LinearGradient(0, 0, 1, 1, true, CycleMethod.REPEAT, new Stop(0.0, Color.STEELBLUE), new Stop(1.0, Color.SKYBLUE));
    
    public HashMap<String, Integer> sell = new HashMap<>();
    public static int firstmenupointer = 0;
    public static int secondmenupointer = 0;
    public static boolean isinfirstmenu = false;
    public static boolean isbuyingdoritos=false;
    public static boolean isinsecondmenu = false;
    public static boolean isinthirdmenu = false;
            
    public TownState(String name) {
        super(name);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(lg);
        gc.fillRect(0, 0, 1300, 950);
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 100, 1300, 3);
        gc.fillRect(0, 200, 1300, 3);

        gc.fillRect(300, 200, 3, 900);
        gc.setFont(new javafx.scene.text.Font("Comic Sans MS", 30));
        gc.fillText("Town: " + "Default", 50, 50);
        gc.fillText("Money: " + GameVariables.money+"$", 1050, 50);
        gc.setFont(new javafx.scene.text.Font("Comic Sans MS", 45));
        gc.fillText("Buy",100,300);
        gc.fillText("Sell",100,400);
        gc.fillText("Repair",100,500);
        gc.drawImage(pointeri, 50, 270 +((firstmenupointer)*100));
        if(isinfirstmenu){
            gc.fillText("Doritos: 10$",600,300);
            gc.fillText("MedKit: 30$",600,650);
            if(isbuyingdoritos){
                gc.drawImage(pointeri, 550, 300);
            }else{
                gc.drawImage(pointeri, 550, 600 );
            }
        }else if(isinsecondmenu){
            
        }
    }

    @Override
    public void update() {
        if(firstmenupointer < 0){
            firstmenupointer = 2;
        }
        if(firstmenupointer > 2){
            firstmenupointer = 0;
        }
    }

}
