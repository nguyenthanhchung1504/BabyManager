package com.babymanager.babymanager.Models;

public class User {
    private Integer id;
    private String nameBaby;
    private String sexBaby;
    private String birthday;
    private byte[] photoBaby;
    private String weight;
    private String height;
    private byte[] background;
    public User() {
    }

    public User(byte[] background) {
        this.background = background;
    }

    public User(Integer id, String nameBaby, String sexBaby, String birthday, byte[] photoBaby, String weight, String height) {
        this.id = id;
        this.nameBaby = nameBaby;
        this.sexBaby = sexBaby;
        this.birthday = birthday;
        this.photoBaby = photoBaby;
        this.weight = weight;
        this.height = height;
    }

    public User(String nameBaby, String sexBaby, String birthday, byte[] photoBaby) {
        this.nameBaby = nameBaby;
        this.sexBaby = sexBaby;
        this.birthday = birthday;
        this.photoBaby = photoBaby;
    }

    public User(String weight, String height) {
        this.weight = weight;
        this.height = height;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameBaby() {
        return nameBaby;
    }

    public void setNameBaby(String nameBaby) {
        this.nameBaby = nameBaby;
    }

    public String getSexBaby() {
        return sexBaby;
    }

    public void setSexBaby(String sexBaby) {
        this.sexBaby = sexBaby;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public byte[] getPhotoBaby() {
        return photoBaby;
    }

    public void setPhotoBaby(byte[] photoBaby) {
        this.photoBaby = photoBaby;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public byte[] getBackground() {
        return background;
    }

    public void setBackground(byte[] background) {
        this.background = background;
    }
}
