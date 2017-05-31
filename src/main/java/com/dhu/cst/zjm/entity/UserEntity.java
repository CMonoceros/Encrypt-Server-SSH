package com.dhu.cst.zjm.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by zjm on 2017/3/14.
 */
@Entity
@Table(name = "User", schema = "encrypt", catalog = "")
public class UserEntity {
    private Integer id;
    private String name;
    private String password;
    private Timestamp registerTime;
    private Byte sex;
    private Integer tel;
    private Integer qq;
    private String email;
    private Integer permission;
    private Collection<FileEntity> filesById;
    private Collection<SessionEntity> sessionById;
    private Collection<EncryptFrequencyEntity> frequencyById;

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
    @Column(name = "Name", nullable = false, length = 16)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Password", nullable = false, length = -1)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "RegisterTime", nullable = false)
    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    @Basic
    @Column(name = "Sex", nullable = true)
    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "Tel", nullable = true)
    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "QQ", nullable = true)
    public Integer getQq() {
        return qq;
    }

    public void setQq(Integer qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "Email", nullable = true, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "Permission", nullable = true)
    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (registerTime != null ? !registerTime.equals(that.registerTime) : that.registerTime != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;
        if (qq != null ? !qq.equals(that.qq) : that.qq != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (permission != null ? !permission.equals(that.permission) : that.permission != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (registerTime != null ? registerTime.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (permission != null ? permission.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userByOwner")
    public Collection<FileEntity> getFilesById() {
        return filesById;
    }

    public void setFilesById(Collection<FileEntity> filesById) {
        this.filesById = filesById;
    }


    @OneToMany(mappedBy = "userByUserID")
    public Collection<SessionEntity> getSessionById() {
        return sessionById;
    }

    public void setSessionById(Collection<SessionEntity> sessionById) {
        this.sessionById = sessionById;
    }

    @OneToMany(mappedBy = "userByFrequencyOwner")
    public Collection<EncryptFrequencyEntity> getFrequencyById() {
        return frequencyById;
    }

    public void setFrequencyById(Collection<EncryptFrequencyEntity> frequencyById) {
        this.frequencyById = frequencyById;
    }
}
