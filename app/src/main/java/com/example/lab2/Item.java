package com.example.lab2;

import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

// указываем к какой БД принадлежит таблица
// allFields = true для того чтобы возле каждого поля не указавать аннотацию @Column
@Table(database = AppDatabase.class, allFields = true)
public class Item extends BaseModel {
    @PrimaryKey(autoincrement = true)
    long id;

    private String image;
    private String name;

    // пустой конструктор нужен для либы
    public Item() {
    }

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

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }
}
