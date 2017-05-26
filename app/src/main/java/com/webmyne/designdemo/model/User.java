package com.webmyne.designdemo.model;

import java.io.Serializable;

/**
 * Created by chiragpatel on 22-05-2017.
 */

public class User implements Serializable {

    private int id;
    private int image;
    private String name;
    private String itemName;
    private String desc;
    private String time;
    private boolean editable;

    public User(int id, int image, String name, String itemName, String desc, String time) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.itemName = itemName;
        this.desc = desc;
        this.time = time;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "User{" +
                "image=" + image +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
