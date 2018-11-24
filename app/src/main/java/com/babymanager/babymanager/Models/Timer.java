package com.babymanager.babymanager.Models;

public class Timer {
    private Integer Id;
    private String title;
    private String body;
    private  String timeStamp;
    private String date;
    private Integer userId;

    public Timer() {
    }

    public Timer(Integer id, String title, String body, String timeStamp, String date, Integer userId) {
        Id = id;
        this.title = title;
        this.body = body;
        this.timeStamp = timeStamp;
        this.date = date;
        this.userId = userId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
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
}
