CREATE TABLE `jp_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(2083) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `article_url` varchar(2083) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `title` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `title_ruby` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `publish_date` datetime NOT NULL,
  `create_time` datetime NOT NULL DEFAULT '1900-01-01 00:00:00',
  `modify_time` datetime NOT NULL DEFAULT '1900-01-01 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci

show CREATE TABLE jp_article;

CREATE TABLE `jp_article` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `url` varchar(2083) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `article_url` varchar(2083) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `title` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `title_ruby` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `publish_date` datetime NOT NULL,
  `create_time` datetime NOT NULL DEFAULT '1900-01-01 00:00:00',
  `modify_time` datetime NOT NULL DEFAULT '1900-01-01 00:00:00',
  PRIMARY KEY (`id`),
  FULLTEXT KEY `FULLTEXT_content` (`content`) /*!50100 WITH PARSER `ngram` */ 
) ENGINE=InnoDB AUTO_INCREMENT=1573234 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci

show CREATE DATABASE ut2;

CREATE DATABASE `ut2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */

-- drop table jp_article

-- delete from jp_article
select id,
url,
    title,
    content,
    title_ruby
from jp_article
limit 1, 100;

CREATE FULLTEXT INDEX FULLTEXT_content ON jp_article(content) WITH PARSER ngram;
CREATE FULLTEXT INDEX FULLTEXT_url ON jp_article(url) WITH PARSER ngram;

-- drop index FULLTEXT_url on jp_article
-- drop index FULLTEXT_content on jp_article

select count(1)
from jp_article
where match (content) AGAINST ('+巴哈' IN BOOLEAN MODE);

-- PARSER ngram not work for text type
select count(1)
from jp_article
where match (content) AGAINST ('几次三番');

select count(1)
from jp_article
where content like  '%公平%';

CREATE FULLTEXT INDEX FULLTEXT_title ON jp_article(title);

EXPLAIN
select id,title
from jp_article
where title like  'HOP%';

EXPLAIN
select id,title
from jp_article
where match (title) AGAINST ('HOP');


select count(1)
from jp_article
where match (content) AGAINST ('一心一意方文图晋安区房地产管理局营销业夏凡密探们赵政委上厕所谷立');

update jp_article set  content = '一心一意 方文 图晋安区房地产管理局营销业夏凡密探们赵政委上厕所谷立' where content ='一心一意 方文图晋安区房地产管理局营销业夏凡密探们赵政委上厕所谷立'

select count(1)
from jp_article
where match (content) AGAINST ('一心 一意');


select count(1)
from jp_article
where match (url) AGAINST ('孵化 公平 黄长荣 饰面');

select id,
    url,
    match (url) AGAINST ('孵化 公平 黄长荣 饰面') as relevance
from jp_article
order by relevance desc;

explain
select id,
    content,
    match (content) AGAINST ('几次三番') as relevance
from jp_article
where id > 16000
order by relevance desc;

select VERSION();




