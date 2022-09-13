package com.wjm.blog.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author wjm;1778682202@qq.com    DATETIME: 2022/9/9-🍀14:41 @version 1.0
 * @description: TODO
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @RequestMapping({"/",""})
    public ModelAndView toIndex(ModelAndView mv){

        mv.setViewName("index");
        return mv;
    }

}
