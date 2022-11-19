package com.bdqn.utils;

import lombok.*;

import java.util.List;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.bdqn.utils
 * @Author: RainGrd
 * @CreateTime: 2022-05-30  09:46
 * @Description: 分页工具类
 * @Version: 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageUtil<E> {
    /**
     * @description: 页中的数据
     * @author: RainGrd
     * @date: 2022/5/30 10:01:21
     **/
    private List<E> data;
    /**
     * @description: 总条数
     * @author: RainGrd
     * @date: 2022/5/30 10:01:37
     **/
    @NonNull
    private int totalCount;
    /**
     * @description: 总页数
     * @author: RainGrd
     * @date: 2022/5/30 10:29:39
     **/
    private int countPages;
    /**
     * @description: 每一行的页数
     * @author: RainGrd
     * @date: 2022/5/30 10:02:14
     **/
    private int pageSize;
    /**
     * @description: 当前页数
     * @author: RainGrd
     * @date: 2022/5/30 10:05:52
     **/
    private int pageNumber;
    /**
     * @description: 下一页
     * @author: RainGrd
     * @date: 2022/5/30 10:07:28
     **/
    private boolean nextPage;
    /**
     * @description: 上一页
     * @author: RainGrd
     * @date: 2022/5/30 10:09:39
     **/
    private boolean upPage;
    /**
     * @description: 开始页
     * @author: RainGrd
     * @date: 2022/5/30 10:11:13
     **/
    private int startPage;
    /**
     * @description: 结束页
     * @author: RainGrd
     * @date: 2022/5/30 10:11:42
     **/
    private int endPage;

    public PageUtil(List<E> data, @NonNull int totalCount, int pageSize, int pageNumber) {
        this.data = data;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.countPages=totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
        this.startPage=1;
        this.endPage=countPages;
        this.upPage=pageNumber!=1;
        this.nextPage = pageNumber!=endPage;
    }
}
