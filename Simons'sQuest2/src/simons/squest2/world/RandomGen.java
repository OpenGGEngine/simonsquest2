/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simons.squest2.world;

import java.util.Random;

/**
 *
 * @author Javier
 */
public class RandomGen {
    
    double persistence;
    int seed;
    RandomGenOctave[] octaves;
    double[] frequencys;
    double[] amplitudes;
    public RandomGen(int octavecount,double persistence, int seed){
        this.persistence=persistence;
        this.seed=seed;

        int numberOfOctaves=octavecount;

        octaves=new RandomGenOctave[numberOfOctaves];
        frequencys=new double[numberOfOctaves];
        amplitudes=new double[numberOfOctaves];

        Random rnd=new Random(seed);

        for(int i=0;i<numberOfOctaves;i++){
            octaves[i]=new RandomGenOctave(rnd.nextInt());

            frequencys[i] = Math.pow(2,i);
            amplitudes[i] = Math.pow(persistence,octaves.length-i);
        }

    }
    
    public double getNoise(int x, int y){

        double result=0;
        
        for(int i=0;i<octaves.length;i++){
          double frequency = Math.pow(2,i);
          double amplitude = Math.pow(persistence,octaves.length-i);

          result=result+octaves[i].noise(x/frequencys[i], y/frequencys[i])* amplitudes[i];
        }
        result = result * 10;

        return result;

    }

}
