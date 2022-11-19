package com.bdqn.Vo;

import lombok.Data;
import lombok.ToString;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.bdqn.Vo
 * @Author: RainGrd
 * @CreateTime: 2022-05-28  19:08
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@ToString
public class PageBean<T> {
    private int current;
    private int pageSize;
    private T pojo;
}
