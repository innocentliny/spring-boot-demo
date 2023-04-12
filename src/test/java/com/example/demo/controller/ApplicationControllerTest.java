package com.example.demo.controller;

import com.example.demo.service.DaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test controller only.
 */
@WebMvcTest(controllers = {ApplicationController.class})
public class  ApplicationControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DaoService daoService;

    @Test
    void testCreateApplicationWithoutReqBody() throws Exception
    {
        this.mockMvc.perform(post("/app/v1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }
}
