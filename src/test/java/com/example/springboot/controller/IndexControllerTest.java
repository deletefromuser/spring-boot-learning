package com.example.springboot.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.common.base.Charsets;

/**
 * @see https://spring.io/guides/gs/testing-web/
 * @see https://stackoverflow.com/a/51789881/19120213
 */

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = "spring.main.lazy-initialization=true")
@WithMockUser(username = "user")
public class IndexControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity()).build();
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
}
