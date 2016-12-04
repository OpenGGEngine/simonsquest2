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
import simons.squest2.states.TownState;
import simons.squest2.world.Attack;
import simons.squest2.world.Enemy;

/**
 *
 * @author Javier
 */
public class SimonsSQuest2 extends Application implements KeyboardListener {

    final Image beaversprite = new Image(new File("C:/res/beaver.png").toURI().toString());
    public static int speed = 32;
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
        Item.add("Glock-18");
        Item.add("Mountain Dew");
        Item.add("Glock-18");
        Item.add("Mountain Dew");
        Item.add("Glock-18");
        Item.add("Mountain Dew");
        Item.add("Glock-18");
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
        TownState ts = new TownState("TownState");
        s.addState(ts);
        s.setState("TownState");

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

    public void loop(GraphicsContext gc) {
        s.update();
        s.render(gc);
    }

    @Override
    public void keyPressed(KeyCode k) {
        switch (k) {
            case W:
                if (s.currentState instanceof MenuState) {
                    MenuState.pointer -= 3;
                } else if (s.currentState instanceof BattleState) {
                    BattleState.indexpointer -= 4;
                } else if (s.currentState instanceof TownState) {
                    if (TownState.isinfirstmenu) {
                        TownState.isbuyingdoritos = !TownState.isbuyingdoritos;
                    } else if (TownState.isinsecondmenu) {

                    } else if (TownState.isinthirdmenu) {

                    } else {
                        TownState.firstmenupointer--;
                    }
                } else {
                    GameVariables.y -= speed;
                    if (!GameState.onMove()) {
                        GameVariables.y += speed;
                    }
                }
                break;
            case S:
                if (s.currentState instanceof MenuState) {
                    MenuState.pointer += 3;
                } else if (s.currentState instanceof BattleState) {
                    BattleState.indexpointer += 4;
                } else if (s.currentState instanceof TownState) {
                    if (TownState.isinfirstmenu) {
                        TownState.isbuyingdoritos = !TownState.isbuyingdoritos;
                    } else if (TownState.isinsecondmenu) {

                    } else if (TownState.isinthirdmenu) {

                    } else {
                        TownState.firstmenupointer++;
                    }
                } else {
                    GameVariables.y += speed;
                    if (!GameState.onMove()) {
                        GameVariables.y -= speed;
                    }
                }
                break;
            case A:
                if (s.currentState instanceof MenuState) {
                    MenuState.pointer--;
                } else if (s.currentState instanceof BattleState) {
                    BattleState.indexpointer--;
                } else if (s.currentState instanceof TownState) {
                    TownState.secondmenupointer--;
                } else {
                    GameVariables.x -= speed;
                    if (!GameState.onMove()) {
                        GameVariables.x += speed;
                    }
                }
                break;
            case D:
                if (s.currentState instanceof MenuState) {
                    MenuState.pointer++;
                } else if (s.currentState instanceof BattleState) {
                    BattleState.indexpointer++;
                } else if (s.currentState instanceof TownState) {
                    TownState.secondmenupointer++;
                } else {
                    GameVariables.x += speed;
                    if (!GameState.onMove()) {
                        GameVariables.x -= speed;
                    }
                }
                break;
            case ESCAPE:
                if (s.currentState instanceof GameState) {
                    s.setState("MenuState");
                } else if (s.currentState instanceof MenuState) {
                    s.setState("GameState");
                } else if (s.currentState instanceof TownState) {
                    if (TownState.isinfirstmenu || TownState.isinsecondmenu || TownState.isinthirdmenu) {
                        TownState.isinfirstmenu = false;
                        TownState.isinsecondmenu = false;
                        TownState.isinthirdmenu = false;
                    }
                } else if (s.currentState instanceof TownState) {
                    if (TownState.isinfirstmenu) {
                        TownState.isinfirstmenu = false;
                    }
                }
                break;
            case ENTER:
                if (s.currentState instanceof BattleState) {
                    BattleState.selected = true;
                } else if (s.currentState instanceof TownState) {
                    if (TownState.isinfirstmenu) {
                        if (TownState.isbuyingdoritos) {
                            if (GameVariables.money >= 10) {
                                GameVariables.money -= 10;
                                Item.add("Doritos");
                                TownState.isinfirstmenu = false;
                            }
                        } else {
                            if (GameVariables.money >= 30) {
                                GameVariables.money -= 30;
                                Item.add("Medkit");
                                TownState.isinfirstmenu = false;
                            }
                        }

                    } else if (TownState.isinsecondmenu) {
                        Item m = Item.items.get(TownState.secondmenupointer);
                        GameVariables.money += m.price;
                        Item.items.remove(m);
                        TownState.isinsecondmenu = false;
                    } else if(TownState.isinthirdmenu){
                        Item m = Item.items.get(TownState.secondmenupointer);
                        if(GameVariables.money >= m.price){
                            m.wear = m.wearmax;
                            GameVariables.money -=m.price;
                        }
                    }else {
                        if (TownState.firstmenupointer == 0) {
                            TownState.isinfirstmenu = true;
                        }
                        if (TownState.firstmenupointer == 1) {
                            if(Item.items.size() != 0){
                            TownState.isinsecondmenu = true;
                            }
                        }
                        if (TownState.firstmenupointer == 2) {
                            TownState.isinthirdmenu = true;
                        }
                    }
                }
                break;
            case SPACE:
                if (s.currentState instanceof MenuState) {
                    MenuState.selected = true;
                }
                break;

        }
    }

    @Override
    public void keyReleased(KeyCode k) {

    }

}
