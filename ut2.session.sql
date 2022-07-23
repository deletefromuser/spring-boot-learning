-- delete from users
EXPLAIN
SELECT *
from users
limit 1 CREATE FULLTEXT INDEX FULLTEXT_PASSword ON jp_article(content);
CREATE FULLTEXT INDEX FULLTEXT_title ON jp_article(title);

select count(1)
from jp_article
where match (title) AGAINST ('工作');

EXPLAIN
select id,
    title,
    match (title) AGAINST ('工作') as relevance
from jp_article
order by relevance desc;
-- delete from jp_article
select id,
url,
    title,
    content
from jp_article
limit 10, 100;

 show table status 

-- check table size in disk mb unit
SELECT (data_length + index_length) / power(1024, 2) tablesize_mb
FROM information_schema.tables
WHERE table_schema = 'ut2'
    and table_name = 'jp_article';
-- 创建全文索引
-- https://www.cnblogs.com/huanzi-qch/p/15238604.html
-- mairadb has not support ngram yet
-- https://jira.mariadb.org/browse/MDEV-10267
CREATE FULLTEXT INDEX FULLTEXT_content ON jp_article(content) WITH PARSER ngram;
SHOW CREATE TABLE `ut2`.`jp_article`;

CREATE FULLTEXT INDEX FULLTEXT_url ON jp_article(url);

select count(1)
from jp_article
where match (url) AGAINST ('呀');