package com.example.rmptest;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity (tableName = "posts" )
public class PostsEntity {

    @PrimaryKey (autoGenerate = true)
    long id;
    private long userId;
    private long date;
    private String text;
    private String image;

    public PostsEntity(long id, long userId, long date, String text, String image) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.text = text;
        this.image = image;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
