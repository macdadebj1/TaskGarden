package com.garden.taskgarden;


/**
 * Class to represent an item in the shop.
 * If you don't supply a resource name to the constructor, will just use the name of the item
 * as a resource name.
 *
 * @author Blake MacDade
 * */
public class ShopItem {

    private String Name;
    private String Description;
    private int Price;
    private String ResourceName;

    public ShopItem(String name, String description, int price, String resourceName){
        this.Name = name;
        this.Description = description;
        this.Price = price;
        this.ResourceName = resourceName;
    }

    public ShopItem(String name, String description, int price){
        this.Name = name;
        this.Description = description;
        this.Price = price;
        this.ResourceName = name;
    }

    public String getName(){
        return this.Name;
    }

    public String getDescription(){
        return this.Description;
    }
    public int getPrice(){
        return this.Price;
    }

    public String getResourceName(){
        return this.ResourceName;
    }

    public void updateName(String name){
        this.Name = name;
    }

    public void updateDescription(String description){
        this.Description = description;
    }

    public void updatePrice(int price){
        this.Price = price;
    }

    public void updateResourceName(String resourceName){
        this.ResourceName = resourceName;
    }

}
