/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simons.squest2.states;

import com.sun.prism.j2d.paint.MultipleGradientPaint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

/**
 *
 * @author Warren
 */
public class MenuState extends State{
    final LinearGradient lg = new LinearGradient(0,0,1,1,true,CycleMethod.REPEAT, new Stop(0.0,Color.BLUE),new Stop(1.0,Color.DODGERBLUE));
    public MenuState(String name) {
        super(name);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(lg);
        gc.fillRect(0, 0, 1300, 950);
        
    }

    @Override
    public void update() {
        
    }
    
}
