package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.constant.ApplicationType;
import lombok.Data;

@Data
public class ApplicationSummaryDtoV2
{
    private Long id;
    private ApplicationType type;
    private String appliedUserId;
    private FileDto file;
    private LocalDateTime createdDttm;
}
