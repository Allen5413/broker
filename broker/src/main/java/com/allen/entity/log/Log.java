package com.allen.entity.log;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Allen on 2017/10/10.
 */
@Entity
@Table(name = "log")
public class Log {

    public final static int TYPE_ADD = 0;
    public final static int TYPE_EDIT = 1;
    public final static int TYPE_DEL = 2;

    @Id
    @GeneratedValue
    private Long id;
    private int type;                           //操作类型[0：新增；1：修改；2：删除]
    private String content;
    private Long operatorId;
    private String operatorName;
    private Date operateTime = new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}
