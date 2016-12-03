/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simons.squest2;

import java.io.File;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import simons.squest2.states.BattleState;
import simons.squest2.states.GameState;
import simons.squest2.states.MenuState;
import simons.squest2.states.StateMachine;
import simons.squest2.world.Attack;
import simons.squest2.world.Enemy;

/**
 *
 * @author Javier
 */
public class SimonsSQuest2 extends Application implements KeyboardListener{
    
    final Image beaversprite = new Image(new File("C:/res/beaver.png").toURI().toString());
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
        Item.add("Airhorn");
        Item.add("Airhorn");
        Item.add("Airhorn");
        Item.add("Airhorn");Item.add("Airhorn");
        Item.add("Mountain Dew");
        
        
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
        BattleState bs = new BattleState("BattleState");
        
        s.addState(bs);
        
        Enemy e = new Enemy(1, 1, "Donald Trump", new Image(new File("C:/res/trump.png").toURI().toString()), 125);
            Attack[] attacks = new Attack[4];
            attacks[0] = new Attack("Twitter War", 20, false, 0.6);//low prob
            attacks[1] = new Attack("Lie", 15, false, 0.8);
            attacks[2] = new Attack("Build a Wall", 20, true, 1);//heal
            attacks[3] = new Attack("Punch with small hands", 10, false, 0.95);//core attack
            e.setAttacks(attacks);
        bs.setEnemy(e);
        s.setState("GameState");
        
        //s.setState("GameState");
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
                if(s.currentState instanceof MenuState){
                    MenuState.pointer--;
                }else if(s.currentState instanceof BattleState){
                    BattleState.indexpointer--;
                }else{
                GameVariables.x -= speed;
                }
                break;
            case D:
                if(s.currentState instanceof MenuState){
                    MenuState.pointer++;
                }else if(s.currentState instanceof BattleState){
                    BattleState.indexpointer++;
                }else{
                GameVariables.x += speed;
                }
                break;
            case ENTER:
                if(s.currentState instanceof GameState){
                    s.setState("MenuState");
                }else if(s.currentState instanceof MenuState){
                    s.setState("GameState");
                }else if(s.currentState instanceof BattleState){
                    BattleState.selected = true;
                }
            break;
            case SPACE:
                if(s.currentState instanceof MenuState){
                    MenuState.selected = true;
                }
                break;
                
        }
    }

    @Override
    public void keyReleased(KeyCode k) {
        
    }
    
}
