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

    public static int speed = 30;
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
        
        GlobalInfo.xres = screenwidth;
        GlobalInfo.yres = screenheight;
        
        GameVariables.inventory.add(new Item("Negev", 50, 8, 0,1));
        GameVariables.inventory.add(new Item("Dagger", 3, 1, 0,1));
        GameVariables.inventory.add(new Item("Sword", 8, 2, 0,1));
        GameVariables.inventory.add(new Item("Glock18", 20, 6, 0,1));
        GameVariables.inventory.add(new Item("Mace", 10, 4, 0,1));
        GameVariables.inventory.add(new Item("Bow and Arrow", 9, 3, 0,1));
        GameVariables.inventory.add(new Item("Airhorn", 1000, 12, 0,1));
        GameVariables.inventory.add(new Item("Mountain Dew", 0, 0, 20,1));
        GameVariables.inventory.add(new Item("Dewritos", 0, 0, 20,1));
        GameVariables.inventory.add(new Item("Deagle", 11, 5, 0,1));
    }
    public void loop(GraphicsContext gc){
        s.update();
        s.render(gc);
    }

    @Override
    public void keyPressed(KeyCode k) {
        switch(k){
            case W:
                GameVariables.y -= speed;
                break;
            case S:
                GameVariables.y += speed;
                break;
            case A:
                GameVariables.x -= speed;
                break;
            case D:
                GameVariables.x += speed;
                break;
            case ENTER:
                if(s.currentState instanceof GameState){
                    s.setState("MenuState");
                }else if(s.currentState instanceof MenuState){
                    s.setState("GameState");
                }
                
        }
    }

    @Override
    public void keyReleased(KeyCode k) {
        
    }
    
}
