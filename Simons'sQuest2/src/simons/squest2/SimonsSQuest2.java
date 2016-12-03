/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simons.squest2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import simons.squest2.states.GameState;
import simons.squest2.states.MenuState;
import simons.squest2.states.StateMachine;

/**
 *
 * @author Javier
 */
public class SimonsSQuest2 extends Application implements KeyboardListener{

    public static final int screenwidth = 1300, screenheight = 950;
    final Canvas screen = new Canvas(screenwidth, screenheight);
    final GraphicsContext gc = screen.getGraphicsContext2D();
    
    public static StateMachine s;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        

        primaryStage.initStyle(StageStyle.DECORATED);

        Group root = new Group();
        Scene scene = new Scene(root, screenwidth, screenheight);

        // gc.fillRect(75,75,100,100);
        root.getChildren().add(screen);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        s = new StateMachine();
        
        MenuState ms = new MenuState("MenuState");
        s.addState(ms);
        GameState gs = new GameState("GameState");
        s.addState(gs);
        s.setState("GameState");
        scene.setOnKeyPressed(KeyboardHandler.getHandler());
        KeyboardHandler.subscribe(this);
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                gc.clearRect(0, 0, screenwidth, screenheight);
                loop(gc);
            }
        }.start();
        
    }
    public void loop(GraphicsContext gc){
        s.update();
        s.render(gc);
    }

    @Override
    public void keyPressed(KeyCode k) {
        
    }

    @Override
    public void keyReleased(KeyCode k) {
        
    }
    
}
