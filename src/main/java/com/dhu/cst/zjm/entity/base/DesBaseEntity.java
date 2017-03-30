package com.dhu.cst.zjm.entity.base;

import javax.persistence.*;

/**
 * Created by zjm on 2017/3/15.
 */
@Entity
@Table(name = "DES", schema = "encrypt", catalog = "")
public class DesBaseEntity {
    private int id;
    private String desKey;
    private Integer relationId;
    private int layer;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    @Column(name = "RelationID", nullable = true)
    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    @Basic
    @Column(name = "Layer", nullable = false)
    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DesBaseEntity that = (DesBaseEntity) o;

        if (id != that.id) return false;
        if (layer != that.layer) return false;
        if (desKey != null ? !desKey.equals(that.desKey) : that.desKey != null) return false;
        if (relationId != null ? !relationId.equals(that.relationId) : that.relationId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (desKey != null ? desKey.hashCode() : 0);
        result = 31 * result + (relationId != null ? relationId.hashCode() : 0);
        result = 31 * result + layer;
        return result;
    }
}
