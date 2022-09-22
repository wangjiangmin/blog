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
    @PreAuthorize("permitAll()")
    public ModelAndView toIndex(ModelAndView mv){

        mv.setViewName("index");
        return mv;
    }

    @RequestMapping({"/hello"})
    @PreAuthorize("hasAuthority('system:hello')")
    public ModelAndView toHello(ModelAndView mv){

        mv.setViewName("hello");
        return mv;
    }

    @RequestMapping({"/admin"})
    @PreAuthorize("hasAuthority('system:admin')")
    public ModelAndView toAdmin(ModelAndView mv){

        mv.setViewName("admin");
        return mv;
    }

    @RequestMapping({"/test"})
    @PreAuthorize("hasAuthority('system:test')")
    public String toTest(ModelAndView mv){

        return "test";
    }

    @RequestMapping({"/test2"})
    @PreAuthorize("hasAuthority('system:test2')")
    public String toTest2(ModelAndView mv){

        return "test2";
    }


}
