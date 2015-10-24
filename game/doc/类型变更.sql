CREATE TABLE `platform_info` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `no` int(11) NOT NULL COMMENT '操作平台编号',
   `desc` varchar(20) DEFAULT NULL COMMENT '操作平台说明',
   `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作平台类型表';

insert into platform_info(`no`,`desc`) values(10,'web');
insert into platform_info(`no`,`desc`) values(11,'android');
insert into platform_info(`no`,`desc`) values(12,'IOS');



CREATE TABLE `user_from` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `no` int(11) NOT NULL COMMENT '用户来源编号',
   `desc` varchar(20) DEFAULT NULL COMMENT '用户来源说明',
   `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户来源表';

insert into user_from(`no`,`desc`) values(10,'主站');
insert into user_from(`no`,`desc`) values(11,'空间');
insert into user_from(`no`,`desc`) values(12,'360');
insert into user_from(`no`,`desc`) values(13,'快播');
insert into user_from(`no`,`desc`) values(14,'ku6');
insert into user_from(`no`,`desc`) values(15,'第三方QQ');
insert into user_from(`no`,`desc`) values(16,'第三方Weibo');


ALTER TABLE account_full_info modify `reg_term` int(4) DEFAULT '1010' COMMENT '组合字段：platform_info.no + user_from.no';

update account_full_info set reg_term = 1010 where reg_term = 2;
update account_full_info set reg_term = 1110 where reg_term = 3;
update account_full_info set reg_term = 1011 where reg_term = 4 or reg_term = 5;
update account_full_info set reg_term = 1111 where reg_term = 6;



ALTER TABLE user_login_history modify `type` int(4) DEFAULT '1010' COMMENT '组合字段：platform_info.no + user_from.no';

update user_login_history set type = 1010 where type = 2;
update user_login_history set type = 1110 where type = 3;
update user_login_history set type = 1011 where type = 4 or type = 5;
update user_login_history set type = 1111 where type = 6;


ALTER TABLE recharge_log add column from_type int(4) not null default 1010 comment '充值用户类型 组合字段：platform_info.no + user_from.no';

ALTER TABLE user_consume_log modify from_type int(4) not null default 1010 comment '消费用户类型 组合字段：platform_info.no + user_from.no';

-- 可能比较慢
update user_consume_log set from_type = 1010 where from_type = 0;
update user_consume_log set from_type = 1011 where from_type = 1;
update user_consume_log set from_type = 1010 where from_type = 2;
update user_consume_log set from_type = 1110 where from_type = 3;
update user_consume_log set from_type = 1111 where from_type = 4;

ALTER TABLE detection_version_native modify type int(4) not null default 1010 comment '升级检查表 组合字段：platform_info.no + user_from.no';
update detection_version_native set type = 1110 where type = 3;
update detection_version_native set type = 1210 where type = 4;
update detection_version_native set type = 1111 where type = 5;
update detection_version_native set type = 1110 where type = 6;
