package com.wjm.blog.security.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wjm;1778682202@qq.com    DATETIME: 2022/9/10-🍀14:36 @version 1.0
 * @description: 用户对象类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Uesr implements Serializable {

    //  用户id
    private Long userId;

    //  用户登录名
    private String userName;

    //  昵称
    private String nickName;

    //  密码
    private String password;

    //  用户状态 0:正常 1:停用
    private Integer status;

    //  邮箱
    private String email;

    //  手机号
    private String phoneNumber;

    //  性别 0:女 1:男
    private Integer sex;

    //  头像
    private String avatar;

    //  用户类型 0:管理员 1:普通用户
    private Integer userType;

    //  创建人id
    private Long createById;

    //  创建日期
    private Date createDate;

    //  更新人id
    private Long updateById;

    //  更新日期
    private Date updateDate;

    //  删除标记 1: 删除 0:存在
    private Integer delFlag;

}
