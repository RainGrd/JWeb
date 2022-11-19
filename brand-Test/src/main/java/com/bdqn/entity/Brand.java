package com.bdqn.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.bdqn.entity
 * @Author: RainGrd
 * @CreateTime: 2022-05-28  10:16
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@ToString
public class Brand implements Serializable {

    private static final long serialVersionUID = -3485370200159291388L;
    /*private int currentPage;
    private int pageSize;*/
    // id 主键
    private Integer id;
    // 品牌名称
    private String brandName;
    // 企业名称
    private String companyName;
    // 排序字段
    private Integer ordered;
    // 描述信息
    private String description;
    // 状态：0：禁用  1：启用
    private Integer status;
    private Integer delStatus;
    public Brand() {
    }

    public Brand(Integer id, String brandName, String companyName, String description) {
        this.id = id;
        this.brandName = brandName;
        this.companyName = companyName;
        this.description = description;
    }

    public Brand(Integer id, String brandName, String companyName, Integer ordered, String description, Integer status) {
        this.id = id;
        this.brandName = brandName;
        this.companyName = companyName;
        this.ordered = ordered;
        this.description = description;
        this.status = status;
    }

    /*public String getStatusStr() {
        if (this.status == 1) {
            return "启用";
        }
        return "弃用";
    }*/
}
