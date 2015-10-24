CREATE TABLE `platform_info` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `no` int(11) NOT NULL COMMENT '����ƽ̨���',
   `desc` varchar(20) DEFAULT NULL COMMENT '����ƽ̨˵��',
   `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='����ƽ̨���ͱ�';

insert into platform_info(`no`,`desc`) values(10,'web');
insert into platform_info(`no`,`desc`) values(11,'android');
insert into platform_info(`no`,`desc`) values(12,'IOS');



CREATE TABLE `user_from` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `no` int(11) NOT NULL COMMENT '�û���Դ���',
   `desc` varchar(20) DEFAULT NULL COMMENT '�û���Դ˵��',
   `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�û���Դ��';

insert into user_from(`no`,`desc`) values(10,'��վ');
insert into user_from(`no`,`desc`) values(11,'�ռ�');
insert into user_from(`no`,`desc`) values(12,'360');
insert into user_from(`no`,`desc`) values(13,'�첥');
insert into user_from(`no`,`desc`) values(14,'ku6');
insert into user_from(`no`,`desc`) values(15,'������QQ');
insert into user_from(`no`,`desc`) values(16,'������Weibo');


ALTER TABLE account_full_info modify `reg_term` int(4) DEFAULT '1010' COMMENT '����ֶΣ�platform_info.no + user_from.no';

update account_full_info set reg_term = 1010 where reg_term = 2;
update account_full_info set reg_term = 1110 where reg_term = 3;
update account_full_info set reg_term = 1011 where reg_term = 4 or reg_term = 5;
update account_full_info set reg_term = 1111 where reg_term = 6;



ALTER TABLE user_login_history modify `type` int(4) DEFAULT '1010' COMMENT '����ֶΣ�platform_info.no + user_from.no';

update user_login_history set type = 1010 where type = 2;
update user_login_history set type = 1110 where type = 3;
update user_login_history set type = 1011 where type = 4 or type = 5;
update user_login_history set type = 1111 where type = 6;


ALTER TABLE recharge_log add column from_type int(4) not null default 1010 comment '��ֵ�û����� ����ֶΣ�platform_info.no + user_from.no';

ALTER TABLE user_consume_log modify from_type int(4) not null default 1010 comment '�����û����� ����ֶΣ�platform_info.no + user_from.no';

-- ���ܱȽ���
update user_consume_log set from_type = 1010 where from_type = 0;
update user_consume_log set from_type = 1011 where from_type = 1;
update user_consume_log set from_type = 1010 where from_type = 2;
update user_consume_log set from_type = 1110 where from_type = 3;
update user_consume_log set from_type = 1111 where from_type = 4;

ALTER TABLE detection_version_native modify type int(4) not null default 1010 comment '�������� ����ֶΣ�platform_info.no + user_from.no';
update detection_version_native set type = 1110 where type = 3;
update detection_version_native set type = 1210 where type = 4;
update detection_version_native set type = 1111 where type = 5;
update detection_version_native set type = 1110 where type = 6;
