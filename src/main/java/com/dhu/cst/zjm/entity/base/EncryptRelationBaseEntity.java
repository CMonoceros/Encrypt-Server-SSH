package com.dhu.cst.zjm.entity.base;

import javax.persistence.*;

/**
 * Created by zjm on 2017/3/15.
 */
@Entity
@Table(name = "EncryptRelation", schema = "encrypt", catalog = "")
public class EncryptRelationBaseEntity {
    private int id;
    private int fileId;
    private int typeId;

    @Id
    @Column(name = "ID", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EncryptRelationBaseEntity that = (EncryptRelationBaseEntity) o;

        if (id != that.id) return false;
        if (fileId != that.fileId) return false;
        if (typeId != that.typeId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + fileId;
        result = 31 * result + typeId;
        return result;
    }
}
