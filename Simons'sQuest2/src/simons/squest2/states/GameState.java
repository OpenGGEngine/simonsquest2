/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simons.squest2.states;

import java.io.File;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import simons.squest2.GameVariables;
import simons.squest2.GlobalInfo;
import simons.squest2.world.Town;
import simons.squest2.world.World;
import simons.squest2.world.Enemy;

/**
 *
 * @author Warren
 */
public class GameState extends State {

    //triggered
    World w;

    final Image playersprite = new Image(new File("C:/res/menupic.png").toURI().toString());
    final int tilesize = 32;

    public GameState(String name) {
        super(name);
        GlobalInfo.w = w = new World();
    }

    @Override
    public void render(GraphicsContext gc) {
        int xmax = (tilesize * w.map.length) - GlobalInfo.xres;
        int ymax = (tilesize * w.map.length) - GlobalInfo.yres;
        int screenX = 650, screenY = 475;
        int camX = GameVariables.x - screenX;
        int camY = GameVariables.y - screenY;

        camX = Math.max(0, Math.min(camX, xmax));
        camY = Math.max(0, Math.min(camY, ymax));

        if (GameVariables.x < screenX
                || GameVariables.x > xmax + screenX) {
            screenX = GameVariables.x - camX;
        }
        // top and bottom sides
        if (GameVariables.y < screenY
                || GameVariables.y > ymax+ screenY) {
            screenY = GameVariables.y - camY;
        }

   
        int xa = camX;
        int ya = camY;

        //int xa = Math.max(0, Math.min(1900,GameVariables.x));
        //int ya = Math.max(0, Math.min(2250, GameVariables.y));
        /*
         int startCol = (x / 32);
         int endCol = startCol + (1000 / 31);
         int startRow = (y / 32);
         int endRow = startRow + (32);
         int offsetX = -x + startCol * 32;
         int offsetY = -y + startRow * 32;
         */
        int startCol = xa / tilesize;
        int endCol = Math.min(startCol + (GlobalInfo.xres / (tilesize - 1)),249);
        int startRow = ya / tilesize;
        int endRow = Math.min(startRow + (GlobalInfo.yres / tilesize + 1),249);

        int offsetX = -xa + startCol * tilesize;
        int offsetY = -ya + startRow * tilesize;

        for (int c = startCol; c <= endCol; c++) {
            for (int r = startRow; r <= endRow; r++) {
                int tile = w.map[c][r].type;
                int x = (c - startCol) * tilesize + offsetX;
                int y = (r - startRow) * tilesize + offsetY;

                if (w.map[c][r].getFeature() instanceof Town) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(x, y, tilesize, tilesize);
                    continue;
                }

                if (w.map[c][r].getFeature() instanceof Enemy) {
                    gc.setFill(Color.RED);
                    gc.fillRect(x, y, tilesize, tilesize);
                    continue;
                }

                switch (tile) {
                    case 0: //water
                        gc.setFill(Color.BLUE);
                        break;
                    case 1: //Sand
                        gc.setFill(Color.BLANCHEDALMOND);
                        break;
                    case 2: //Grass
                        gc.setFill(Color.YELLOWGREEN);
                        break;
                    case 3: //Forest
                        gc.setFill(Color.GREEN);
                        break;
                    case 4: //water
                        gc.setFill(Color.DARKGRAY);
                        break;
                    case 5: //water
                        gc.setFill(Color.WHITE);
                        break;
                    default:
                        gc.setFill(Color.CRIMSON);
                        break;
                }
                gc.fillRect(x, y, tilesize, tilesize);

            }
        }
        gc.drawImage(playersprite, screenX, screenY);
    }

    @Override
    public void update() {

    }

}
