package com.dhu.cst.zjm.entity.base;

import javax.persistence.*;

/**
 * Created by zjm on 2017/3/15.
 */
@Entity
@Table(name = "RSA", schema = "encrypt", catalog = "")
public class RsaBaseEntity {
    private int id;
    private Integer relationId;
    private String publicKey;
    private String privateKey;

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

        RsaBaseEntity that = (RsaBaseEntity) o;

        if (id != that.id) return false;
        if (relationId != null ? !relationId.equals(that.relationId) : that.relationId != null) return false;
        if (publicKey != null ? !publicKey.equals(that.publicKey) : that.publicKey != null) return false;
        if (privateKey != null ? !privateKey.equals(that.privateKey) : that.privateKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (relationId != null ? relationId.hashCode() : 0);
        result = 31 * result + (publicKey != null ? publicKey.hashCode() : 0);
        result = 31 * result + (privateKey != null ? privateKey.hashCode() : 0);
        return result;
    }
}
