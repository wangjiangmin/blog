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
 * 角色信息表
 * </p>
 *
 * @author 王江民
 * @since 2022-09-21
 */
@Getter
@Setter
@TableName("sys_role")
@ApiModel(value = "SysRoleDb1对象", description = "角色信息表")
public class SysRoleDb1 extends Model<SysRoleDb1> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色ID")
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色权限字符串")
    private String roleKey;

    @ApiModelProperty("显示顺序")
    private Integer roleSort;

    @ApiModelProperty("数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）")
    private Integer dataScope;

    @ApiModelProperty("菜单树选择项是否关联显示 0:不显示 1:显示")
    private Integer menuCheckStrictly;

    @ApiModelProperty("部门树选择项是否关联显示 0:不显示 1:显示")
    private Integer deptCheckStrictly;

    @ApiModelProperty("角色状态（0正常 1停用）")
    private Integer status;

    @ApiModelProperty("删除标志（0代表存在 1代表删除）")
    private Integer delFlag;

    @ApiModelProperty("创建者")
    private Long createBy;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新者")
    private Long updateBy;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("备注")
    private String remark;


    @Override
    public Serializable pkVal() {
        return this.roleId;
    }

}
