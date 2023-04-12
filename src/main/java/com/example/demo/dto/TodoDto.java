package com.example.demo.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TodoDto
{
    @NonNull
    private final String task;

    private final int status;
}
