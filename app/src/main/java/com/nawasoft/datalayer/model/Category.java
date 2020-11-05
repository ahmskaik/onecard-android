package com.nawasoft.datalayer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("caId")
    private String id;
    @SerializedName("theTitle")
    private String title;
    @SerializedName("thePhoto")
    private String photoLink;
    @SerializedName("theColor")
    private String color;

    @Expose
    private boolean isSelected;
    public Category(String id, String title, String photoLink, String color) {
        this.id = id;
        this.title = title;
        this.photoLink = photoLink;
        this.color = color;
        isSelected = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
