package com.dhu.cst.zjm.entity.base;

import javax.persistence.*;

/**
 * Created by zjm on 2017/3/15.
 */
@Entity
@Table(name = "MD5", schema = "encrypt", catalog = "")
public class Md5BaseEntity {
    private int id;
    private Integer relationId;
    private String sign;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "RelationID", nullable = true)
    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    @Basic
    @Column(name = "Sign", nullable = false, length = -1)
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Md5BaseEntity that = (Md5BaseEntity) o;

        if (id != that.id) return false;
        if (relationId != null ? !relationId.equals(that.relationId) : that.relationId != null) return false;
        if (sign != null ? !sign.equals(that.sign) : that.sign != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (relationId != null ? relationId.hashCode() : 0);
        result = 31 * result + (sign != null ? sign.hashCode() : 0);
        return result;
    }
}
