package com.example.lab2;

public class Item {
    private String image;
    private String name;

    public Item(String imageUrl, String name) {
        this.image = imageUrl;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
