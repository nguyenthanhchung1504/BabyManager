package com.babymanager.babymanager.Models;

public class Diaper {
    private Integer Id;
    private String status,timeStamp;
    private Integer userId;
    private byte[] imageDiaper;
    public Diaper() {
    }

    public Diaper(Integer id, String status, String timeStamp, Integer userId, byte[] imageDiaper) {
        Id = id;
        this.status = status;
        this.timeStamp = timeStamp;
        this.userId = userId;
        this.imageDiaper = imageDiaper;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public byte[] getImageDiaper() {
        return imageDiaper;
    }

    public void setImageDiaper(byte[] imageDiaper) {
        this.imageDiaper = imageDiaper;
    }
}
