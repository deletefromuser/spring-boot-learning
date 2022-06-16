package cc.asako;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.springboot.Application;

@SpringBootTest(classes = Application.class)
public class AsakoTest {

    @Autowired
    Asako asako;

    @Test
    void testAsako() {
        assertNotNull(asako);
        assertEquals(0, asako.getId());
        assertNull(asako.getName());
    }

}
