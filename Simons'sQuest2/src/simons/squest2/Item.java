/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simons.squest2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Warren
 */
public class Item {
    static List<Item> items = new ArrayList<>();
    static{
        //items.add(new Item());
    }
    public Item(String name, int attackpoer, int wear, int maxwear,int level,int heallevel,int quantity) {
        this.name = name;
        this.attackpoer = attackpoer;
        this.wear = wear;
        this.maxwear = maxwear;
        this.level = level;
        this.heallevel = heallevel;
        this.quantity = quantity;
    }
    public static void add(String item){
        items.stream().filter((i) -> (i.name.equals(item))).forEach((i) -> {
            i.quantity++;
        });
    }
    public String name;
    public int attackpoer;
    public int wear;
    public int maxwear;
    public int level;
    public int heallevel;
    public int quantity;
    
    public ItemType type;
}
