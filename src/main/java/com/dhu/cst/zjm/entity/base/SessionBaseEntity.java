package com.dhu.cst.zjm.entity.base;

import javax.persistence.*;

/**
 * Created by zjm on 2017/3/17.
 */
@Entity
@Table(name = "Session", schema = "encrypt", catalog = "")
public class SessionBaseEntity {
    private String id;
    private int userId;
    private int time;

    @Id
    @Column(name = "ID", nullable = false, length = 45)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "UserID", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "Time", nullable = false)
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionBaseEntity that = (SessionBaseEntity) o;

        if (userId != that.userId) return false;
        if (time != that.time) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + userId;
        result = 31 * result + time;
        return result;
    }
}
