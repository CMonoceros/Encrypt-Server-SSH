package com.dhu.cst.zjm.entity;

import javax.persistence.*;

/**
 * Created by zjm on 2017/5/29.
 */
@Entity
@Table(name = "EncryptFrequency", schema = "encrypt", catalog = "")
public class EncryptFrequencyEntity {
    private int id;
    private int dayFrequency;
    private int weekFrequency;
    private int monthFrequency;
    private int yearFrequency;
    private int priority;
    private UserEntity userByFrequencyOwner;
    private EncryptTypeEntity encryptTypeByFrequencyTypeId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DayFrequency", nullable = true, columnDefinition = "int default 0")
    public int getDayFrequency() {
        return dayFrequency;
    }

    public void setDayFrequency(int dayFrequency) {
        this.dayFrequency = dayFrequency;
    }

    @Basic
    @Column(name = "WeekFrequency", nullable = true, columnDefinition = "int default 0")
    public int getWeekFrequency() {
        return weekFrequency;
    }

    public void setWeekFrequency(int weekFrequency) {
        this.weekFrequency = weekFrequency;
    }

    @Basic
    @Column(name = "MonthFrequency", nullable = true, columnDefinition = "int default 0")
    public int getMonthFrequency() {
        return monthFrequency;
    }

    public void setMonthFrequency(int monthFrequency) {
        this.monthFrequency = monthFrequency;
    }

    @Basic
    @Column(name = "YearFrequency", nullable = true, columnDefinition = "int default 0")
    public int getYearFrequency() {
        return yearFrequency;
    }

    public void setYearFrequency(int yearFrequency) {
        this.yearFrequency = yearFrequency;
    }

    @Basic
    @Column(name = "Priority", nullable = true, columnDefinition = "int default 0")
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EncryptFrequencyEntity that = (EncryptFrequencyEntity) o;

        if (id != that.id) return false;
        if (dayFrequency != that.dayFrequency) return false;
        if (weekFrequency != that.weekFrequency) return false;
        if (monthFrequency != that.monthFrequency) return false;
        if (yearFrequency != that.yearFrequency) return false;
        if (priority != that.priority) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + dayFrequency;
        result = 31 * result + weekFrequency;
        result = 31 * result + monthFrequency;
        result = 31 * result + yearFrequency;
        result = 31 * result + priority;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Owner", referencedColumnName = "ID", nullable = false)
    public UserEntity getUserByFrequencyOwner() {
        return userByFrequencyOwner;
    }

    public void setUserByFrequencyOwner(UserEntity userByFrequencyOwner) {
        this.userByFrequencyOwner = userByFrequencyOwner;
    }

    @ManyToOne
    @JoinColumn(name = "TypeID", referencedColumnName = "ID", nullable = false)
    public EncryptTypeEntity getEncryptTypeByFrequencyTypeId() {
        return encryptTypeByFrequencyTypeId;
    }

    public void setEncryptTypeByFrequencyTypeId(EncryptTypeEntity encryptTypeByFrequencyTypeId) {
        this.encryptTypeByFrequencyTypeId = encryptTypeByFrequencyTypeId;
    }
}
