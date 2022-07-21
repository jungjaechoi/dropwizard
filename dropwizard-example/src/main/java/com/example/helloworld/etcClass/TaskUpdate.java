package com.example.helloworld.etcClass;

import javax.persistence.*;


public class TaskUpdate {

    private Long id;

    private String userEmail;
    private Long userId;

    private String title;

    private String content;

    private String state;

    private String priority;

    private String access;

    private String create_date;

    private String edit_date;

    public TaskUpdate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getEdit_date() {
        return edit_date;
    }

    public void setEdit_date(String edit_date) {
        this.edit_date = edit_date;
    }

    public TaskUpdate(long id, long userId, String title,
                String content, String state, String priority,
                String access, String create_date) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.state = state;
        this.priority = priority;
        this.access = access;
        this.create_date = create_date;
    }

}
