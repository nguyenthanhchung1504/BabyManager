package com.babymanager.babymanager.Models;

public class DetailTips {
    private Integer id;
    private String content;
    private byte[] photo;
    private String coverImage;
    private Integer categoryId;

    public DetailTips() {
    }

    public DetailTips(Integer id, String content, byte[] photo, String coverImage, Integer categoryId) {
        this.id = id;
        this.content = content;
        this.photo = photo;
        this.coverImage = coverImage;
        this.categoryId = categoryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
