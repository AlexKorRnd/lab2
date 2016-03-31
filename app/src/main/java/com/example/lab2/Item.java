package com.example.lab2;


import java.io.Serializable;

import io.realm.annotations.PrimaryKey;


public class Item  implements Serializable{
    @PrimaryKey
    private String image;
    private String name;

    public Item() {
    }

    public Item(String imageUrl, String name) {
        super();
        this.image = imageUrl;
        this.name = name;
    }


    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }
}
