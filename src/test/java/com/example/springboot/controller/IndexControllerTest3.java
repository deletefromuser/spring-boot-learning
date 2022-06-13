package com.example.springboot.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.springboot.service.IndexService;
import com.google.common.base.Charsets;

/**
 * article: https://spring.io/guides/gs/testing-web/
 */
@WithMockUser(username = "user")
@WebMvcTest(IndexController.class)
public class IndexControllerTest3 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IndexService service;

    /**
     * @throws Exception
     */
    @Test
    public void test_index()
            throws Exception {
        Mockito.when(service.index()).thenReturn("index");
        MediaType contentType = new MediaType(MediaType.TEXT_HTML, Charsets.UTF_8);
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

}
