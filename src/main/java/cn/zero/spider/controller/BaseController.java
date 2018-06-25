package cn.zero.spider.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 蔡元豪
 * @date 2018/6/23 21:54
 */
public class BaseController {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String exceptionHandler(Exception e) {
        return e.getMessage();
    }

}
