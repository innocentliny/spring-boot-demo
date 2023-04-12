package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.constant.ApplicationType;
import lombok.Value;

@Value
public class ApplicationSummaryDto
{
    private Long id;
    private ApplicationType type;
    private Long srcFileId;
    private LocalDateTime createdDttm;
}
