package com.bdqn.mapper;

import com.bdqn.entity.Book;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.bdqn.mapper
 * @Author: RainGrd
 * @CreateTime: 2022-05-17  15:00
 * @Description: TODO
 * @Version: 1.0
 */
public interface BookMapper {
    /**
     * @description: 根据书名查询数据
     * @author: RainGrd
     * @date: 2022/5/17 15:05:23
     * @param: null
     * @return: null
     **/
    @ResultMap("bookResultMap")
    List<Book> selectBookName(String bookName);
    /**
     * @description: 查询所有
     * @author: RainGrd
     * @date: 2022/5/17 15:13:19
     * @param: null
     * @return: null
     **/
    @Select("select * from tb_book where delstatus=1")

    List<Book> selectBookList();
}
