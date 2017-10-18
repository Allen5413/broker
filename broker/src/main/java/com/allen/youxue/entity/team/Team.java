package com.allen.youxue.entity.team;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Allen on 2017/10/17.
 */
@Entity
@Table(name = "yx_team")
public class Team {

    public final static int ISHEAD_NOT = 0;
    public final static int ISHEAD_YES = 1;

    @Id
    @GeneratedValue
    private Long id;
    private Long productDateId;     //产品的行程时间id
    private String zz;              //团员zz
    private int isHead;             //是否团长[0：否；1：是]
    private int state;              //状态[0：待审核；1：通过；2：不通过]只有普通团员才会审核，团长由经纪人在后台直接创建通过
    private String reason;          //审核不通过原因
    private String label;           //团长的个性说明
    private Long parentId;          //团长id
    private String remark;          //备注
    private String creator;                     //创建人
    private Date createTime = new Date();       //创建时间
    private String operator;                    //操作人
    private Date operateTime = new Date();      //操作时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductDateId() {
        return productDateId;
    }

    public void setProductDateId(Long productDateId) {
        this.productDateId = productDateId;
    }

    public String getZz() {
        return zz;
    }

    public void setZz(String zz) {
        this.zz = zz;
    }

    public int getIsHead() {
        return isHead;
    }

    public void setIsHead(int isHead) {
        this.isHead = isHead;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
