package com.bdqn.Vo;

import java.util.List;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.bdqn.Vo
 * @Author: RainGrd
 * @CreateTime: 2022-05-28  11:28
 * @Description: TODO
 * @Version: 1.0
 */
public class BrandVo<T> {
    /*数据*/
    private List<T> data;
    /*总数*/
    private Integer totalCount;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "BrandVo{" +
                "data=" + data +
                ", totalCount=" + totalCount +
                '}';
    }
}
