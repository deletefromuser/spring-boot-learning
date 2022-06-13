package com.example.springboot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.common.base.Charsets;

/**
 * article: https://spring.io/guides/gs/testing-web/
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@WithMockUser(username = "user")
@AutoConfigureMockMvc
public class IndexControllerTest2 {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    /**
     * @throws Exception
     */
    @Test
    public void test_index()
            throws Exception {
        MediaType contentType = new MediaType(MediaType.TEXT_HTML, Charsets.UTF_8);
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    /**
     * @throws Exception
     */
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
