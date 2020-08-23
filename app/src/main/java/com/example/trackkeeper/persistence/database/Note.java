package com.example.trackkeeper.persistence.database;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable {
    private String title;
    private String subTitle;
    private String description;
    private String color;
    private String imageUrl;
    private String noteUrl;
    private Date date;

    public Note(String title, String subTitle, String description, String color, String imageUrl, String noteUrl, Date date) {
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.color = color;
        this.imageUrl = imageUrl;
        this.noteUrl = noteUrl;
        this.date = date;
    }

    public Note(String title, String subTitle, String description, String color, Date date) {
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.color = color;
        this.date = date;
    }

    public Note() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getNoteUrl() {
        return noteUrl;
    }

    public void setNoteUrl(String noteUrl) {
        this.noteUrl = noteUrl;
    }
}
