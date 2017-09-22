# 数据库 
#创建数据库
DROP DATABASE IF EXISTS app_center_db;
CREATE DATABASE app_center_db;

#使用数据库 
use app_center_db;

#创建应用类型表 
CREATE TABLE app_cate_tb(
app_cate_id int(11) NOT NULL AUTO_INCREMENT COMMENT '应用类型id',
name varchar(255) COMMENT '应用类型名称',
update_date datetime COMMENT '更新时间',
PRIMARY KEY (app_cate_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='应用类型表';

#创建应用表 
CREATE TABLE app_tb(
app_id int(11) NOT NULL AUTO_INCREMENT COMMENT '应用id',
type tinyint(4) COMMENT '版面类型，1经典必玩，2热门推荐，3，最热必玩',
source varchar(255) COMMENT '应用来源',
version varchar(255) COMMENT '版本',
title varchar(255) COMMENT '应用名称',
summary longtext COMMENT '简介',
capacity decimal(11,2) COMMENT '容量，单位MB',
img_address varchar(255) COMMENT '封面图片',
content longtext COMMENT '应用内容',
pvs bigint(20) DEFAULT 0 COMMENT 'pv',
uvs bigint(20) DEFAULT 0 COMMENT 'uv',
ips bigint(20) DEFAULT 0 COMMENT 'ip',
reading_number bigint(20) DEFAULT 0 COMMENT '阅读数',
download_number bigint(20) DEFAULT 0 COMMENT '下载次数',
android_url varchar(255) COMMENT '安卓跳转url',
ios_url varchar(255) COMMENT 'IOS跳转url',
status tinyint(4) DEFAULT 1 COMMENT '下架0,上架1',
app_cate_id int(11) COMMENT '应用类型id,外键',
create_date datetime  COMMENT '创建时间',
update_date datetime  COMMENT '更新时间',
PRIMARY KEY (app_id),
INDEX INDEX_TYPE (type) USING BTREE,
INDEX INDEX_CAPACITY (capacity) USING BTREE,
INDEX INDEX_READINGNUMBER (reading_number) USING BTREE,
INDEX INDEX_DOWNLOADNUMBER (download_number) USING BTREE,
INDEX INDEX_APPCATEID (app_cate_id) USING BTREE,
INDEX INDEX_CREATEDATE (create_date) USING BTREE,
INDEX INDEX_UPDATEDATE (update_date) USING BTREE,
INDEX INDEX_STATUS (status) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='应用表';

#创建应用图片表 
CREATE TABLE app_img_tb(
app_img_id int(11) NOT NULL AUTO_INCREMENT COMMENT '应用图片id',
img_address varchar(255) COMMENT '应用图地址',
order_num int(11) COMMENT '排序数字',
update_date datetime  COMMENT '应用图片更新时间',
app_id int(11) COMMENT '应用id,外键',
PRIMARY KEY (app_img_id),
INDEX INDEX_APPID (app_id) USING BTREE,
INDEX INDEX_ORDERNUM (order_num) USING BTREE,
INDEX INDEX_UPDATEDATE (update_date) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='应用图片表';


#创建每日数据表 
CREATE TABLE daily_data_tb(
daily_data_id int(11) NOT NULL AUTO_INCREMENT COMMENT '数据id',
pvs bigint(20) COMMENT 'pvs',
uvs bigint(20) COMMENT 'uvs',
ips bigint(20) COMMENT 'ips',
reading_number bigint(20) COMMENT '阅读数',
create_date datetime COMMENT '创建时间',
app_id int(11) COMMENT '应用id外键',
acount_id int(11) COMMENT '账户id外键',
PRIMARY KEY (daily_data_id),
INDEX INDEX_CREATEDATE (create_date) USING BTREE,
INDEX INDEX_APPID (app_id) USING BTREE,
INDEX INDEX_ACOUNTID (acount_id) USING BTREE,
UNIQUE INDEX DAY_DATA (create_date,app_id,acount_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='每日数据表';

#创建数据表 
CREATE TABLE data_tb(
data_id int(11) NOT NULL AUTO_INCREMENT COMMENT '数据id',
pvs bigint(20) COMMENT 'pvs',
uvs bigint(20) COMMENT 'uvs',
ips bigint(20) COMMENT 'ips',
reading_number bigint(20) COMMENT '阅读数',
create_date datetime COMMENT '创建时间',
app_id int(11) COMMENT '应用id外键',
acount_id int(11) COMMENT '账户id外键',
PRIMARY KEY (data_id),
INDEX INDEX_CREATEDATE (create_date) USING BTREE,
INDEX INDEX_APPID (app_id) USING BTREE,
INDEX INDEX_ACOUNTID (acount_id) USING BTREE,
UNIQUE INDEX TIME_DATA (create_date,app_id,acount_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='数据表';