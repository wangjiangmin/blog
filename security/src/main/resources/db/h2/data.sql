--	添加用户数据
INSERT INTO sys_user (user_id,user_name, nick_name, password, email, phone_number, sex, create_by, create_date)
values (1,'admin', 'h2o', '$2a$10$1lou.GFQJNBCYIeU2MSrk.L/00FWxegzhR106l1DkcHBPMmuFggCa', 'wangjiangmin@qq.com',
        '12345678901', 1, -1, now())
;
INSERT INTO sys_user (user_id,user_name, nick_name, password, email, phone_number, sex, create_by, create_date)
values (2,'test', 'h2o', '$2a$10$1lou.GFQJNBCYIeU2MSrk.L/00FWxegzhR106l1DkcHBPMmuFggCa', 'wangjiangmin@qq.com',
        '12345678901', 1, -1, now())
;

--  添加用户角色数据
INSERT INTO sys_user_role (user_id, role_id,create_by)
values (1, 1,-1)
;
INSERT INTO sys_user_role (user_id, role_id,create_by)
values (2, 2,-1)
;

--  添加角色数据
INSERT INTO sys_role (role_id,role_name,role_key,role_sort,create_by)
values (1,'管理员','',1,-1)
;
INSERT INTO sys_role (role_id,role_name,role_key,role_sort,create_by)
values (2,'测试','',1,-1)
;

--  添加角色菜单数据
INSERT INTO sys_role_menu (role_id, menu_id,create_by)
values (1, 1,-1)
;
INSERT INTO sys_role_menu (role_id, menu_id,create_by)
values (1, 2,-1)
;
INSERT INTO sys_role_menu (role_id, menu_id,create_by)
values (1, 3,-1)
;
INSERT INTO sys_role_menu (role_id, menu_id,create_by)
values (1, 4,-1)
;
INSERT INTO sys_role_menu (role_id, menu_id,create_by)
values (2, 3,-1)
;
INSERT INTO sys_role_menu (role_id, menu_id,create_by)
values (2, 4,-1)
;

--  添加菜单数据
INSERT INTO sys_menu (menu_id,menu_name,path,perms,menu_type,create_by)
values (1,'管理员权限菜单1','admin1','system:hello',2,-1)
;
INSERT INTO sys_menu (menu_id,menu_name,path,perms,menu_type,create_by)
values (2,'管理员权限菜单2','admin2','system:admin',2,-1)
;
INSERT INTO sys_menu (menu_id,menu_name,path,perms,menu_type,create_by)
values (3,'测试权限菜单1','test1','system:test',2,-1)
;
INSERT INTO sys_menu (menu_id,menu_name,path,perms,menu_type,create_by)
values (4,'测试权限菜单2','test2','system:test2',2,-1)
;