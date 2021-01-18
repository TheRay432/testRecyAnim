package com.example.testrecyanim.model;

public class NewsItem {
    private String Title;
    private String Content;
    private String Date;
    private int userPhoto;

    public NewsItem() {
    }

    public NewsItem(String title, String content, String date, int userPhoto) {
        Title = title;
        Content = content;
        Date = date;
        this.userPhoto = userPhoto;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(int userPhoto) {
        this.userPhoto = userPhoto;
    }
}
