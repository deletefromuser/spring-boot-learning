CREATE TABLE `jp_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(2083) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `article_url` varchar(2083) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `title` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `title_ruby` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `publish_date` datetime NOT NULL,
  `create_time` datetime NOT NULL DEFAULT '1900-01-01 00:00:00',
  `modify_time` datetime NOT NULL DEFAULT '1900-01-01 00:00:00',
  PRIMARY KEY (`id`),
  FULLTEXT KEY `FULLTEXT_PASSword` (`content`),
  FULLTEXT KEY `FULLTEXT_title` (`title`),
  FULLTEXT KEY `FULLTEXT_url` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=1572234 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci


-- delete from jp_article
select id,
url,
    title,
    content
from jp_article
limit 10, 100;

CREATE FULLTEXT INDEX FULLTEXT_content ON jp_article(content) WITH PARSER ngram;
CREATE FULLTEXT INDEX FULLTEXT_url ON jp_article(url) WITH PARSER ngram;

drop index FULLTEXT_url on jp_article

select count(1)
from jp_article
where match (content) AGAINST ('+巴哈' IN BOOLEAN MODE);

select count(1)
from jp_article
where match (content) AGAINST ('公平');

select count(1)
from jp_article
where content like  '%公平%';

select count(1)
from jp_article
where match (url) AGAINST ('孵化 公平 黄长荣 饰面');

select id,
    url,
    match (url) AGAINST ('孵化 公平 黄长荣 饰面') as relevance
from jp_article
order by relevance desc;

select VERSION();

