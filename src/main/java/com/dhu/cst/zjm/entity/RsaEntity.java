package com.dhu.cst.zjm.entity;

import javax.persistence.*;

/**
 * Created by zjm on 2017/3/14.
 */
@Entity
@Table(name = "RSA", schema = "encrypt", catalog = "")
public class RsaEntity {
    private Integer id;
    private String publicKey;
    private String privateKey;
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
    @Column(name = "PublicKey", nullable = false, length = -1)
    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    @Basic
    @Column(name = "PrivateKey", nullable = false, length = -1)
    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RsaEntity rsaEntity = (RsaEntity) o;

        if (id != null ? !id.equals(rsaEntity.id) : rsaEntity.id != null) return false;
        if (publicKey != null ? !publicKey.equals(rsaEntity.publicKey) : rsaEntity.publicKey != null) return false;
        if (privateKey != null ? !privateKey.equals(rsaEntity.privateKey) : rsaEntity.privateKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (publicKey != null ? publicKey.hashCode() : 0);
        result = 31 * result + (privateKey != null ? privateKey.hashCode() : 0);
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
