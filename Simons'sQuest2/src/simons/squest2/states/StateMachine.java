/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simons.squest2.states;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Warren
 */
public class StateMachine {

    public Map<String, State> states = new HashMap<>();
    public State currentState;

    public void addState(State state) {

        states.put(state.name, state);

    }

    public void setState(String name) {
        currentState = states.get(name);
    }

    public void render(GraphicsContext gc) {
        currentState.render(gc);
    }

    public void update() {
        currentState.update();
    }

}
