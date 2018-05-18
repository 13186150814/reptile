package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 常培兵
 * @Description: 描述
 * @date 2018/5/12 14:47
 */
@Controller
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "login";
    }
    @RequestMapping("/test2")
    @ResponseBody
    public String test2(){
        return ClassUtils.getDefaultClassLoader().getResource("").getPath();
    }

    public static void main(String[] args) {
        String sss="http://img.xiumeim.com/data/1910/86/15260392491393.jpg";
        System.out.printf(sss.substring(sss.lastIndexOf('/')+1));
    }
}
