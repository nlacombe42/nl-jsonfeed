package net.nlacombe.jsonfeed.impl.mapper;

import net.nlacombe.jsonfeed.api.JsonFeedAttachment;
import net.nlacombe.jsonfeed.api.exception.JsonFeedException;
import net.nlacombe.jsonfeed.impl.DefaultJsonFeedAttachment;
import net.nlacombe.jsonfeed.impl.dto.JsonFeedAttachmentDto;

import java.net.MalformedURLException;
import java.net.URI;

public class JsonFeedAttachmentMapper extends AbstractBeanMapper<JsonFeedAttachmentDto, JsonFeedAttachment> {

    @Override
    public JsonFeedAttachmentDto mapToDto(JsonFeedAttachment jsonFeedAttachment) {
        var jsonFeedAttachmentDto = new JsonFeedAttachmentDto();
        jsonFeedAttachmentDto.setUrl(jsonFeedAttachment.getUrl() == null ? null : jsonFeedAttachment.getUrl().toString());
        jsonFeedAttachmentDto.setMime_type(jsonFeedAttachment.getMimeType());
        jsonFeedAttachmentDto.setTitle(jsonFeedAttachment.getTitle());
        jsonFeedAttachmentDto.setDuration_in_seconds(jsonFeedAttachment.getDurationInSeconds());
        jsonFeedAttachmentDto.setSize_in_bytes(jsonFeedAttachment.getSizeInBytes());

        return jsonFeedAttachmentDto;
    }

    @Override
    public JsonFeedAttachment mapToDomainObject(JsonFeedAttachmentDto jsonFeedAttachmentDto) {
        try {
            var url = URI.create(jsonFeedAttachmentDto.getUrl()).toURL();
            var mimeType = jsonFeedAttachmentDto.getMime_type();

            var jsonFeedAttachment = DefaultJsonFeedAttachment.from(url, mimeType);
            jsonFeedAttachment.setTitle(jsonFeedAttachmentDto.getTitle());
            jsonFeedAttachment.setDurationInSeconds(jsonFeedAttachmentDto.getDuration_in_seconds());
            jsonFeedAttachment.setSizeInBytes(jsonFeedAttachmentDto.getSize_in_bytes());

            return jsonFeedAttachment;
        } catch (MalformedURLException e) {
            throw new JsonFeedException("Error deserializing: " + e.getMessage(), e);
        }
    }
}
