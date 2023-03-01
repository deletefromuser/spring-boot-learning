package com.example.springboot.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import com.google.common.base.Charsets;

import lombok.extern.slf4j.Slf4j;

/**
 * @see https://spring.io/guides/gs/testing-web/
 * @see https://stackoverflow.com/a/51789881/19120213
 */

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = "spring.main.lazy-initialization=true")
// @WithMockUser(username = "user")
@Slf4j
public class IndexControllerTest {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc() {
        // mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        //         .apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @Test
    public void test_index()
            throws Exception {
        MediaType contentType = new MediaType(MediaType.TEXT_HTML, Charsets.UTF_8);
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @Test
    public void test_port()
            throws Exception {
        MediaType contentType = new MediaType(MediaType.TEXT_HTML, Charsets.UTF_8);
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    // @Test
    // public void test_user() throws Exception {
    //     Authentication authenticationByManual = SecurityContextHolder.getContext().getAuthentication();

    //     log.info(authenticationByManual.toString());
    //     assertEquals("user", authenticationByManual.getName());
    // }

}
