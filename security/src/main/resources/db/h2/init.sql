drop table if exists sys_user;
-- 系统用户表
CREATE TABLE IF NOT EXISTS sys_user
(
    user_id      long         NOT NULL AUTO_INCREMENT comment '用id',
    user_name    varchar(50)  not NULL COMMENT '用户名',
    nick_name    varchar(100) NOT NULL COMMENT '昵称',
    password     varchar(255) not NULL COMMENT '密码',
    status       int          not NULL default 0 COMMENT '用户状态 0:正常 1:停用',
    email        varchar(255)          DEFAULT NULL COMMENT '邮箱',
    phone_number varchar(50)           DEFAULT NULL COMMENT '电话',
    sex          int          not NULL COMMENT '性别 0:女 1:男',
    avatar       varchar(255)          default NULL COMMENT '头像',
    user_type    int          not NULL default 1 COMMENT '用户类型 0:管理员 1:普通用户',
    create_by_id long         not NULL COMMENT '创建人id',
    create_date  date         not NULL default now() COMMENT '创建日期',
    update_by_id long                  default NULL COMMENT '修改人id',
    update_date  date                  default NULL COMMENT '修改日期',
    del_flag     int                   default 0 comment '是否删除 0:存在   1:删除',
    PRIMARY KEY (user_id),
    UNIQUE KEY sys_user_user_name_unique_key (user_name)
)
;

drop table if exists sys_menu;
--  系统菜单表
create table sys_menu
(
    menu_id     long        not null auto_increment comment '菜单ID',
    menu_name   varchar(50) not null comment '菜单名称',
    parent_id   long         default 0 comment '父菜单ID',
    order_num   int          default 0 comment '显示顺序',
    path        varchar(200) default '' comment '路由地址',
    component   varchar(255) default null comment '组件路径',
    query       varchar(255) default null comment '路由参数',
    is_frame    int          default 1 comment '是否为外链（0是 1否）',
    is_cache    int          default 0 comment '是否缓存（0缓存 1不缓存）',
    menu_type   int         not null comment '菜单类型（1目录 2菜单 3按钮）',
    visible     int          default 0 comment '菜单状态（0显示 1隐藏）',
    status      int          default 0 comment '菜单状态（0正常 1停用）',
    perms       varchar(100) default null comment '权限标识',
    icon        varchar(100) default '#' comment '菜单图标',
    create_by   long        not null comment '创建者',
    create_time datetime     default now() comment '创建时间',
    update_by   long         default '' comment '更新者',
    update_time datetime comment '更新时间',
    del_flag    int          default 0 comment '是否删除 0:存在   1:删除',
    remark      varchar(500) default '' comment '备注',
    primary key (menu_id)
) engine=h2datebase auto_increment=2000 comment = '菜单权限表';


drop table if exists sys_role;
--  系统权限表
create table sys_role
(
    role_id             long         not null auto_increment comment '角色ID',
    role_name           varchar(30)  not null comment '角色名称',
    role_key            varchar(100) not null comment '角色权限字符串',
    role_sort           int          not null comment '显示顺序',
    data_scope          int          default 1 comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    menu_check_strictly int          default 1 comment '菜单树选择项是否关联显示 0:不显示 1:显示',
    dept_check_strictly int          default 1 comment '部门树选择项是否关联显示 0:不显示 1:显示',
    status              int          default 0 comment '角色状态（0正常 1停用）',
    del_flag            int          default 0 comment '删除标志（0代表存在 1代表删除）',
    create_by           long         not null comment '创建者',
    create_time         datetime     default now() comment '创建时间',
    update_by           long         default null comment '更新者',
    update_time         datetime     default null comment '更新时间',
    remark              varchar(500) default null comment '备注',
    primary key (role_id)
) engine=h2datebase auto_increment=100 comment = '角色信息表';
