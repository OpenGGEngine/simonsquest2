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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import simons.squest2.states.StateMachine;

/**
 *
 * @author Javier
 */
public class SimonsSQuest2 extends Application{
    
    public static final int screenwidth = 1280, screenheight = 720;
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
        
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                gc.clearRect(0, 0, screenwidth, 900);
            }
        }.start();
        
    }
    public void loop(GraphicsContext gc){
        s.update();
        s.render(gc);
    }
    
}
