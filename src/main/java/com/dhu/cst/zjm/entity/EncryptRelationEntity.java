package com.dhu.cst.zjm.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by zjm on 2017/3/14.
 */
@Entity
@Table(name = "EncryptRelation", schema = "encrypt", catalog = "")
public class EncryptRelationEntity {
    private Integer id;
    private Collection<AesEntity> aesById;
    private Collection<DesEntity> desById;
    private FileEntity fileByFileId;
    private EncryptTypeEntity encryptTypeByTypeId;
    private Collection<Md5Entity> md5sById;
    private Collection<RsaEntity> rsasById;
    private Timestamp encryptTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

        EncryptRelationEntity that = (EncryptRelationEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (encryptTime != null ? !encryptTime.equals(that.encryptTime) : that.encryptTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (encryptTime != null ? encryptTime.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "encryptRelationByRelationId")
    public Collection<AesEntity> getAesById() {
        return aesById;
    }

    public void setAesById(Collection<AesEntity> aesById) {
        this.aesById = aesById;
    }

    @OneToMany(mappedBy = "encryptRelationByRelationId")
    public Collection<DesEntity> getDesById() {
        return desById;
    }

    public void setDesById(Collection<DesEntity> desById) {
        this.desById = desById;
    }

    @ManyToOne
    @JoinColumn(name = "FileID", referencedColumnName = "ID", nullable = false)
    public FileEntity getFileByFileId() {
        return fileByFileId;
    }

    public void setFileByFileId(FileEntity fileByFileId) {
        this.fileByFileId = fileByFileId;
    }

    @ManyToOne
    @JoinColumn(name = "TypeID", referencedColumnName = "ID", nullable = false)
    public EncryptTypeEntity getEncryptTypeByTypeId() {
        return encryptTypeByTypeId;
    }

    public void setEncryptTypeByTypeId(EncryptTypeEntity encryptTypeByTypeId) {
        this.encryptTypeByTypeId = encryptTypeByTypeId;
    }

    @OneToMany(mappedBy = "encryptRelationByRelationId")
    public Collection<Md5Entity> getMd5sById() {
        return md5sById;
    }

    public void setMd5sById(Collection<Md5Entity> md5sById) {
        this.md5sById = md5sById;
    }

    @OneToMany(mappedBy = "encryptRelationByRelationId")
    public Collection<RsaEntity> getRsasById() {
        return rsasById;
    }

    public void setRsasById(Collection<RsaEntity> rsasById) {
        this.rsasById = rsasById;
    }
}
