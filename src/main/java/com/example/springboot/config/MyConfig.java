package com.example.springboot.config;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.boot.actuate.audit.InMemoryAuditEventRepository;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import lombok.extern.slf4j.Slf4j;

/**
 * @see https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config.typesafe-configuration-properties.enabling-annotated-types
 */
@Configuration
@EnableConfigurationProperties(PropertiesConfig.class)
@PropertySource(value = "classpath:application-oauth2.yml", factory = YamlPropertySourceFactory.class)
@Slf4j
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

    // https://spring.io/guides/tutorials/spring-boot-oauth2/
    // https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter#configuring-httpsecurity
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        SimpleUrlAuthenticationFailureHandler handler = new SimpleUrlAuthenticationFailureHandler("/");

        http
            .authorizeRequests(a -> a
                .antMatchers("/", "/error", "/webjars/**", "/css/**", "/js/**", "/login").permitAll()
                .anyRequest().authenticated()
            )
            .exceptionHandling(e -> e
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            )
            .logout(l -> l
                .logoutSuccessUrl("/").permitAll()
            )
            .oauth2Login(o -> o
            .failureHandler(
                // anonymous class
                new SimpleUrlAuthenticationFailureHandler("/") {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException ,ServletException {
                        request.getSession().setAttribute("error.message", exception.getMessage());
                        super.onAuthenticationFailure(request, response, exception);
                    };
                })
            // lambda expression
            // (request, response, exception) -> {
			//     request.getSession().setAttribute("error.message", exception.getMessage());
			//     handler.onAuthenticationFailure(request, response, exception);
            // })
            );
        // @formatter:on
        return http.build();
    }

    // // https://stackoverflow.com/a/63876210/19120213
    // @Bean
    // public GrantedAuthoritiesMapper userAuthoritiesMapper() {
    // return (authorities) -> {
    // Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

    // authorities.forEach(authority -> {
    // if (authority instanceof OidcUserAuthority) {
    // OidcUserAuthority oidcUserAuthority = (OidcUserAuthority) authority;

    // OidcIdToken idToken = oidcUserAuthority.getIdToken();
    // OidcUserInfo userInfo = oidcUserAuthority.getUserInfo();

    // // Map the claims found in idToken and/or userInfo
    // // to one or more GrantedAuthority's and add it to mappedAuthorities
    // mappedAuthorities.add(oidcUserAuthority);
    // } else if (authority instanceof OAuth2UserAuthority) {
    // OAuth2UserAuthority oauth2UserAuthority = (OAuth2UserAuthority) authority;

    // Map<String, Object> userAttributes = oauth2UserAuthority.getAttributes();
    // // userAttributes.put("name", authority.)
    // log.info(userAttributes.toString());
    // log.info(new Gson().toJson(userAttributes));

    // // Map the attributes found in userAttributes
    // // to one or more GrantedAuthority's and add it to mappedAuthorities
    // mappedAuthorities.add(oauth2UserAuthority);
    // }
    // });

    // log.info(mappedAuthorities.toString());
    // log.info(new Gson().toJson(mappedAuthorities));

    // return mappedAuthorities;
    // };
    // }

}
