package com.example.demo.constant;

public enum FileType
{
    PROJ("P");

    private String code;

    FileType(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return this.code;
    }
}
