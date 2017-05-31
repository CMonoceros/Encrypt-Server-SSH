package com.dhu.cst.zjm.entity;

import javax.persistence.*;

/**
 * Created by zjm on 2017/5/31.
 */
@Entity
@Table(name = "favoritetypedataset", schema = "encrypt", catalog = "")
public class FavoriteTypeDataSetEntity {
    private int id;
    private double dayScale;
    private double weekScale;
    private double monthScale;
    private double yearScale;
    private Integer rate;
    private int owner;
    private int typeId;
    private Byte isTrain;

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
    @Column(name = "DayScale", nullable = false, precision = 0)
    public double getDayScale() {
        return dayScale;
    }

    public void setDayScale(double dayScale) {
        this.dayScale = dayScale;
    }

    @Basic
    @Column(name = "WeekScale", nullable = false, precision = 0)
    public double getWeekScale() {
        return weekScale;
    }

    public void setWeekScale(double weekScale) {
        this.weekScale = weekScale;
    }

    @Basic
    @Column(name = "MonthScale", nullable = false, precision = 0)
    public double getMonthScale() {
        return monthScale;
    }

    public void setMonthScale(double monthScale) {
        this.monthScale = monthScale;
    }

    @Basic
    @Column(name = "YearScale", nullable = false, precision = 0)
    public double getYearScale() {
        return yearScale;
    }

    public void setYearScale(double yearScale) {
        this.yearScale = yearScale;
    }

    @Basic
    @Column(name = "Rate", nullable = true, columnDefinition = "int default 0")
    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @Basic
    @Column(name = "Owner", nullable = false)
    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    @Basic
    @Column(name = "TypeID", nullable = false)
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "IsTrain", nullable = true)
    public Byte getIsTrain() {
        return isTrain;
    }

    public void setIsTrain(Byte isTrain) {
        this.isTrain = isTrain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FavoriteTypeDataSetEntity that = (FavoriteTypeDataSetEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.dayScale, dayScale) != 0) return false;
        if (Double.compare(that.weekScale, weekScale) != 0) return false;
        if (Double.compare(that.monthScale, monthScale) != 0) return false;
        if (Double.compare(that.yearScale, yearScale) != 0) return false;
        if (owner != that.owner) return false;
        if (typeId != that.typeId) return false;
        if (isTrain != null ? !isTrain.equals(that.isTrain) : that.isTrain != null) return false;
        if (rate != null ? !rate.equals(that.rate) : that.rate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(dayScale);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(weekScale);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(monthScale);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(yearScale);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        result = 31 * result + owner;
        result = 31 * result + (isTrain != null ? isTrain.hashCode() : 0);
        result = 31 * result + typeId;
        return result;
    }
}
