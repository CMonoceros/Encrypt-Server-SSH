package com.dhu.cst.zjm.entity;

import javax.persistence.*;

/**
 * Created by zjm on 2017/3/14.
 */
@Entity
@Table(name = "DES", schema = "encrypt", catalog = "")
public class DesEntity {
    private Integer id;
    private String desKey;
    private Integer layer;
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
    @Column(name = "DESKey", nullable = false, length = -1)
    public String getDesKey() {
        return desKey;
    }

    public void setDesKey(String desKey) {
        this.desKey = desKey;
    }


    @Basic
    @Column(name = "Layer", nullable = false)
    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DesEntity desEntity = (DesEntity) o;

        if (id != null ? !id.equals(desEntity.id) : desEntity.id != null) return false;
        if (desKey != null ? !desKey.equals(desEntity.desKey) : desEntity.desKey != null) return false;
        if (layer != null ? !layer.equals(desEntity.layer) : desEntity.layer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (desKey != null ? desKey.hashCode() : 0);
        result = 31 * result + (layer != null ? layer.hashCode() : 0);
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
