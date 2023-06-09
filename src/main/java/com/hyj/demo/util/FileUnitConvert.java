package com.hyj.demo.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class FileUnitConvert  extends JsonSerializer<String> {

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(StringUtils.isNotBlank(s)){
            jsonGenerator.writeString(NasFileUtils.convertMemory(Long.valueOf(s)));
        }
    }
    }
