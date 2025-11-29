USE smm_05_01;

-- Work Order Status Table
DROP TABLE IF EXISTS `work_order_status`;
CREATE TABLE `work_order_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT 'Status name',
  `description` varchar(256) DEFAULT NULL COMMENT 'Status description',
  `sort` int(11) DEFAULT 0 COMMENT 'Sort order',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Work order status table';

INSERT INTO `work_order_status` (`name`, `description`, `sort`) VALUES 
('待受理', '等待受理', 1),
('处理中', '处理中', 2),
('已解决', '已解决', 3),
('已关闭', '已关闭', 4);

-- Work Order Type Table
DROP TABLE IF EXISTS `work_order_type`;
CREATE TABLE `work_order_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT 'Type name',
  `description` varchar(256) DEFAULT NULL COMMENT 'Type description',
  `department_id` bigint(20) DEFAULT NULL COMMENT 'Responsible department ID',
  `sort` int(11) DEFAULT 0 COMMENT 'Sort order',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Work order type table';

INSERT INTO `work_order_type` (`name`, `description`, `department_id`, `sort`) VALUES 
('系统问题', '系统相关问题', 1, 1),
('业务咨询', '业务相关咨询', 4, 2),
('权限请求', '权限相关请求', 3, 3),
('功能建议', '功能相关建议', 1, 4);

-- Work Order Table
DROP TABLE IF EXISTS `work_order`;
CREATE TABLE `work_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL COMMENT 'Work order title',
  `content` text NOT NULL COMMENT 'Work order content',
  `type_id` bigint(20) NOT NULL COMMENT 'Work order type ID',
  `status_id` bigint(20) NOT NULL DEFAULT 1 COMMENT 'Work order status ID',
  `submitter_id` bigint(20) NOT NULL COMMENT 'Submitter user ID',
  `assignee_id` bigint(20) DEFAULT NULL COMMENT 'Assignee user ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation time',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
  `resolve_time` datetime DEFAULT NULL COMMENT 'Resolve time',
  `close_time` datetime DEFAULT NULL COMMENT 'Close time',
  `score` int(11) DEFAULT NULL COMMENT 'Submitter score',
  `comment` varchar(512) DEFAULT NULL COMMENT 'Submitter comment',
  PRIMARY KEY (`id`),
  KEY `idx_type_id` (`type_id`),
  KEY `idx_status_id` (`status_id`),
  KEY `idx_submitter_id` (`submitter_id`),
  KEY `idx_assignee_id` (`assignee_id`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Work order table';

-- Work Order Comment Table
DROP TABLE IF EXISTS `work_order_comment`;
CREATE TABLE `work_order_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `work_order_id` bigint(20) NOT NULL COMMENT 'Work order ID',
  `user_id` bigint(20) NOT NULL COMMENT 'User ID',
  `content` text NOT NULL COMMENT 'Comment content',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation time',
  PRIMARY KEY (`id`),
  KEY `idx_work_order_id` (`work_order_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Work order comment table';