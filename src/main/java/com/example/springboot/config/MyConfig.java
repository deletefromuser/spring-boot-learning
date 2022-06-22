package com.example.springboot.config;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
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
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import com.example.springboot.dao.mapper.UserMapper;
import com.example.springboot.dao.model.User;
import com.example.springboot.dao.model.UserExample;

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

    @Bean
    public WebClient rest(ClientRegistrationRepository clients, OAuth2AuthorizedClientRepository authz) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 = new ServletOAuth2AuthorizedClientExchangeFilterFunction(
                clients, authz);
        return WebClient.builder()
                .filter(oauth2).build();
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService(WebClient rest, UserMapper userMapper) {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        return new OAuth2UserService<OAuth2UserRequest, OAuth2User>() {
            @Override
            @Transactional
            public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
                OAuth2User user = delegate.loadUser(request);
                if (!"github".equals(request.getClientRegistration().getRegistrationId())) {
                    return user;
                }

                UserExample searchFilter = new UserExample();
                searchFilter.createCriteria().andProviderEqualTo("github")
                        .andOauthIdEqualTo(user.getAttribute("id").toString());
                List<User> userInfo = userMapper.selectByExample(searchFilter);
                if (userInfo.isEmpty()) {
                    if (userMapper.selectByPrimaryKey(user.getAttribute("login").toString()) == null) {
                        User newUser = new User(user.getAttribute("login"), "", true, "github",
                                user.getAttribute("id").toString());
                        userMapper.insert(newUser);
                    } else {
                        User newUser = new User(
                                user.getAttribute("login") + "_" + RandomStringUtils.randomAlphanumeric(6),
                                "", true,
                                "github", user.getAttribute("id").toString());
                        userMapper.insert(newUser);
                    }
                } else {
                    // do nothing
                }

                return user;
            }
        };
        // return request -> {
        // OAuth2User user = delegate.loadUser(request);
        // if (!"github".equals(request.getClientRegistration().getRegistrationId())) {
        // return user;
        // }

        // UserExample searchFilter = new UserExample();
        // searchFilter.createCriteria().andProviderEqualTo("github")
        // .andOauthIdEqualTo(user.getAttribute("id").toString());
        // List<User> userInfo = userMapper.selectByExample(searchFilter);
        // if (userInfo.isEmpty()) {
        // // TODO handle username duplicate case and lack of transaction
        // User newUser = new User(user.getAttribute("login"), "", true, "github",
        // user.getAttribute("id").toString());
        // userMapper.insert(newUser);
        // } else {
        // // do nothing
        // }

        // return user;

        // // OAuth2AuthorizedClient client = new
        // // OAuth2AuthorizedClient(request.getClientRegistration(), user.getName(),
        // // request.getAccessToken());
        // // String url = user.getAttribute("organizations_url");

        // // List<Map<String, Object>> orgs = rest
        // // .get().uri(url)
        // //
        // .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(client))
        // // .retrieve()
        // // .bodyToMono(List.class)
        // // .block();

        // // if (orgs.stream().anyMatch(org ->
        // // "spring-projects".equals(org.get("login")))) {
        // // return user;
        // // }

        // // throw new OAuth2AuthenticationException(new OAuth2Error("invalid_token",
        // "Not
        // // in Spring Team", ""));
        // };
    }

}
