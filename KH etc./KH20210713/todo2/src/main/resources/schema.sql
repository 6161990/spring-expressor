CREATE TABLE `todoV2` (
                          `id` bigint(11) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'ID',
                          `title` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '제목',
                          `deadline` date NOT NULL COMMENT '날짜',
                          `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '상태',
                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
                          `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '변경일',
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `title` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
