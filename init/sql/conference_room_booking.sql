CREATE DATABASE /*!32312 IF NOT EXISTS*/`smm_04_01`/*!40100 DEFAULT CHARACTER SET utf8 */;

USE `smm_04_01`;

/*Table structure for table `conference_room` */

DROP TABLE IF EXISTS `conference_room`;

CREATE TABLE `conference_room` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL COMMENT '会议室名称',
  `capacity` int(11) NOT NULL COMMENT '容纳人数',
  `location` varchar(128) NOT NULL COMMENT '会议室位置',
  `equipment` varchar(256) DEFAULT NULL COMMENT '会议室设备',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态：1-可用，2-不可用',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  `creator` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会议室表';

/*Table structure for table `booking` */

DROP TABLE IF EXISTS `booking`;

CREATE TABLE `booking` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `room_id` bigint(20) NOT NULL COMMENT '会议室ID',
  `user_id` bigint(20) NOT NULL COMMENT '预订人ID',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态：1-待审批，2-已批准，3-已拒绝，4-已完成',
  `reason` varchar(256) DEFAULT NULL COMMENT '预订原因',
  `contact` varchar(32) DEFAULT NULL COMMENT '联系方式',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `fk_booking_room` (`room_id`),
  KEY `fk_booking_user` (`user_id`),
  CONSTRAINT `fk_booking_room` FOREIGN KEY (`room_id`) REFERENCES `conference_room` (`id`),
  CONSTRAINT `fk_booking_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预订表';

/*Table structure for table `booking_approval` */

DROP TABLE IF EXISTS `booking_approval`;

CREATE TABLE `booking_approval` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `booking_id` bigint(20) NOT NULL COMMENT '预订ID',
  `approver_id` bigint(20) NOT NULL COMMENT '审批人ID',
  `approval_status` int(11) NOT NULL DEFAULT '1' COMMENT '审批状态：1-待审批，2-已批准，3-已拒绝',
  `comment` varchar(256) DEFAULT NULL COMMENT '审批意见',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `fk_approval_booking` (`booking_id`),
  KEY `fk_approval_user` (`approver_id`),
  CONSTRAINT `fk_approval_booking` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`),
  CONSTRAINT `fk_approval_user` FOREIGN KEY (`approver_id`) REFERENCES `u_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预订审批表';

/*Data for the table `conference_room` */

insert into `conference_room`(`name`,`capacity`,`location`,`equipment`,`status`,`description`,`creator`,`create_time`) 
values ('会议室A', 10, '1号楼101室', '投影仪,白板', 1, '小型会议室，适合小组讨论', 'admin', now());

insert into `conference_room`(`name`,`capacity`,`location`,`equipment`,`status`,`description`,`creator`,`create_time`) 
values ('会议室B', 20, '1号楼102室', '投影仪,白板,音响', 1, '中型会议室，适合部门会议', 'admin', now());

insert into `conference_room`(`name`,`capacity`,`location`,`equipment`,`status`,`description`,`creator`,`create_time`) 
values ('会议室C', 50, '1号楼201室', '投影仪,白板,音响,视频会议系统', 1, '大型会议室，适合公司会议', 'admin', now());

/*Data for the table `booking` */

insert into `booking`(`room_id`,`user_id`,`start_time`,`end_time`,`status`,`reason`,`contact`,`create_time`) 
values (1, 1, '2024-05-20 10:00:00', '2024-05-20 11:00:00', 4, '项目讨论', '13800138000', '2024-05-18 10:00:00');

insert into `booking`(`room_id`,`user_id`,`start_time`,`end_time`,`status`,`reason`,`contact`,`create_time`) 
values (2, 11, '2024-05-20 14:00:00', '2024-05-20 15:00:00', 4, '部门会议', '13800138001', '2024-05-18 14:00:00');

/*Data for the table `booking_approval` */

insert into `booking_approval`(`booking_id`,`approver_id`,`approval_status`,`comment`,`create_time`) 
values (1, 1, 2, '批准', '2024-05-18 10:00:00');

insert into `booking_approval`(`booking_id`,`approver_id`,`approval_status`,`comment`,`create_time`) 
values (2, 1, 2, '批准', '2024-05-18 14:00:00');