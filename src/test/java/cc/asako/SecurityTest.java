package cc.asako;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.keygen.Base64StringKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityTest {
    @Test
    void testPassword() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("user")
                .build();
        log.info(user.getPassword());
        // {bcrypt}$2a$10$9etS3a6xT4DKEV/ZP29bXeA5UiU/hA4IsKXuNvoWbuF4ciEPLKGMG

        // Create an encoder with all the defaults
        Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder();
        String result = encoder.encode("myPassword");
        log.info(result);
        assertTrue(encoder.matches("myPassword", result));

        StringKeyGenerator keyGenerator = KeyGenerators.string();
        log.info(keyGenerator.getClass().toGenericString());
        log.info(keyGenerator.generateKey());
        keyGenerator = new Base64StringKeyGenerator(64);
        log.info(keyGenerator.generateKey());
        // keyGenerator = new
        // HexEncodingStringKeyGenerator(KeyGenerators.secureRandom(16));
        // log.info(keyGenerator.generateKey());
    }
}
