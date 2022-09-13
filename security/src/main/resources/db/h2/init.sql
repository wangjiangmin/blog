--drop table if exists `user`;
-- 用户信息表
CREATE TABLE IF NOT EXISTS `user` (
    `user_id` long NOT NULL  AUTO_INCREMENT comment '用id',
    `user_name` varchar(50) not NULL COMMENT '用户名',
    `nick_name` varchar(100) NOT NULL COMMENT '昵称',
    `password` varchar(255) not NULL COMMENT '密码',
    `status` int not NULL default 0 COMMENT '用户状态 0:正常 1:停用',
    `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
    `phone_number` varchar(50) DEFAULT NULL COMMENT '电话',
    `sex` int not NULL COMMENT '性别 0:女 1:男',
    `avatar` varchar(255) not NULL COMMENT '头像',
    `user_type` int not NULL default 1 COMMENT '用户类型 0:管理员 1:普通用户',
    `create_by_id` long not NULL COMMENT '创建人id',
    `create_date` date not NULL COMMENT '创建日期',
    `update_by_id` long not NULL COMMENT '修改人id',
    `update_date` date not NULL COMMENT '修改日期',
    `del_flag` int default 0 comment '是否删除 0:删除   1:存在',
    PRIMARY KEY (`user_id`)
    )
;

show tables;
