package com.example.lab2;

public class Item {
    private String imageUrl;
    private String name;

    public Item(String imageUrl, String name) {
        this.imageUrl = imageUrl;
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }
}
