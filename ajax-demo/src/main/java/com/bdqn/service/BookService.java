package com.bdqn.service;

import com.bdqn.entity.Book;
import com.bdqn.mapper.BookMapper;
import com.bdqn.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.bdqn.service
 * @Author: RainGrd
 * @CreateTime: 2022-05-17  15:00
 * @Description: TODO
 * @Version: 1.0
 */
public class BookService {
    private SqlSessionFactory sqlSessionFactory=SqlSessionFactoryUtils.getSqlSessionFactory();
    /**
     * @description: 查询所有
     * @author: RainGrd
     * @date: 2022/5/17 15:18:49
     * @param: null
     * @return: null
     **/
    public List<Book> selectAllBook(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        List<Book> books = mapper.selectBookList();
        sqlSession.close();
        return  books;
    }
    public List<Book> selectBook(String bookName){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        List<Book> books = mapper.selectBookName(bookName);
        return books;
    };
}
