package com.dhu.cst.zjm.entity.base;

import javax.persistence.*;

/**
 * Created by zjm on 2017/3/15.
 */
@Entity
@Table(name = "AES", schema = "encrypt", catalog = "")
public class AesBaseEntity {
    private int id;
    private Integer relationId;
    private String aesKey;

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
    @Column(name = "AESKey", nullable = false, length = -1)
    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AesBaseEntity that = (AesBaseEntity) o;

        if (id != that.id) return false;
        if (relationId != null ? !relationId.equals(that.relationId) : that.relationId != null) return false;
        if (aesKey != null ? !aesKey.equals(that.aesKey) : that.aesKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (relationId != null ? relationId.hashCode() : 0);
        result = 31 * result + (aesKey != null ? aesKey.hashCode() : 0);
        return result;
    }
}
