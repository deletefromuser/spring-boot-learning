package cc.asako.autoconfigure;

import cc.asako.Asako;
import cc.asako.AsakoProperties;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AsakoProperties.class)
public class AsakoAutoConfiguration {

    // @Autowired
    // AsakoProperties asakoProperties;

    @Bean
    @ConditionalOnMissingBean
    public Asako asako(AsakoProperties asakoProperties) {
        Asako asako = new Asako();
        asako.setGreeting(asakoProperties.getGreeting());
        asako.setId(asakoProperties.getId());
        asako.setName(asakoProperties.getName());

        return asako;
    }

}
