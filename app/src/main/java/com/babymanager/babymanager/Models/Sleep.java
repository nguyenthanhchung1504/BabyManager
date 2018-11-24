package com.babymanager.babymanager.Models;

public class Sleep {
    private Integer id;
    private String timeStamp;
    private String startDate;
    private String endDate;
    private Integer userId;

    public Sleep() {
    }

    public Sleep(Integer id, String timeStamp, String startDate, String endDate, Integer userId) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
