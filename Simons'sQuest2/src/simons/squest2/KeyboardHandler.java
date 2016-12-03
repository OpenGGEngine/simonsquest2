/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simons.squest2;

import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Javier
 */
public class KeyboardHandler {
    public static List<KeyboardListener> listeners = new ArrayList<>();
    static EventHandler<KeyEvent> handler;
    
    
    public static EventHandler<KeyEvent> getHandler(){
        
        if(handler != null) return handler;
        
        handler = (KeyEvent event) -> {
            for(KeyboardListener l : listeners){
                l.keyPressed(event.getCode());
            }
        };
        return handler;
    }
    
    public static void subscribe(KeyboardListener listener){
        listeners.add(listener);
    }
}
