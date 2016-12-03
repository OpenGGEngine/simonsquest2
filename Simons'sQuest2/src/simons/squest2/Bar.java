/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simons.squest2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Warren
 */
public class Bar {

    public Bar(Color color, int max, int current, int posx, int posy, int width, int height) {
        this.color = color;
        this.max = max;
        this.current = current;
        this.posx = posx;
        this.posy = posy;
        this.width = width;
        this.height = height;
    }
    Color color;
    public int max, current;
    int posx,posy;
    int width,height;
    double fraction = 1.0;
    public void render(GraphicsContext gc){
      gc.setFill(Color.BLACK);
        gc.fillRect(posx, posy, width, height);
         gc.setFill(color);
        gc.fillRect(posx+3, posy+3, fraction, height-6);
        
    }
    public void setValue(int i){
        this.current = i;
        fraction = Math.round( width *((double) i /max)-6);
    //    System.out.println(fraction);
    }
    public void setMax(int max){
        this.max = max;
    }
}
