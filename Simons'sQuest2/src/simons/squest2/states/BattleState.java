/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simons.squest2.states;

import java.io.File;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import simons.squest2.GlobalInfo;
import simons.squest2.world.Enemy;

/**
 *
 * @author Warren
 */
public class BattleState extends State{
    
    Enemy e;
    public boolean myturn = true;
    final Image battlebackground = new Image(new File("C:/res/battlebackground.jpg").toURI().toString());
    final Image simon = new Image(new File("C:/res/simon.png").toURI().toString());
    
    
    public BattleState(String name, Enemy e) {
        super(name);
        this.e = e;
    }

    @Override
    public void render(GraphicsContext gc) {
        int x = GlobalInfo.xres;
        int y = GlobalInfo.yres;
        gc.drawImage(battlebackground, 0, 0, x, y);
        gc.drawImage(simon, 0,0,x/4,y-(y/3*2));
        //gc.drawImage(e.image, x/3*2, y/3*2, x, y);\

    }

    @Override
    public void update() {
        
    }
    
}
