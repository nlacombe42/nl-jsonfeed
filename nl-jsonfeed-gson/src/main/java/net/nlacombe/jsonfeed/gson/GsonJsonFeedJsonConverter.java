package net.nlacombe.jsonfeed.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import net.nlacombe.jsonfeed.api.JsonFeedJsonConverter;
import net.nlacombe.jsonfeed.api.exception.JsonFeedException;

import java.io.Reader;
import java.io.Writer;

public class GsonJsonFeedJsonConverter implements JsonFeedJsonConverter {

    private static Gson userSpecifiedGson;
    private final Gson gson;

    public GsonJsonFeedJsonConverter() {
        this.gson = userSpecifiedGson != null ? userSpecifiedGson : getDefaultGson();
    }

    public static void use(Gson gson) {
        userSpecifiedGson = gson;
    }

    @Override
    public <BeanType> BeanType readBeanFromJson(Reader reader, Class<BeanType> beanClass) {
        try {
            return gson.fromJson(reader, beanClass);
        } catch (JsonIOException exception) {
            throw new JsonFeedException("Error reading/deserializing json feed value: " + exception.getMessage(), exception);
        }
    }

    @Override
    public <BeanType> void writeBeanToJson(Writer writer, BeanType bean) {
        try {
            gson.toJson(bean, writer);
        } catch (JsonIOException exception) {
            throw new JsonFeedException("Error writing/serializing json feed value: " + exception.getMessage(), exception);
        }
    }

    private static Gson getDefaultGson() {
        return new GsonBuilder().create();
    }

}
