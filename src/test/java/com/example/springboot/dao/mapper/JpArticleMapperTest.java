package com.example.springboot.dao.mapper;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.util.buf.StringUtils;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.springboot.dao.model.JpArticle;

@SpringBootTest
public class JpArticleMapperTest {

    @Autowired
    JpArticleMapper mapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    void testBatchInsert() throws IOException {
        final List<String> wordlist = Files.readAllLines(new ClassPathResource("wordlist.txt").getFile().toPath());
        final List<String> wordlistCh = Files
                .readAllLines(new ClassPathResource("chinese-word-list.txt").getFile().toPath());
        EasyRandom easyRandom = new EasyRandom();

        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);) {
            JpArticleMapper mapper = sqlSession.getMapper(JpArticleMapper.class);

            for (int i = 0; i < 200; i++) {

                for (int j = 0; j < 500; j++) {
                    JpArticle article = easyRandom.nextObject(JpArticle.class);
                    article.setId(null);

                    article.setTitleRuby(StringUtils.join(IntStream.range(1, 5)
                            .mapToObj(index -> RandomStringUtils.randomAlphabetic(3, 10))
                            .collect(Collectors.toList()), ' '));
                    article.setTitle(StringUtils.join(IntStream.range(1, 5)
                            .mapToObj(index -> wordlist.get(RandomUtils.nextInt(0, wordlist.size())))
                            .collect(Collectors.toList()), ' '));
                    article.setContent(IntStream.range(1, 10)
                            .mapToObj(index -> wordlistCh.get(RandomUtils.nextInt(0, wordlistCh.size())))
                            .collect(Collectors.joining()));
                    article.setUrl(StringUtils.join(IntStream.range(1, 5)
                            .mapToObj(index -> wordlistCh.get(RandomUtils.nextInt(0, wordlistCh.size())))
                            .collect(Collectors.toList()), ' '));
                    mapper.insert(article);
                }
                sqlSession.commit();
            }

        }
    }
}
