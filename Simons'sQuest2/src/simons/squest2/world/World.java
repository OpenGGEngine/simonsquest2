/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simons.squest2.world;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Javier
 */
public class World {
    List<Enemy> entities = new ArrayList<>();
    
    public static final int WORLDSIZE = 250;
    
    public Tile[][] map = new Tile[WORLDSIZE][WORLDSIZE];
    
    public World(){
        double[][] rmap = generateMap();
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                map[i][j] = new Tile(rmap[i][j]);
                if(map[i][j].type == Tile.GRASS){
                    if(Math.random() > 0.998){
                        map[i][j].setFeature(new Town());
                    }
                }
            }
        }
        
        
        
        for(Tile[] x : map){
            for(Tile y : x){
                System.out.print(y + " ");
            }
            System.out.println("");
        }
        
        System.out.println("Generator finished");
    }
    double[][] generateMap(){
        RandomGen simplexNoise=new RandomGen(6,0.1,(int) (Math.random() * 1000));

        double xStart=0;
        double XEnd=WORLDSIZE;
        double yStart=0;
        double yEnd=WORLDSIZE;

        int xResolution= WORLDSIZE;
        int yResolution= WORLDSIZE;

        double[][] result=new double[xResolution][yResolution];

        for(int i=0;i<xResolution;i++){
            for(int j=0;j<yResolution;j++){
                int x=(int)(xStart+i*((XEnd-xStart)/xResolution));
                int y=(int)(yStart+j*((yEnd-yStart)/yResolution));
                result[i][j]=simplexNoise.getNoise(x,y);
            }
        }
        return result;
   }
   public void render(GraphicsContext gc){
       
   }

}
