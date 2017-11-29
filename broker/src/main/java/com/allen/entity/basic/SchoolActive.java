package com.allen.entity.basic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 高校月活跃度，数据来源于至善接口提供
 * Created by Allen on 2017/11/28.
 */
@Entity
@Table(name = "school_active")
public class SchoolActive {
    @Id
    @GeneratedValue
    private long id;
    private String no;
    private String date;       //只记录年月
    private long num;
    private int numPercent;     //活跃度百分比

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public int getNumPercent() {
        return numPercent;
    }

    public void setNumPercent(int numPercent) {
        this.numPercent = numPercent;
    }
}
