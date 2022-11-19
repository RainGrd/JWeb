package com.system.Vo;


import java.io.Serializable;
import java.util.List;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.system.vo
 * @Author: RainGrd
 * @CreateTime: 2022-05-15  20:38
 * @Description: TODO
 * @Version: 1.0
 */
public class TeacherVO implements Serializable {
    private static final long serialVersionUID = 1691220694801748138L;
    /*code msg count data*/
    private Integer code;
    private String msg;
    private Integer count;
    private List<Object> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
