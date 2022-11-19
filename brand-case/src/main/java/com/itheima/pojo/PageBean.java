package com.itheima.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.itheima.pojo
 * @Author: RainGrd
 * @CreateTime: 2022-05-18  15:48
 * @Description: TODO
 * @Version: 1.0
 */
public class PageBean<T> implements Serializable {
    private static final long serialVersionUID = 4480542477194369199L;
    /*总记录数*/
    private int totalCount;
    /*返回的当前页的list集合*/
    private List<T> rows;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
