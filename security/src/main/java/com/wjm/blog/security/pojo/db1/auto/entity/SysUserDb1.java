package com.wjm.blog.security.pojo.db1.auto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 王江民
 * @since 2022-09-20
 */
@Getter
@Setter
@TableName("sys_user")
@ApiModel(value = "SysUserDb1对象", description = "")
public class SysUserDb1 extends Model<SysUserDb1> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用id")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("用户状态 0:正常 1:停用")
    private Integer status;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("电话")
    private String phoneNumber;

    @ApiModelProperty("性别 0:女 1:男")
    private Integer sex;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("用户类型 0:管理员 1:普通用户")
    private Integer userType;

    @ApiModelProperty("创建人id")
    private Long createById;

    @ApiModelProperty("创建日期")
    private LocalDate createDate;

    @ApiModelProperty("修改人id")
    private Long updateById;

    @ApiModelProperty("修改日期")
    private LocalDate updateDate;

    @ApiModelProperty("是否删除 0:存在   1:删除")
    private Integer delFlag;


    @Override
    public Serializable pkVal() {
        return this.userId;
    }

}
