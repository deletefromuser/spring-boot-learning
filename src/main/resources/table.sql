-- https://roytuts.com/embedded-hsql-database-with-spring/
DROP TABLE jp_article IF EXISTS;
CREATE TABLE jp_article (
  id integer identity primary key,
  url varchar(2083) ,
  article_url varchar(2083) ,
  title varchar(1000) ,
  title_ruby varchar(1000) ,
  content varchar(1000) ,
  publish_date datetime ,
  create_time datetime ,
  modify_time datetime
) ;