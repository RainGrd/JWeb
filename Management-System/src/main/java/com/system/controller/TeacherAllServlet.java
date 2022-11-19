package com.system.controller;
/**
 * @BelongsProject: JWeb
 * @BelongsPackage: ${PACKAGE_NAME}
 * @Author: RainGrd
 * @CreateTime: 2022-05-16  16:38
 * @Description: TODO
 * @Version: 1.0
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.Vo.TeacherVO;
import com.system.service.impl.TeacherImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/teacherAllServlet")
public class TeacherAllServlet extends HttpServlet {
    private static final long serialVersionUID = -5493803081919874823L;
    TeacherImpl teacherImpl=new TeacherImpl();
    private ObjectMapper mapper=new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        /*解析*/

        /*获取要传输的数据*/
        List<Object> teachers = teacherImpl.selectTeacherList();
        /*响应成功数据*/
        response.getWriter().write(mapper.writeValueAsString(packingTeacher(teachers)));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    /**
     * @description:
     * @author: RainGrd
     * @date: 2022/5/16 19:19:21
     * @param: null
     * @return: null
     **/
    public <T> TeacherVO packingTeacher(List<Object> lists){
        /*封装数据*/
        /*创建VO类进行数据传递*/
        TeacherVO teacherVO = new TeacherVO();
        teacherVO.setCode(0);
        /*设置长度*/
        teacherVO.setCount(lists.size());
        /*成功标识*/
        teacherVO.setMsg("success");
        teacherVO.setData(lists);
        return teacherVO;
    }
}
