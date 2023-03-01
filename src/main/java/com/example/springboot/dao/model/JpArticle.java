package com.example.springboot.dao.model;

import java.time.LocalDateTime;

import lombok.ToString;

@ToString
public class JpArticle {
    private Long id;

    private String url;

    private String articleUrl;

    private String title;

    private String titleRuby;

    private LocalDateTime publishDate;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    private String content;

    public JpArticle(Long id, String url, String articleUrl, String title, String titleRuby, LocalDateTime publishDate, LocalDateTime createTime, LocalDateTime modifyTime) {
        this.id = id;
        this.url = url;
        this.articleUrl = articleUrl;
        this.title = title;
        this.titleRuby = titleRuby;
        this.publishDate = publishDate;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public JpArticle(Long id, String url, String articleUrl, String title, String titleRuby, LocalDateTime publishDate, LocalDateTime createTime, LocalDateTime modifyTime, String content) {
        this.id = id;
        this.url = url;
        this.articleUrl = articleUrl;
        this.title = title;
        this.titleRuby = titleRuby;
        this.publishDate = publishDate;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.content = content;
    }

    public JpArticle() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl == null ? null : articleUrl.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getTitleRuby() {
        return titleRuby;
    }

    public void setTitleRuby(String titleRuby) {
        this.titleRuby = titleRuby == null ? null : titleRuby.trim();
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

}