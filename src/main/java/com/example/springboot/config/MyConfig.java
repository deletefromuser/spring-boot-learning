package com.example.springboot.config;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

/**
 * @see https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config.typesafe-configuration-properties.enabling-annotated-types
 */
@Configuration
@EnableConfigurationProperties(PropertiesConfig.class)
public class MyConfig implements WebMvcConfigurer {

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

}
