package com.vinspier.pojo;

public class Suggestion {
    private String id;
    private String suggest_content;
    private Integer like_count;
    private User user;


    public Suggestion() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSuggest_content() {
        return suggest_content;
    }

    public void setSuggest_content(String suggest_content) {
        this.suggest_content = suggest_content;
    }

    public Integer getLike_count() {
        return like_count;
    }

    public void setLike_count(Integer like_count) {
        this.like_count = like_count;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
