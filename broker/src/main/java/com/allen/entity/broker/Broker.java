package com.allen.entity.broker;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Allen on 2017/10/9.
 */
@Entity
@Table(name = "broker")
public class Broker {

    public static final int ISBLACK_NOT = 0;     //否
    public static final int ISBLACK_YES = 1;     //是

    public static final int BLACKTYPE_SIXMONTH = 0;     //6个月
    public static final int BLACKTYPE_FOREVER = 1;     //永久

    private Long id;
    private String zz;
    private String schoolNo;            //学校No
    private Integer isBlack;            //是否拉黑[0：正常；1：拉黑]
    private Integer blackType;          //拉黑类型[0：6个月; 1：永久]
    private Date blackTime;             //拉黑时间
    private String blackReason;     //拉黑原因
    private String creator;                     //创建人
    private Date createTime = new Date();       //创建时间

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZz() {
        return zz;
    }

    public void setZz(String zz) {
        this.zz = zz;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsBlack() {
        return isBlack;
    }

    public void setIsBlack(Integer isBlack) {
        this.isBlack = isBlack;
    }

    public Integer getBlackType() {
        return blackType;
    }

    public void setBlackType(Integer blackType) {
        this.blackType = blackType;
    }

    public String getBlackReason() {
        return blackReason;
    }

    public void setBlackReason(String blackReason) {
        this.blackReason = blackReason;
    }

    public Date getBlackTime() {
        return blackTime;
    }

    public void setBlackTime(Date blackTime) {
        this.blackTime = blackTime;
    }

    public String getSchoolNo() {
        return schoolNo;
    }

    public void setSchoolNo(String schoolNo) {
        this.schoolNo = schoolNo;
    }
}
