package com.dhu.cst.zjm.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by zjm on 2017/3/14.
 */
@Entity
@Table(name = "EncryptType", schema = "encrypt", catalog = "")
public class EncryptTypeEntity {
    private Integer id;
    private String name;
    private String description;
    private Collection<EncryptRelationEntity> encryptRelationsById;
    private Collection<EncryptFrequencyEntity> encryptFrequencyById;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EncryptTypeEntity that = (EncryptTypeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "encryptTypeByTypeId")
    public Collection<EncryptRelationEntity> getEncryptRelationsById() {
        return encryptRelationsById;
    }

    public void setEncryptRelationsById(Collection<EncryptRelationEntity> encryptRelationsById) {
        this.encryptRelationsById = encryptRelationsById;
    }

    @OneToMany(mappedBy = "encryptTypeByFrequencyTypeId")
    public Collection<EncryptFrequencyEntity> getEncryptFrequencyById() {
        return encryptFrequencyById;
    }

    public void setEncryptFrequencyById(Collection<EncryptFrequencyEntity> encryptFrequencyById) {
        this.encryptFrequencyById = encryptFrequencyById;
    }
}
