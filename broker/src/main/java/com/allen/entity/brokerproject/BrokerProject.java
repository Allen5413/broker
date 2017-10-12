package com.allen.entity.brokerproject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Allen on 2017/10/10.
 */
@Entity
@Table(name = "broker_project")
public class BrokerProject {

    public static final int STATE_WAIT = 0;     //待审核
    public static final int STATE_PASS = 1;     //通过
    public static final int STATE_NOT = 2;      //不通过

    public static final int ISBLACK_NOT = 0;     //否
    public static final int ISBLACK_YES = 1;     //是

    public static final int BLACKTYPE_SIXMONTH = 0;     //6个月
    public static final int BLACKTYPE_FOREVER = 1;     //永久

    private Long id;
    private Long brokerId;
    private Long projectId;
    private Integer state;              //状态[0：待审核，1：通过；2：不通过]
    private String reason;          //不通过原因
    private Integer isBlack;            //是否拉黑[0：正常；1：拉黑]
    private Integer blackType;          //拉黑类型[0：6个月; 1：永久]
    private String blackReason;     //拉黑原因
    private String creator;                     //创建人
    private Date createTime = new Date();       //创建时间
    private String operator;                    //操作人
    private Date operateTime = new Date();      //操作时间

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(Long brokerId) {
        this.brokerId = brokerId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}
