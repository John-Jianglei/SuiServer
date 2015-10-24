set names utf8;
CREATE DATABASE `update` default charset utf8 COLLATE utf8_general_ci;

use update;
drop table if exists `u_server_list`;
CREATE TABLE `u_server_list` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `server_no` int(10) NOT NULL COMMENT 'server编号',
  `channel_id` int(10) NOT NULL COMMENT '渠道Id 0:官网；1：360；2：QQ',
  `name` varchar(255) NOT NULL COMMENT 'server名称',
  `desc` varchar(255) NOT NULL COMMENT '服务器描述',
  `url` varchar(255) NOT NULL COMMENT '服务器url前缀:ip+port,以"/"结尾',
  `db_ip` varchar(100) NOT NULL COMMENT '数据库IP',
  `db_port` int(10) NOT NULL DEFAULT '3306' COMMENT '数据库端口',
  `db_name` varchar(50) NOT NULL COMMENT '数据库名称',
  `flag` int(10) NOT NULL DEFAULT '1' COMMENT '1:新;2:热',
  `status` int(10) NOT NULL DEFAULT '1' COMMENT '状态 0：不可用；1：可用',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务器列表';

insert into u_server_list(server_no,channel_id,name,url) values(1,2,'桑巴荣耀','http://203.195.163.33:9001/');

drop table if exists `u_version`;
CREATE TABLE `u_version` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `channel_id` int(10) NOT NULL COMMENT '渠道Id',
  `version` varchar(255) NOT NULL COMMENT '版本号,规则：a.b.c，其中a,b1位数字，c最多2位数字',
  `version_num` int(11) NOT NULL COMMENT '=a*1000+b*100+c，程序计算生成，主要方便版本比较',
  `url` varchar(255) NOT NULL COMMENT 'http全路径',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='版本更新表';

insert into u_version(channel_id,version,version_num,url) values(2,'1.0.5',1005,'');
insert into u_version(channel_id,version,version_num,url) values(2,'1.1.0',1100,'');


drop table if exists `u_update_way`;
CREATE TABLE `u_update_way` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `channel_id` int(10) NOT NULL COMMENT '渠道Id',
  `way` int(10) NOT NULL DEFAULT 0 COMMENT '0:友盟更新；1：官网更新;2:应用宝更新',
  `update_desc` varchar(255) NOT NULL COMMENT '更新方式描述',
  `status` int(4) NOT NULL default 1 COMMENT '是否启用 0：不启用；1:启用',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='更新方式表';

insert into u_update_way(channel_id,way,update_desc,status) values(2,2,'游戏有更新,请到应用宝重新下载并安装',1);

drop table if exists `u_notice`;
CREATE TABLE `u_notice` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `channel_id` int(10) NOT NULL COMMENT '渠道Id: -1代码所有渠道；0：官网；1：360；2：QQ',
  `notice_desc` varchar(255) NOT NULL COMMENT '服务器状态描述',
  `status` int(4) NOT NULL default 1 COMMENT '是否启用 0：不启用；1:启用',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务器公告表';

insert into u_notice(channel_id,notice_desc,status) values(2,'抱歉，本轮封测已结束，游戏登录暂时关闭。不过我们诚邀您参与下一轮技术测试，您可通过联系我们的官方客服（普约尔QQ：2803422923），申请报名。',0);


drop table if exists `u_update_whitelist`;
CREATE TABLE `u_update_whitelist` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `channel_id` int(10) NOT NULL COMMENT '渠道Id: -1代码所有渠道；0：官网；1：360；2：QQ',
  `uids` varchar(200) NULL COMMENT 'uid列表，以,分割',  
  `status` int(4) not null default 0 COMMENT '是否启动 0：不启用；1：启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='更新用户白名单';

drop table if exists `u_update_audit`;
CREATE TABLE `u_update_audit` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `channel_id` int(10) NOT NULL COMMENT '渠道号',
  `version` int(10) NOT NULL COMMENT '审核版本号',
  `status` int(4) not null default 0 COMMENT '是否启动 0：不启用；1：启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='审核服务器配置表';

drop table if exists `u_server_status`;
CREATE TABLE `u_server_status` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `channel_id` int(10) NOT NULL COMMENT '渠道号',
  `version` int(10) NOT NULL COMMENT '服务器支持版本号',
  `status` int(4) not null default 0 COMMENT '是否启动 0：不启用；1：启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务器版本状态信息';

drop table if exists `u_server_whitelist`;
CREATE TABLE `u_server_whitelist` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `channel_id` int(10) NOT NULL COMMENT '渠道Id: -1代码所有渠道；0：官网；1：360；2：QQ',
  `uids` varchar(500) NULL COMMENT 'uid列表，以,分割',  
  `status` int(4) not null default 0 COMMENT '是否启动 0：不启用；1：启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务器列表白名单';