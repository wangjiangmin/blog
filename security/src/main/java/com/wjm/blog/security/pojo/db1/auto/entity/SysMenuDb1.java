package com.wjm.blog.security.pojo.db1.auto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author 王江民
 * @since 2022-09-20
 */
@Getter
@Setter
@TableName("sys_menu")
@ApiModel(value = "SysMenuDb1对象", description = "菜单权限表")
public class SysMenuDb1 extends Model<SysMenuDb1> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("菜单ID")
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    @ApiModelProperty("菜单名称")
    private String menuName;

    @ApiModelProperty("父菜单ID")
    private Long parentId;

    @ApiModelProperty("显示顺序")
    private Integer orderNum;

    @ApiModelProperty("路由地址")
    private String path;

    @ApiModelProperty("组件路径")
    private String component;

    @ApiModelProperty("路由参数")
    private String query;

    @ApiModelProperty("是否为外链（0是 1否）")
    private Integer isFrame;

    @ApiModelProperty("是否缓存（0缓存 1不缓存）")
    private Integer isCache;

    @ApiModelProperty("菜单类型（1目录 2菜单 3按钮）")
    private Integer menuType;

    @ApiModelProperty("菜单状态（0显示 1隐藏）")
    private Integer visible;

    @ApiModelProperty("菜单状态（0正常 1停用）")
    private Integer status;

    @ApiModelProperty("权限标识")
    private String perms;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("创建者")
    private Long createBy;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新者")
    private Long updateBy;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("是否删除 0:存在   1:删除")
    private Integer delFlag;

    @ApiModelProperty("备注")
    private String remark;


    @Override
    public Serializable pkVal() {
        return this.menuId;
    }

}
