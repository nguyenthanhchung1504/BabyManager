package com.babymanager.babymanager.Models;

public class Feed {
    private Integer Id;
    private String nameFood;
    private String content;
    private String date;
    private Integer userId;
    private String note;
    private byte[] imageFood;

    public Feed() {
    }

    public Feed(Integer id, String nameFood, String content, String date, Integer userId, String note, byte[] imageFood) {
        Id = id;
        this.nameFood = nameFood;
        this.content = content;
        this.date = date;
        this.userId = userId;
        this.note = note;
        this.imageFood = imageFood;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public byte[] getImageFood() {
        return imageFood;
    }

    public void setImageFood(byte[] imageFood) {
        this.imageFood = imageFood;
    }
}
