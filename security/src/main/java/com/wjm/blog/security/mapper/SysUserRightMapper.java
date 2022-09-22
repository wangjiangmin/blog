package com.wjm.blog.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author jiangmin.wang.qq.com    DATETIME: 2022/9/20-🍀16:21 @version 1.0
 * @description: 用户权限查询 mapper
 */
@Mapper
public interface SysUserRightMapper {

    /***
     * @description: 通过用户id 查询权限集合
     * @param userId
     * @return: * @return java.util.List<java.lang.String>

     * @author jiangming.wang@qq.com  DATETIME: 2022/9/20-🍀16:23
    **/
    @Select("<script> " +
            " select distinct sm.perms \n" +
            " from sys_user_role sur \n" +
            " left join sys_role sr on sur.role_id = sr.role_id \n" +
            " left join sys_role_menu srm on sr.role_id = srm.role_id \n" +
            " left join sys_menu sm on srm.menu_id = sm.menu_id \n" +
            " where sur.user_id = #{userId} \n" +
            " and sr.status = 0 \n" +
            " and sr.del_flag = 0 \n" +
            " and sm.del_flag = 0 \n" +
            " and sm.status = 0 " +
            " </script>")
    List<String> selectRightByUserId(@Param("userId") long userId);
}
