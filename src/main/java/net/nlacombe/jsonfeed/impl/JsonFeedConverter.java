package net.nlacombe.jsonfeed.impl;

import net.nlacombe.jsonfeed.api.JsonFeed;
import net.nlacombe.jsonfeed.api.JsonFeedJsonConverter;
import net.nlacombe.jsonfeed.api.exception.JsonFeedException;
import net.nlacombe.jsonfeed.impl.dto.JsonFeedDto;
import net.nlacombe.jsonfeed.impl.mapper.JsonFeedMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class JsonFeedConverter {

    private static final Logger logger = LoggerFactory.getLogger(JsonFeedConverter.class);

    private static JsonFeedConverter instance;

    private final JsonFeedMapper jsonFeedMapper;
    private JsonFeedJsonConverter jsonConverter;

    private JsonFeedConverter() {
        this.jsonFeedMapper = new JsonFeedMapper();
    }

    public static JsonFeedConverter getInstance() {
        if (instance == null) {
            instance = new JsonFeedConverter();
        }

        return instance;
    }

    public JsonFeed readJsonFeed(Reader reader) {
        var jsonConverter = getJsonFeedJsonConverter();
        var jsonFeedDto = jsonConverter.readBeanFromJson(reader, JsonFeedDto.class);

        return jsonFeedMapper.mapToDomainObject(jsonFeedDto);
    }

    public void writeJsonFeed(JsonFeed jsonFeed, Writer writer) {
        var jsonConverter = getJsonFeedJsonConverter();
        var jsonFeedDto = jsonFeedMapper.mapToDto(jsonFeed);

        jsonConverter.writeBeanToJson(writer, jsonFeedDto);
    }

    private JsonFeedJsonConverter getJsonFeedJsonConverter() {
        if (jsonConverter != null) {
            return jsonConverter;
        }

        var jsonFeedJsonConverterClass = JsonFeedJsonConverter.class;
        var jsonConverterProviders = ServiceLoader.load(jsonFeedJsonConverterClass).stream()
            .collect(Collectors.toList());

        if (jsonConverterProviders.size() < 1) {
            var message = "No provider found for $class.".replace("$class", jsonFeedJsonConverterClass.getCanonicalName())
                + " You need to include an additional library that provides an implementation for that class.";
            throw new JsonFeedException(message);
        }

        var chosenJsonConverterProvider = jsonConverterProviders.get(0);

        if (jsonConverterProviders.size() > 1) {
            var message = "Multiple providers found for $class. Choosing $chosenClass out of $classes"
                .replace("$class", jsonFeedJsonConverterClass.getCanonicalName())
                .replace("$chosenClass", chosenJsonConverterProvider.type().getCanonicalName())
                .replace("$classes", Arrays.toString(jsonConverterProviders.stream().map(ServiceLoader.Provider::type).toArray()));
            logger.warn(message);
        }

        var message = "Using $chosenClass as a provider for $class"
            .replace("$class", jsonFeedJsonConverterClass.getCanonicalName())
            .replace("$chosenClass", chosenJsonConverterProvider.type().getCanonicalName());
        logger.debug(message);

        jsonConverter = chosenJsonConverterProvider.get();

        return jsonConverter;
    }
}
