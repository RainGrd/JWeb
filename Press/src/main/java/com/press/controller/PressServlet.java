package com.press.controller;
/**
 * @BelongsProject: JWeb
 * @BelongsPackage: ${PACKAGE_NAME}
 * @Author: RainGrd
 * @CreateTime: 2022-05-20  13:45
 * @Description: TODO
 * @Version: 1.0
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.press.Vo.PressVO;
import com.press.entity.Press;
import com.press.entity.User;
import com.press.service.IPressService;
import com.press.service.impl.PressServiceImpl;
import com.press.tools.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/press/*")
public class PressServlet extends BaseServlet {
    private IPressService pressService=new PressServiceImpl();
    private ObjectMapper mapper = new ObjectMapper();
    private static final long serialVersionUID = -2706355904308156076L;

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*接收每页传入*/
        Press press = Util.parsing(Press.class, request);
        PressVO<Press> pressPressVO = pressService.selectPressVoCondition(press.getPage(), press.getLimit(), press);
        System.out.println(pressPressVO);
        response.getWriter().write(mapper.writeValueAsString(pressPressVO));
    }
    /**
     * @description: 查询全部
     * @author: RainGrd
     * @date: 2022/5/21 8:09:28
     * @param: null
     * @return: null
     **/
    public void selectPressAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PressVO<Press> pressPressVO = pressService.selectPressAll();
        response.getWriter().write(mapper.writeValueAsString(pressPressVO));
    }
    /**
     * @description:
     * @author: RainGrd
     * @date: 2022/5/21 14:07:34
     * @param: null
     * @return: null
     **/
    public void selectInitAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*接收每页传入*/
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        int currentPage=Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        PressVO<Press> pressPressVO = pressService.selectInitPressAll(currentPage, pageSize);
        response.getWriter().write(mapper.writeValueAsString(pressPressVO));
    }
    /**
     * @description: 批量删除
     * @author: RainGrd
     * @date: 2022/5/21 17:07:34
     * @param: null
     * @return: null
     **/
    public void deletePressAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int[] idList = Util.parsing(int[].class, request);
        pressService.deletePressAll(idList);
        response.getWriter().write("deleteAllSuccess");
    }
    /**
     * @description: 编辑新闻
     * @author: RainGrd
     * @date: 2022/5/22 9:46:23
     * @param: null
     * @return: null
     **/
    public void updatePress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Press press = Util.parsing(Press.class, request);
        pressService.updatePress(press);
        Press pressById = pressService.selectPressById(Integer.parseInt(String.valueOf(press.getPressId())));
        if(pressById.getPressStatus()==3){
            if (pressById.getPressTop()==1) {
                pressService.updatePress(press);
                response.getWriter().write("statusError");
            }
        }
        response.getWriter().write("updateSuccess");
        /*System.out.println(pressById.getPressStatus());
        System.out.println(press.getPressStatus().equals(pressById.getPressStatus()));
        if (press.getPressStatus().equals(pressById.getPressStatus())) {
            response.getWriter().write("statusError");
        }else{

        }
        if(press.getPressStatus()!=null){
            if (press.getPressStatus().equals(pressById.getPressStatus())) {
                response.getWriter().write("statusError");
            }
        }else{
            pressService.updatePress(press);
            *//*返回成功标识*//*
            response.getWriter().write("updateSuccess");
        }*/

    }

    /**
     * @description: 删除
     * @author: RainGrd
     * @date: 2022/5/22 15:59:25
     * @param: null
     * @return: null
     **/
    public void deletePress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String _id = request.getParameter("id");
        int id=Integer.parseInt(_id);
        pressService.deletePress(id);
        /*返回成功标识*/
        response.getWriter().write("deleteSuccess");
    }
    /**
     * @description: 新增
     * @author: RainGrd
     * @date: 2022/5/22 19:25:00
     * @param: null
     * @return: null
     **/
    public void insertPress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Press press = Util.parsing(Press.class, request);
        /*进行比较*/
        /*获取创始人*/
        HttpSession session = request.getSession();
        User user= (User) session.getAttribute("user");
        if(user!=null){
            press.setUsername(user.getUsername());
        }
        pressService.addPress(press);
        /*成功标识*/
        response.getWriter().write("insertSuccess");
    }
}
