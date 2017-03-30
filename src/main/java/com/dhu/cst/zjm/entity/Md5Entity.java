package com.dhu.cst.zjm.entity;

import javax.persistence.*;

/**
 * Created by zjm on 2017/3/14.
 */
@Entity
@Table(name = "MD5", schema = "encrypt", catalog = "")
public class Md5Entity {
    private Integer id;
    private String sign;
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

        Md5Entity md5Entity = (Md5Entity) o;

        if (id != null ? !id.equals(md5Entity.id) : md5Entity.id != null) return false;
        if (sign != null ? !sign.equals(md5Entity.sign) : md5Entity.sign != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sign != null ? sign.hashCode() : 0);
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
