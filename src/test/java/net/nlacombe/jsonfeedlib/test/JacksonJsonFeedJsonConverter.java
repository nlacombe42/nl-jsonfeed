package net.nlacombe.jsonfeedlib.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.nlacombe.jsonfeedlib.api.JsonFeedJsonConverter;
import net.nlacombe.jsonfeedlib.api.exception.JsonFeedException;

import java.io.IOException;
import java.io.Writer;

public class JacksonJsonFeedJsonConverter implements JsonFeedJsonConverter {

    private final ObjectMapper objectMapper;

    public JacksonJsonFeedJsonConverter() {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public <BeanType> void writeBeanToJson(BeanType bean, Class<BeanType> beanClass, Writer writer) {
        try {
            objectMapper.writeValue(writer, bean);
        } catch (IOException exception) {
            throw new JsonFeedException("Error writing/serializing json feed value: " + exception.getMessage(), exception);
        }
    }

}
