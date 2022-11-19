package com.book.service;

import com.book.entity.Book;
import com.book.mapper.BookMapper;
import com.book.tools.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.book.service
 * @Author: RainGrd
 * @CreateTime: 2022-05-10  10:13
 * @Description: TODO
 * @Version: 1.0
 */
public class BookService implements BookMapper {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
    @Override
    public List<Book> selectBookAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        return mapper.selectBookAll();
    }

    @Override
    public Book selectBook(String id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        return  mapper.selectBook(id);
    }


}
