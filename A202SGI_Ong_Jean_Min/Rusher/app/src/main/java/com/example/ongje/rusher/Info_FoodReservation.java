package com.example.ongje.rusher;

public class Info_FoodReservation {

    // Food ordering info
    private String name;
    private int imageMenu;
    private String price;

    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageMenu() {
        return imageMenu;
    }

    public void setImageMenu(int imageMenu) {
        this.imageMenu = imageMenu;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Info_FoodReservation(String name, int imageMenu, String price) {
        this.name = name;
        this.imageMenu = imageMenu;
        this.price = price;
    }
}
