/*
SQL script for document module
*/

USE `smm_06_01`;

/*Table structure for table `directory` */

DROP TABLE IF EXISTS `directory`;

CREATE TABLE `directory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT 'Directory name',
  `parent_id` bigint(20) DEFAULT '0' COMMENT 'Parent directory ID, 0 means root',
  `create_by` bigint(20) NOT NULL COMMENT 'Created by user ID',
  `create_time` datetime NOT NULL COMMENT 'Creation time',
  `update_by` bigint(20) NOT NULL COMMENT 'Updated by user ID',
  `update_time` datetime NOT NULL COMMENT 'Update time',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`),
  KEY `idx_create_by` (`create_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Directory table';

/*Table structure for table `document` */

DROP TABLE IF EXISTS `document`;

CREATE TABLE `document` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL COMMENT 'Document title',
  `content` longtext COMMENT 'Document content',
  `directory_id` bigint(20) DEFAULT '0' COMMENT 'Directory ID, 0 means root',
  `create_by` bigint(20) NOT NULL COMMENT 'Created by user ID',
  `create_time` datetime NOT NULL COMMENT 'Creation time',
  `update_by` bigint(20) NOT NULL COMMENT 'Updated by user ID',
  `update_time` datetime NOT NULL COMMENT 'Update time',
  `status` tinyint(4) DEFAULT '0' COMMENT '0: draft, 1: published, 2: archived',
  PRIMARY KEY (`id`),
  KEY `idx_directory_id` (`directory_id`),
  KEY `idx_create_by` (`create_by`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Document table';

/*Add permissions for document module*/

INSERT INTO `u_permission`(`name`, `url`) 
VALUES 
('文档管理', '/document/index.shtml'),
('文档列表', '/document/list'),
('文档搜索', '/document/search'),
('文档详情', '/document/get'),
('文档保存', '/document/saveOrUpdate'),
('文档删除', '/document/delete'),
('文档批量删除', '/document/batchDelete'),
('文档状态更新', '/document/updateStatus'),
('目录树', '/directory/tree'),
('目录列表', '/directory/list'),
('目录详情', '/directory/get'),
('目录子项', '/directory/children'),
('目录保存', '/directory/saveOrUpdate'),
('目录删除', '/directory/delete'),
('目录批量删除', '/directory/batchDelete');

/*Add permissions to system admin role*/

SET @admin_role_id = (SELECT id FROM u_role WHERE type = '888888');

INSERT INTO `u_role_permission`(`rid`, `pid`) 
SELECT @admin_role_id, id FROM u_permission WHERE url LIKE '/document/%' OR url LIKE '/directory/%';

/*Add permissions to permission role*/

SET @permission_role_id = (SELECT id FROM u_role WHERE type = '100003');

INSERT INTO `u_role_permission`(`rid`, `pid`) 
SELECT @permission_role_id, id FROM u_permission WHERE url LIKE '/document/%' OR url LIKE '/directory/%';

/*Add permissions to user center role*/

SET @user_center_role_id = (SELECT id FROM u_role WHERE type = '100002');

INSERT INTO `u_role_permission`(`rid`, `pid`) 
SELECT @user_center_role_id, id FROM u_permission WHERE url LIKE '/document/%' OR url LIKE '/directory/%';
