package com.book.mapper;

import com.book.entity.Book;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.book.mapper
 * @Author: RainGrd
 * @CreateTime: 2022-05-10  09:53
 * @Description: TODO
 * @Version: 1.0
 */
public interface BookMapper {
    /**
     * @description: 查找数据库所有的图书列表
     * @author: RainGrd
     * @date: 2022/5/10 9:54:35
     * @param: null
     * @return: null
     **/
    @Select("select * from tb_book")
    List<Book> selectBookAll();
    @Select("select * from tb_book where id=#{id};")
    Book selectBook(String id);
}
