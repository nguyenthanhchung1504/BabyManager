package com.babymanager.babymanager.Models;

public class Measure {
    private Integer id;
    private Integer height;
    private Float weight;
    private String date;
    private Integer month;
    private Integer userId;

    public Measure() {
    }

    public Measure(Integer height) {
        this.height = height;
    }

    public Measure(Integer id, Float weight, Integer height, String date, Integer userId) {
        this.id = id;
        this.weight = weight;
        this.height = height;
        this.date = date;
        this.userId = userId;
    }

    public Measure(Float weight, Integer height) {
        this.weight = weight;
        this.height = height;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
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

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
