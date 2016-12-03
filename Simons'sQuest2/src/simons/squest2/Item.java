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
    public static final int 
            WEAPON = 5,
            ITEM = 6;
    static{
        //items.add(new Item());
    }
    public Item(String name, int attackpoer, int quantity, int type) {
        this.name = name;
        this.attackpoer = attackpoer;
        this.quantity = quantity;
    }
    public static void add(String item){
        items.stream().filter((i) -> (i.name.equals(item))).forEach((i) -> {
            i.quantity++;
        });
    }
    public String name;
    public int attackpoer;
    public int quantity;
    
    public ItemType type;
}
