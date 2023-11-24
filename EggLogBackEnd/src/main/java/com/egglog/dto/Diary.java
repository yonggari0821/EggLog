package com.egglog.dto;


public class Diary {
    private String userId;
    private String diaryDate;
    private String title;
    private String content;
    private String hashtag;
    private String location;
    
    public Diary(String userId, String diaryDate, String title, String content,
            String hashtag, String location) {
        this.userId = userId;
        this.diaryDate = diaryDate;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
        this.location = location;
    }
    
    public Diary() {
    }
    
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getDiaryDate() {
        return diaryDate;
    }
    public void setDiaryDate(String diaryDate) {
        this.diaryDate = diaryDate;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getHashtag() {
        return hashtag;
    }
    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    @Override
    public String toString() {
        return "Diary [userId=" + userId + ", diaryDate=" + diaryDate
                + ", title=" + title + ", content=" + content + ", hashtag="
                + hashtag + ", location=" + location + "]";
    }
    
    

    
    
}
