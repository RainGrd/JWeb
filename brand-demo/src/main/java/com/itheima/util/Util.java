package com.itheima.util;

import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;

import java.util.List;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.itheima.util
 * @Author: RainGrd
 * @CreateTime: 2022-05-12  14:54
 * @Description: TODO
 * @Version: 1.0
 */
public class Util {
    private static BrandService brandService = new BrandService();
    /**
     * @description: 用于判断字符串是不是数字
     * @author: RainGrd
     * @date: 2022/5/12 14:43:46
     * @param: null
     * @return: null
     **/
    public static boolean isNumber(String number){
        return number.matches("-?[0-9]+.?[0-9]*");
    }
    /**
     * @description: 根据参数判断返回的值是否是null还是参数自己
     * @author: RainGrd
     * @date: 2022/5/12 14:55:49
     * @param: null
     * @return: null
     **/
    public static List<Brand> brandIsNull(List<?> brandList){
        if(brandList!=null){
            return (List<Brand>) brandList;
        }
        return brandService.selectAll();
    }
}
