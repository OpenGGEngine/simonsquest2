/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simons.squest2.states;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Warren
 */
public abstract class State {
    String name;

    public State(String name) {
        this.name = name;
    }
    public abstract void render(GraphicsContext gc);
    public abstract void update();
}
