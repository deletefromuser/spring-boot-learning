package com.example.springboot.config;

import java.util.Locale;
import java.util.concurrent.Executor;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.boot.actuate.audit.InMemoryAuditEventRepository;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import lombok.extern.slf4j.Slf4j;

/**
 * @see https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config.typesafe-configuration-properties.enabling-annotated-types
 */
@Configuration
// @EnableConfigurationProperties(PropertiesConfig.class)
// @PropertySource(value = "classpath:application-oauth2.yml", factory =
// YamlPropertySourceFactory.class)
@Slf4j
// @EnableKafka
@EnableAsync
public class MyConfig implements WebMvcConfigurer {

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL).addScript("classpath:table.sql")
                .addScript("classpath:data.sql").build();
        return db;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        return jdbcTemplate;
    }

    /**
     * @see http://c.biancheng.net/spring_boot/global.html
     */
    @Bean
    public LocaleResolver localeResolver(@Nullable Locale defaultLocale) {
        // return new LocaleResolver() {

        // @Override
        // public Locale resolveLocale(HttpServletRequest request) {
        // String l = request.getParameter("l");
        // // Locale locale = Locale.getDefault();
        // Locale locale = request.getLocales().nextElement();
        // if (StringUtils.hasText(l)) {
        // String[] s = l.split("_");
        // locale = new Locale(s[0], s[1]);
        // }
        // return locale;
        // }

        // /**
        // * no need to implement
        // */
        // @Override
        // public void setLocale(HttpServletRequest request, HttpServletResponse
        // response, Locale locale) {
        // }

        // };

        return new AcceptHeaderLocaleResolver() {

            @Override
            public Locale resolveLocale(HttpServletRequest request) {
                String l = request.getParameter("l");
                if (StringUtils.hasText(l)) {
                    String[] s = l.split("_");
                    return new Locale(s[0], s[1]);
                } else {
                    return super.resolveLocale(request);
                }
            }

        };
    }

    // https://stackoverflow.com/a/59115579/19120213
    @Bean
    public HttpTraceRepository httpTraceRepository() {
        return new InMemoryHttpTraceRepository();
    }

    // https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#actuator.auditing
    @Bean
    public AuditEventRepository auditEventRepository() {
        return new InMemoryAuditEventRepository();
    }

    // 声明一个线程池(并指定线程池的名字)
    @Bean("taskExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数5：线程池创建时候初始化的线程数
        executor.setCorePoolSize(5);
        // 最大线程数5：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
        executor.setMaxPoolSize(5);
        // 缓冲队列500：用来缓冲执行任务的队列
        executor.setQueueCapacity(500);
        // 允许线程的空闲时间60秒：当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
        executor.setKeepAliveSeconds(60);
        // 线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
        executor.setThreadNamePrefix("DailyAsync-");
        executor.initialize();
        return executor;
    }
}
