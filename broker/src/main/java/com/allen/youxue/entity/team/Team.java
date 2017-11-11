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

    public final static int STATE_SIGN = 0;
    public final static int STATE_WAIT = 1;
    public final static int STATE_PASS = 2;
    public final static int STATE_NOT = 3;
    public final static int STATE_FEE = 4;

    @Id
    @GeneratedValue
    private Long id;
    private Long productDateId;     //产品的行程时间id
    private String zz;              //团员zz
    private int isHead;             //是否团长[0：否；1：是]
    private Integer state;          //状态[0：已报名；1：待审核；2：通过；3：不通过；4：已缴费]只有普通团员才会审核，团长由经纪人在后台直接创建通过
    private String reason;          //审核不通过原因
    private String label;           //团长的个性说明
    private String yyDate;          //预约咨询时间
    private String qq;              //团咨询qq群
    private Long parentId;          //团长id
    private int visitCount;          //团长的浏览次数
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getYyDate() {
        return yyDate;
    }

    public void setYyDate(String yyDate) {
        this.yyDate = yyDate;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
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

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }
}
