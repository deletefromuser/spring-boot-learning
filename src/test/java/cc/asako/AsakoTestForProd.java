package cc.asako;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.springboot.Application;

@SpringBootTest(classes = Application.class)
@ActiveProfiles({ "prod" })
public class AsakoTestForProd {

    @Autowired
    Asako asako;

    @Test
    void testAsakoForProd() {
        assertNotNull(asako);
        assertEquals(3, asako.getId());
        assertEquals("asako", asako.getName());
    }

}
