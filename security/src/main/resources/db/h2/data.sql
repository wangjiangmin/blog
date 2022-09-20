--	添加用户数据
INSERT INTO `user` (user_name,nick_name,password,email,phone_number,sex,create_by_id,create_date)
select 'test','h2o','$2a$10$1lou.GFQJNBCYIeU2MSrk.L/00FWxegzhR106l1DkcHBPMmuFggCa','wangjiangmin@qq.com','12345678901',1,-1,now(),
from DUAL
where not exists(select user_id from user where user_name='test')
;
INSERT INTO `user` (user_name,nick_name,password,email,phone_number,sex,create_by_id,create_date)
select 'admin','h2o','$2a$10$1lou.GFQJNBCYIeU2MSrk.L/00FWxegzhR106l1DkcHBPMmuFggCa','wangjiangmin@qq.com','12345678901',1,-1,now(),
from DUAL
where not exists(select user_id from user where user_name='admin')
;