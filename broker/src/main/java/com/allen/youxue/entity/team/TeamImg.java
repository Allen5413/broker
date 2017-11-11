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
@Table(name = "yx_team_img")
public class TeamImg {

    public final static int ISCOVER_NOT = 0;
    public final static int ISCOVER_YES = 1;

    @Id
    @GeneratedValue
    private Long id;
    private String zz;                  //团员zz
    private String imgUrl;              //照片url
    private String imgSmallUrl;         //照片缩略图url
    private int isCover;                //是否封面图[0：不是；1：是]
    private String creator;                     //创建人
    private Date createTime = new Date();       //创建时间

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgSmallUrl() {
        return imgSmallUrl;
    }

    public void setImgSmallUrl(String imgSmallUrl) {
        this.imgSmallUrl = imgSmallUrl;
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

    public int getIsCover() {
        return isCover;
    }

    public void setIsCover(int isCover) {
        this.isCover = isCover;
    }
}
