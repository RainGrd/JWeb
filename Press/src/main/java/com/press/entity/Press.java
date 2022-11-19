package com.press.entity;

import java.io.Serializable;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.press.entity
 * @Author: RainGrd
 * @CreateTime: 2022-05-19  15:15
 * @Description: TODO
 * @Version: 1.0
 */
public class Press implements Serializable {

    private static final long serialVersionUID = 4633898715539978337L;
    /*当前页码*/
    private int page;
    /*查询的多少*/
    private int limit;
    /*新闻编号*/
    private Long pressId;
    /*标题*/
    private String pressHead;
    /*发表时间*/
    private String pressCreateDate;
    /*创建人*/
    private String username;
    /*摘要*/
    private String pressAbstract;
    /*内容*/
    private String pressContent;
    /*状态*/
    private Integer pressStatus;
    /*分类*/
    private Integer motifId;
    /*是否置顶*/
    private Integer pressTop;

    /*访问量*/
    private Long pressTraffic;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Long getPressId() {
        return pressId;
    }

    public void setPressId(Long pressId) {
        this.pressId = pressId;
    }

    public String getPressHead() {
        return pressHead;
    }

    public void setPressHead(String pressHead) {
        this.pressHead = pressHead;
    }

    public String getPressCreateDate() {
        return pressCreateDate;
    }

    public void setPressCreateDate(String pressCreateDate) {
        this.pressCreateDate = pressCreateDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPressAbstract() {
        return pressAbstract;
    }

    public void setPressAbstract(String pressAbstract) {
        this.pressAbstract = pressAbstract;
    }

    public String getPressContent() {
        return pressContent;
    }

    public void setPressContent(String pressContent) {
        this.pressContent = pressContent;
    }

    public Integer getPressStatus() {
        return pressStatus;
    }

    public void setPressStatus(Integer pressStatus) {
        this.pressStatus = pressStatus;
    }

    public Integer getMotifId() {
        return motifId;
    }

    public void setMotifId(Integer motifId) {
        this.motifId = motifId;
    }

    public String getMotifIdStr() {
        String str = "";
        if (this.motifId == 0) {
            str = "互联网";
        } else if (this.motifId == 1) {
            str = "军事";
        } else if (this.motifId == 2) {
            str = "国内";
        } else if (this.motifId == 3) {
            str = "国际";
        } else if (this.motifId == 4) {
            str = "体育";
        } else if (this.motifId == 5) {
            str = "科技";
        } else if (this.motifId == 6) {
            str = "财经";
        } else if (this.motifId == 7) {
            str = "娱乐";
        } else if (this.motifId == 8) {
            str = "其他";
        }
        return str;
    }

    public Integer getPressTop() {
        return pressTop;
    }

    public void setPressTop(Integer pressTop) {
        this.pressTop = pressTop;
    }

    public Long getPressTraffic() {
        return pressTraffic;
    }

    public void setPressTraffic(Long pressTraffic) {
        this.pressTraffic = pressTraffic;
    }

    @Override
    public String toString() {
        return "Press{" +
                "page=" + page +
                ", limit=" + limit +
                ", pressId=" + pressId +
                ", pressHead='" + pressHead + '\'' +
                ", pressCreateDate='" + pressCreateDate + '\'' +
                ", username='" + username + '\'' +
                ", pressAbstract='" + pressAbstract + '\'' +
                ", pressContent='" + pressContent + '\'' +
                ", pressStatus=" + pressStatus +
                ", motifId=" + motifId +
                ", pressTop=" + pressTop +
                ", pressTraffic=" + pressTraffic +
                '}';
    }
}
