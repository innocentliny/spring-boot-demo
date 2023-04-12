package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.constant.ApplicationType;
import com.example.demo.controller.ApplicationController;
import com.example.demo.dto.ApplicationDetailDto;
import com.example.demo.dto.FileDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
//@ActiveProfiles(profiles = {"integration_test"})
@EnabledIfSystemProperty(named = "spring.profiles.active", matches = "integration_test")
@AutoConfigureMockMvc
class SpringBootDemoApplicationTests
{
    @Autowired
    private ApplicationController appController;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${spring.profiles.active}")
    String activeProfiles;

    @Test
    void contextLoads()
    {
        System.out.println("active profile ----------------------" + activeProfiles);
        assertThat(appController).isNotNull();
    }

    @Test
    void testSubmitApplicationWithoutBodyAndGetBadRequest(@Autowired MockMvc mvc) throws Exception
    {
        mvc.perform(post("/app/v1")).andExpect(status().isBadRequest());
    }

    @Test
    void testSubmitApplicationAndGetNoContent(@Autowired MockMvc mvc) throws Exception
    {
        FileDto fileDto = new FileDto();
        fileDto.setSrcId(123L);
        ApplicationDetailDto dto = new ApplicationDetailDto();
        dto.setAppliedUserId("user@fake.app");
        dto.setType(ApplicationType.PUB_DL);
        dto.setFile(fileDto);
        mvc.perform(post("/app/v1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(dto))).andExpect(status().isNoContent());
    }
}
