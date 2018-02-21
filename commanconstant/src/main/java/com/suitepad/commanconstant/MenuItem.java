package com.suitepad.commanconstant;

/**
 * Created by EslamHussein on 2/21/18.
 */

public class MenuItem {

    private String UUID;
    private String name;
    private int price;
    private String type;


    public MenuItem(String UUID, String name, int price, String type) {
        this.UUID = UUID;
        this.name = name;
        this.price = price;
        this.type = type;
    }


    public String getUUID() {
        return UUID;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }
}
