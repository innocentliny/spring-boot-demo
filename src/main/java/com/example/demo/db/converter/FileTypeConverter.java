package com.example.demo.db.converter;

import java.util.stream.Stream;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.example.demo.constant.FileType;

@Converter(autoApply = true)
public class FileTypeConverter implements AttributeConverter<FileType, String>
{
    @Override
    public String convertToDatabaseColumn(FileType attribute)
    {
        return attribute != null? attribute.getCode() : null;
    }

    @Override
    public FileType convertToEntityAttribute(String dbData)
    {
        if(dbData == null)
        {
            return null;
        }

        return Stream.of(FileType.values())
                     .filter(type -> type.getCode().equals(dbData))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Invalid file type: " + dbData));
    }
}
