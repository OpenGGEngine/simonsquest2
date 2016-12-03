/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simons.squest2.world;

/**
 *
 * @author Warren
 */
    public class Attack{

        public Attack(String name, double damage, boolean heal, double chanceToHit) {
            this.name = name;
            this.damage = damage;
            this.heal = heal;
            this.chanceToHit = chanceToHit;
        }
        String name;
        double damage;
        boolean heal;
        double chanceToHit;
       
    }
