package com.wjm.blog.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author wjm;1778682202@qq.com    DATETIME: 2022/9/9-🍀14:41 @version 1.0
 * @description: 主页访问
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @RequestMapping({"/",""})
    public ModelAndView toIndex(ModelAndView mv){

        mv.setViewName("index");
        return mv;
    }

    @RequestMapping({"/hello"})
    @PreAuthorize("hasAuthority('test')")
    public ModelAndView toHello(ModelAndView mv){

        mv.setViewName("hello");
        return mv;
    }

    @RequestMapping({"/admin"})
    @PreAuthorize("hasAuthority('admin')")
    public ModelAndView toAdmin(ModelAndView mv){

        mv.setViewName("admin");
        return mv;
    }


}
