package com.dhu.cst.zjm.entity;

import javax.persistence.*;

/**
 * Created by zjm on 2017/3/14.
 */
@Entity
@Table(name = "AES", schema = "encrypt", catalog = "")
public class AesEntity {
    private Integer id;
    private String aesKey;
    private EncryptRelationEntity encryptRelationByRelationId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false ,unique=true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

        AesEntity aesEntity = (AesEntity) o;

        if (id != null ? !id.equals(aesEntity.id) : aesEntity.id != null) return false;
        if (aesKey != null ? !aesKey.equals(aesEntity.aesKey) : aesEntity.aesKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (aesKey != null ? aesKey.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "RelationID", referencedColumnName = "ID")
    public EncryptRelationEntity getEncryptRelationByRelationId() {
        return encryptRelationByRelationId;
    }

    public void setEncryptRelationByRelationId(EncryptRelationEntity encryptRelationByRelationId) {
        this.encryptRelationByRelationId = encryptRelationByRelationId;
    }
}
