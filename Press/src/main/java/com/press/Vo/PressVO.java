package com.press.Vo;


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
public class PressVO<T> implements Serializable {
    private static final long serialVersionUID = 1691220694801748138L;
    /*code msg count data*/
    private int code;
    private String msg;
    private int count;
    private List<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
