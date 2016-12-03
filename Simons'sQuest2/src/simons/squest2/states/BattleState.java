/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simons.squest2.states;

import java.io.File;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Warren
 */
public class BattleState extends State{
    
    public boolean myturn = true;
    final Image battlebackground = new Image(new File("C:/res/battlebackground.jpg").toURI().toString());
    final Image simon = new Image(new File("C:/res/simon.png").toURI().toString());
    
    
    public BattleState(String name) {
        super(name);
        
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(battlebackground, 0, 0,1300,600);
        gc.drawImage(simon, 0,0,323,600);
    }

    @Override
    public void update() {
        
    }
    
}
