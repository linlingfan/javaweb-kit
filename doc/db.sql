CREATE TABLE `growth_banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) NOT NULL COMMENT '跳转页面',
  `url_type` varchar(45) NOT NULL COMMENT '跳转页面类型：h5,公众号',
  `image_url` text NOT NULL COMMENT '活动图url',
  `type` varchar(45) NOT NULL COMMENT '分类:首页',
  `begin` datetime NOT NULL COMMENT '开始时间',
  `end` datetime NOT NULL COMMENT '结束时间',
  `sequence` int(11) NOT NULL COMMENT '顺序',
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='运营——banner';

CREATE TABLE `growth_collocation_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL COMMENT '姓名',
  `age` int(11) NOT NULL COMMENT '年龄',
  `occupation` varchar(45) NOT NULL COMMENT '职业',
  `height` int(11) NOT NULL COMMENT '身高',
  `weight` int(11) NOT NULL COMMENT '体重',
  `needed` text NOT NULL COMMENT '需求',
  `image_urls` text COMMENT '活动图url',
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='运营——搭配案例';

CREATE TABLE `growth_collocation_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL COMMENT '组名',
  `image_url` text COMMENT '配图url',
  `case_list` text COMMENT '案例组',
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='运营——案例组';

CREATE TABLE `growth_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` text NOT NULL COMMENT '图片url',
  `type` varchar(45) NOT NULL COMMENT '类型',
  `deleted` int(11) NOT NULL COMMENT '是否删除',
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='运营——图片库';


CREATE TABLE `ques_quiz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL COMMENT '小测验标题',
  `quiz_describe` text COMMENT '小测验描述',
  `type` varchar(45) DEFAULT NULL COMMENT '小测验类型',
  `finish` int(11) NOT NULL DEFAULT '0',
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='小测验类型表';

CREATE TABLE `ques_quiz_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quiz_id` int(11) NOT NULL,
  `sequence` int(11) NOT NULL COMMENT '顺序',
  `item_describe` text NOT NULL COMMENT '小测验item描述',
  `value_type` varchar(45) DEFAULT NULL COMMENT '单选/多选  1/n',
  `value_range` text COMMENT 'item值范围',
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='小测验细项表';

CREATE TABLE `ques_quiz_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quiz_id` int(11) NOT NULL,
  `result` varchar(45) NOT NULL COMMENT '结果',
  `result_describe` text NOT NULL COMMENT '描述',
  `result_point_upper_limit` int(11) DEFAULT NULL COMMENT '结果分数区间上限',
  `result_reference` varchar(200) DEFAULT NULL COMMENT '参照人',
  `result_img` text COMMENT '图片',
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='小测验结果表';

ALTER TABLE  `growth_collocation_case`
CHANGE COLUMN `age` `age` INT(11) NULL COMMENT '年龄' ,
CHANGE COLUMN `occupation` `occupation` VARCHAR(45) NULL COMMENT '职业' ,
CHANGE COLUMN `height` `height` INT(11) NULL COMMENT '身高' ,
CHANGE COLUMN `weight` `weight` INT(11) NULL COMMENT '体重' ;

ALTER TABLE `growth_banner`
ADD COLUMN `title` VARCHAR(45) NULL DEFAULT NULL COMMENT '标题 eg:素人改造' AFTER `type`,
ADD COLUMN `sub_title` VARCHAR(45) NULL DEFAULT NULL COMMENT '副标题 eg: REFORM MUSUME' AFTER `title`,
ADD COLUMN `intro` VARCHAR(200) NULL DEFAULT NULL COMMENT '简介 eg:文章文章文章' AFTER `sub_title`;

CREATE TABLE `growth_banner_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL COMMENT '组名',
  `type` varchar(45) NOT NULL COMMENT '分类:首页',
  `banner_list` text COMMENT 'banner组',
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `type_UNIQUE` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='运营——banner组';

ALTER TABLE  `growth_banner`
ADD COLUMN `font_color` VARCHAR(20) NULL DEFAULT NULL COMMENT 'banner字体颜色' AFTER `type`;

ALTER TABLE `growth_collocation_case`
CHANGE COLUMN `age` `age` VARCHAR(45) NULL DEFAULT NULL COMMENT '年龄' ,
CHANGE COLUMN `height` `height` VARCHAR(45) NULL DEFAULT NULL COMMENT '身高' ,
CHANGE COLUMN `weight` `weight` VARCHAR(45) NULL DEFAULT NULL COMMENT '体重' ;
