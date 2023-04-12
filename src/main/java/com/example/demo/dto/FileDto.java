package com.example.demo.dto;

import com.example.demo.constant.FileType;
import lombok.Data;

@Data
public class FileDto
{
    private FileType fileType;
    private Long srcId;
}
