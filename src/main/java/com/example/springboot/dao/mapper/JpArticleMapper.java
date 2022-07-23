package com.example.springboot.dao.mapper;

import com.example.springboot.dao.model.JpArticle;
import com.example.springboot.dao.model.JpArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JpArticleMapper {
    long countByExample(JpArticleExample example);

    int deleteByExample(JpArticleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(JpArticle row);

    int insertSelective(JpArticle row);

    List<JpArticle> selectByExampleWithBLOBs(JpArticleExample example);

    List<JpArticle> selectByExample(JpArticleExample example);

    JpArticle selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") JpArticle row, @Param("example") JpArticleExample example);

    int updateByExampleWithBLOBs(@Param("row") JpArticle row, @Param("example") JpArticleExample example);

    int updateByExample(@Param("row") JpArticle row, @Param("example") JpArticleExample example);

    int updateByPrimaryKeySelective(JpArticle row);

    int updateByPrimaryKeyWithBLOBs(JpArticle row);

    int updateByPrimaryKey(JpArticle row);
}