/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simons.squest2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Warren
 */
public class Item implements Cloneable{
    private static final ArrayList<Item> itemLookup = new ArrayList<>();
    public static final ArrayList<Item> items = new ArrayList<>();
    public static Random hitChance = new Random();
    public int wear;
    public int wearmax;
    public int prob;
    public String name;
    public int attackpower;
    public ItemType type;
    static{
        itemLookup.add(new Item("Airhorn",100,ItemType.WEAPON,-1,100));
        itemLookup.add(new Item("Negev",50,ItemType.WEAPON,55,60));
        itemLookup.add(new Item("Glock-18",10,ItemType.WEAPON,20,85));
        itemLookup.add(new Item("Mace",25,ItemType.WEAPON,40,72));
        itemLookup.add(new Item("Bow and Arrow",16,ItemType.WEAPON,30,50));
        itemLookup.add(new Item("Sword",20,ItemType.WEAPON,40,80));
        itemLookup.add(new Item("Dagger",15,ItemType.WEAPON,40,60));
        itemLookup.add(new Item("Mountain Dew",15,ItemType.ITEM,1,100));
        itemLookup.add(new Item("Doritos",35,ItemType.ITEM,1,100));
        itemLookup.add(new Item("Medkit",15,ItemType.ITEM,1,100));
        
    }
    public Item(String name, int attackpower, ItemType type,int wearmax,int prob) {
        this.name = name;
        this.attackpower = attackpower;
        this.wear = wearmax;
        this.type = type;
        this.wearmax = wearmax;
        this.prob = prob;
    }
    private static Item getTemplate(String item) {
        for (Item i: itemLookup) {
            if (i.name.equals(item))
                return i;
        }
        return null;
    }
    public static void add(String item){
        Item i = getTemplate(item);
        if (i == null)
            throw new IllegalArgumentException(item + " is not valid item name");
        items.add(i);
    }
    
    public static int getQuantity(String item) {
        int quantity = 0;
        for(Item i: items) {
            if (i.name.equals(item))
                quantity++;
        }
        return quantity;
    }
    
    public int use(){
        if (wear == 0) {
            return -1;
        }
        if (hitChance.nextInt()%100 < prob)
            wear--;
        return attackpower;
    }
    
    public enum ItemType {
        ITEM(5),
        WEAPON(6),
        ARMOR(7);
        int num;
        private ItemType(int num) {
            this.num = num;
        }
        public int getNum() {
            return num;
        }
    }
}
