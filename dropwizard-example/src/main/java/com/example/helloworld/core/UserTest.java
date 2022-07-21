package com.example.helloworld.core;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "usertest")
@NamedQuery(
    name = "com.example.helloworld.core.UserTest.findAll",
    query = "SELECT u FROM UserTest u"
)
public class UserTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "userId", nullable = false)
    private String userId;

    @Column(name = "userPassword", nullable = false)
    private String userPassword;

    public UserTest() {
    }

    public UserTest(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof UserTest)) {
            return false;
        }

        UserTest user = (UserTest) o;

        return id == user.id &&
            Objects.equals(userId, user.userId) &&
            Objects.equals(userPassword, user.userPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, userPassword);
    }

}
