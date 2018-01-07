package com.example.redis.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Table(name = "user_info", schema = "test")
@Entity
public class UserInfoEntity implements Serializable {
    private static final long serialVersionUID = -8216716094707569171L;
    private Integer id;
    private String userName;
    private String email;

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfoEntity that = (UserInfoEntity) o;
        return id == that.id &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, email);
    }
}
