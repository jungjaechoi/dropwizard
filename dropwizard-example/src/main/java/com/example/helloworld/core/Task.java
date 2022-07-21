package com.example.helloworld.core;

import javax.persistence.*;

@Entity
@Table(name = "task_email")
@NamedQuery(
    name = "com.example.helloworld.core.Task.findAll",
    query = "SELECT u FROM Task u"
)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "priority", nullable = false)
    private String priority;

    @Column(name = "access", nullable = false)
    private String access;

    @Column(name = "create_date", nullable = false)
    private String create_date;

    @Column(name = "edit_date", nullable = false)
    private String edit_date;

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long userId) {
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

    public Task(Long id, Long userId, String title,
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
