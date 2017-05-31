package com.dhu.cst.zjm.entity.base;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by zjm on 2017/3/15.
 */
@Entity
@Table(name = "EncryptRelation", schema = "encrypt", catalog = "")
public class EncryptRelationBaseEntity {
    private int id;
    private int fileId;
    private int typeId;
    private Timestamp encryptTime;

    @Id
    @Column(name = "ID", nullable = false,unique=true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FileID", nullable = false)
    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    @Basic
    @Column(name = "TypeID", nullable = false)
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "EncryptTime", nullable = true)
    public Timestamp getEncryptTime() {
        return encryptTime;
    }

    public void setEncryptTime(Timestamp encryptTime) {
        this.encryptTime = encryptTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EncryptRelationBaseEntity that = (EncryptRelationBaseEntity) o;

        if (id != that.id) return false;
        if (fileId != that.fileId) return false;
        if (typeId != that.typeId) return false;
        if (encryptTime != null ? !encryptTime.equals(that.encryptTime) : that.encryptTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + fileId;
        result = 31 * result + typeId;
        result = 31 * result + (encryptTime != null ? encryptTime.hashCode() : 0);
        return result;
    }
}
