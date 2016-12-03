/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simons.squest2.world;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import simons.squest2.Vector2i;

/**
 *
 * @author Javier
 */
public class World {
    List<Enemy> entities = new ArrayList<>();
    
    public static final int WORLDSIZE = 250;
    
    public Tile[][] map = new Tile[WORLDSIZE][WORLDSIZE];
    
    public Tile getTile(Vector2i v){
        return getTile(v.x,v.y);
    }
    
    public Tile getTile(int x, int y){
        return map[x][y];
    }
    
    public World(){
        double[][] rmap = generateMap();
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                map[i][j] = new Tile(rmap[i][j]);
                if(map[i][j].type == Tile.GRASS){
                    if(Math.random() > 0.999){
                        map[i][j].setFeature(new Town());
                    }
                }
            }
        }
        
        int xr;
        int yr;
        boolean fin = false;
        for(int i = 0; i < 5; i++){
            fin = false;
            do{
                
                xr = (int)(Math.random() * (WORLDSIZE-10))+5;
                yr = (int)(Math.random() * (WORLDSIZE-10))+5;
                if(map[xr][yr].type == Tile.FOREST && map[xr][yr].f == null){
                    Enemy e;
                    switch(i){
                        case 0:
                            e = new Enemy(xr, yr, "Donald Trump", new Image(new File("C:/res/trump.png").toURI().toString()), 125);
                            Attack[] attacks = new Attack[4];
                            attacks[0] = new Attack("Twitter War", 20, false, 0.6);//low prob
                            attacks[1] = new Attack("Lie", 15, false, 0.8);//strong attack
                            attacks[2] = new Attack("Build a Wall", 20, true, 1);//heal
                            attacks[3] = new Attack("Punch with small hands", 10, false, 0.95);//core attack
                            e.setAttacks(attacks);
                            break;
                        case 1:
                            e = new Enemy(xr, yr, "Hillary Clinton", new Image(new File("C:/res/clinton.png").toURI().toString()), 100);
                            Attack[] attacks2 = new Attack[4];
                            attacks2[0] = new Attack("Shatter Glass Ceiling", 15, false, 0.9);//core attack
                            attacks2[1] = new Attack("Delete Email", 15, true, 1);//heal
                            attacks2[2] = new Attack("Spread Pneumonia", 25, false, 0.8);//strong attack
                            attacks2[3] = new Attack("Rig Battle", 50, false, 0.1);//low prob
                            e.setAttacks(attacks2);
                            break;
                        case 2:
                            e = new Enemy(xr, yr, "Anita Sarkeesian", new Image(new File("C:/res/anita.png").toURI().toString()), 150);
                            Attack[] attacks3 = new Attack[4];
                            attacks3[0] = new Attack("Enter Safe Space", 25, true, 1);//heal
                            attacks3[1] = new Attack("Remove Privilege", 20, false, 0.95);//core attack
                            attacks3[2] = new Attack("Trigger", 25, false, 0.8);//strong attack
                            attacks3[3] = new Attack("Rage on YouTube", 30, false, 0.5);//low prob
                            e.setAttacks(attacks3);
                            break;
                        case 3:
                            e = new Enemy(xr, yr, "Stalin", new Image(new File("C:/res/stalin.png").toURI().toString()), 250);
                            Attack[] attacks4 = new Attack[4];
                            attacks4[0] = new Attack("Seize means of production", 150, true, 0.2);//super heal
                            attacks4[1] = new Attack("Create camp", 35, true, 1);//heal
                            attacks4[2] = new Attack("Convert to communism", 30, false, 0.95);//core attack
                            attacks4[3] = new Attack("Fire nuclear missile", 50, false, 0.3);//low prob
                            e.setAttacks(attacks4);
                            break;
                        default:
                            e = new Enemy(xr, yr, "Robbie Rotten", new Image(new File("C:/res/numberone.png").toURI().toString()), 200);
                            Attack[] attacks5 = new Attack[4];
                            attacks5[0] = new Attack("Throw Net", 30, false, 0.95);//core attack
                            attacks5[1] = new Attack("Sneak Around", 30, true, 1);//heal
                            attacks5[2] = new Attack("Catch a good guy, like a real superhero", 50, false, 0.3);//low prob
                            attacks5[3] = new Attack("Slip and slide on banana peel", 35, false, 0.8);//strong attack
                            e.setAttacks(attacks5);
                            break;
                    }
                    map[xr][yr].setFeature(e);
                    System.out.println(e.enemyType);
                    System.out.println(((Enemy) map[xr][yr].f).enemyType);
                    fin = true;
                }
            }while(!fin);
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
