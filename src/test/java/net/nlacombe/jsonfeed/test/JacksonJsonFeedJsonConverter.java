package net.nlacombe.jsonfeed.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.nlacombe.jsonfeed.api.JsonFeedJsonConverter;
import net.nlacombe.jsonfeed.api.exception.JsonFeedException;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class JacksonJsonFeedJsonConverter implements JsonFeedJsonConverter {

    private final ObjectMapper objectMapper;

    public JacksonJsonFeedJsonConverter() {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public <BeanType> BeanType readBeanFromJson(Reader reader, Class<BeanType> beanClass) {
        try {
            return objectMapper.readValue(reader, beanClass);
        } catch (IOException exception) {
            throw new JsonFeedException("Error reading/deserializing json feed value: " + exception.getMessage(), exception);
        }
    }

    @Override
    public <BeanType> void writeBeanToJson(Writer writer, BeanType bean) {
        try {
            objectMapper.writeValue(writer, bean);
        } catch (IOException exception) {
            throw new JsonFeedException("Error writing/serializing json feed value: " + exception.getMessage(), exception);
        }
    }

}
