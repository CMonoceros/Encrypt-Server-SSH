package com.dhu.cst.zjm.entity.base;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by zjm on 2017/3/15.
 */
@Entity
@Table(name = "File", schema = "encrypt", catalog = "")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FileBaseEntity {
    private int id;
    private String name;
    private String size;
    private Timestamp uploadTime;
    private Timestamp lastDownloadTime;
    private Timestamp lastEncryptTime;
    private String path;
    private int owner;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Size", nullable = false, length = 10)
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Basic
    @Column(name = "UploadTime", nullable = true)
    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Basic
    @Column(name = "LastDownloadTime", nullable = true)
    public Timestamp getLastDownloadTime() {
        return lastDownloadTime;
    }

    public void setLastDownloadTime(Timestamp lastDownloadTime) {
        this.lastDownloadTime = lastDownloadTime;
    }

    @Basic
    @Column(name = "LastEncryptTime", nullable = true)
    public Timestamp getLastEncryptTime() {
        return lastEncryptTime;
    }

    public void setLastEncryptTime(Timestamp lastEncryptTime) {
        this.lastEncryptTime = lastEncryptTime;
    }

    @Basic
    @Column(name = "Path", nullable = false, length = 100)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "Owner", nullable = false)
    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileBaseEntity that = (FileBaseEntity) o;

        if (id != that.id) return false;
        if (owner != that.owner) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;
        if (uploadTime != null ? !uploadTime.equals(that.uploadTime) : that.uploadTime != null) return false;
        if (lastDownloadTime != null ? !lastDownloadTime.equals(that.lastDownloadTime) : that.lastDownloadTime != null)
            return false;
        if (lastEncryptTime != null ? !lastEncryptTime.equals(that.lastEncryptTime) : that.lastEncryptTime != null)
            return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (uploadTime != null ? uploadTime.hashCode() : 0);
        result = 31 * result + (lastDownloadTime != null ? lastDownloadTime.hashCode() : 0);
        result = 31 * result + (lastEncryptTime != null ? lastEncryptTime.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + owner;
        return result;
    }
}
