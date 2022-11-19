package com.bdqn.web; /**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：
 */

import com.bdqn.entity.Brand;
import com.bdqn.entity.User;
import com.bdqn.mapper.UserMapper;
import com.bdqn.tools.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@WebServlet("/demo1")
public class ServletDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*准备数据*/
        List<Brand> brands = new ArrayList<>();
        brands.add(new Brand(1, "三只松鼠", "三只松鼠", 100, "三只松鼠，好吃不上火", 1));
        brands.add(new Brand(2, "优衣库", "优衣库", 200, "优衣库，服适人生", 0));
        brands.add(new Brand(3, "小米", "小米科技有限公司", 1000, "为发烧而生", 1));
        /*获取工具类的SqlSessionFactory对象*/
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        /*获取sqlSession对象*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.selectUsers();
        /*存储数据*/
        request.setAttribute("brands",brands);
//        request.setAttribute("users",users);
//        request.setAttribute("status",1);
        /*转发到el-demo.jsp*/
//        request.getRequestDispatcher("/el-demo.jsp").forward(request, response);
        /*转发到jstl-if.jsp中*/
//        request.getRequestDispatcher("/jstl-if.jsp").forward(request, response);
        request.getRequestDispatcher("/jstl-foreach.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
