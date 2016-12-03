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

/**
 *
 * @author Warren
 */
public class MenuState extends State{
    final Image simon = new Image(new File("C:/res/menupic.png").toURI().toString());
    final LinearGradient lg = new LinearGradient(0,0,1,1,true,CycleMethod.REPEAT, new Stop(0.0,Color.BLUE),new Stop(1.0,Color.DODGERBLUE));
    Bar bar = new Bar(Color.GREEN, GameVariables.playermaxhealth, GameVariables.playerhealth, 315, 610, 650, 20);
    public MenuState(String name) {
        super(name);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(lg);
        gc.fillRect(0, 0, 1300, 950);
        bar.render(gc);
        gc.fillRect(0, 0, 200, 200);
        gc.drawImage(simon, 0, 0,200,200);
        
    }

    @Override
    public void update() {
        bar.setValue(GameVariables.playerhealth);
    }
    
}
