package net.nlacombe.jsonfeed.impl.mapper;

import net.nlacombe.jsonfeed.api.JsonFeedItem;
import net.nlacombe.jsonfeed.api.exception.JsonFeedException;
import net.nlacombe.jsonfeed.impl.DefaultJsonFeedItem;
import net.nlacombe.jsonfeed.impl.dto.JsonFeedItemDto;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Locale;
import java.util.Objects;

public class JsonFeedItemMapper extends AbstractBeanMapper<JsonFeedItemDto, JsonFeedItem> {

    private final JsonFeedAuthorMapper jsonFeedAuthorMapper;
    private final JsonFeedAttachmentMapper jsonFeedAttachmentMapper;

    public JsonFeedItemMapper() {
        jsonFeedAuthorMapper = new JsonFeedAuthorMapper();
        jsonFeedAttachmentMapper = new JsonFeedAttachmentMapper();
    }

    @Override
    public JsonFeedItemDto mapToDto(JsonFeedItem jsonFeedItem) {
        var jsonFeedItemDto = new JsonFeedItemDto();
        jsonFeedItemDto.setId(jsonFeedItem.getId());
        jsonFeedItemDto.setContent_text(jsonFeedItem.getContentText());
        jsonFeedItemDto.setContent_html(jsonFeedItem.getContentHtml());
        jsonFeedItemDto.setUrl(Objects.toString(jsonFeedItem.getUrl(), null));
        jsonFeedItemDto.setExternal_url(Objects.toString(jsonFeedItem.getExternalUrl(), null));
        jsonFeedItemDto.setTitle(jsonFeedItem.getTitle());
        jsonFeedItemDto.setSummary(jsonFeedItem.getSummary());
        jsonFeedItemDto.setImage(Objects.toString(jsonFeedItem.getImage(), null));
        jsonFeedItemDto.setBanner_image(Objects.toString(jsonFeedItem.getBannerImage(), null));
        jsonFeedItemDto.setDate_published(Objects.toString(jsonFeedItem.getDatePublished(), null));
        jsonFeedItemDto.setDate_modified(Objects.toString(jsonFeedItem.getDateModified(), null));
        jsonFeedItemDto.setLanguage(jsonFeedItem.getLanguage() == null ? null : jsonFeedItem.getLanguage().toLanguageTag());
        jsonFeedItemDto.setTags(jsonFeedItem.getTags());
        jsonFeedItemDto.setAuthors(jsonFeedAuthorMapper.mapToDtos(jsonFeedItem.getAuthors()));
        jsonFeedItemDto.setAttachments(jsonFeedAttachmentMapper.mapToDtos(jsonFeedItem.getAttachments()));

        return jsonFeedItemDto;
    }

    @Override
    public JsonFeedItem mapToDomainObject(JsonFeedItemDto jsonFeedItemDto) {
        try {
            var id = jsonFeedItemDto.getId();
            var contentText = jsonFeedItemDto.getContent_text();
            var contentHtml = jsonFeedItemDto.getContent_html();
            var jsonFeedItem = DefaultJsonFeedItem.newDefaultJsonFeedItem(id, contentText, contentHtml);
            jsonFeedItem.setUrl(jsonFeedItemDto.getUrl() == null ? null : URI.create(jsonFeedItemDto.getUrl()).toURL());
            jsonFeedItem.setExternalUrl(jsonFeedItemDto.getExternal_url() == null ? null : URI.create(jsonFeedItemDto.getExternal_url()).toURL());
            jsonFeedItem.setTitle(jsonFeedItemDto.getTitle());
            jsonFeedItem.setSummary(jsonFeedItemDto.getSummary());
            jsonFeedItem.setImage(jsonFeedItemDto.getImage() == null ? null : URI.create(jsonFeedItemDto.getImage()).toURL());
            jsonFeedItem.setBannerImage(jsonFeedItemDto.getBanner_image() == null ? null : URI.create(jsonFeedItemDto.getBanner_image()).toURL());
            jsonFeedItem.setDatePublished(jsonFeedItemDto.getDate_published() == null ? null : OffsetDateTime.parse(jsonFeedItemDto.getDate_published()));
            jsonFeedItem.setDateModified(jsonFeedItemDto.getDate_modified() == null ? null : OffsetDateTime.parse(jsonFeedItemDto.getDate_modified()));
            jsonFeedItem.setLanguage(jsonFeedItemDto.getLanguage() == null ? null : Locale.forLanguageTag(jsonFeedItemDto.getLanguage()));
            jsonFeedItem.setTags(jsonFeedItemDto.getTags());
            jsonFeedItem.setAuthors(jsonFeedAuthorMapper.mapToDomainObjects(jsonFeedItemDto.getAuthors()));
            jsonFeedItem.setAttachments(jsonFeedAttachmentMapper.mapToDomainObjects(jsonFeedItemDto.getAttachments()));

            return jsonFeedItem;
        } catch (MalformedURLException e) {
            throw new JsonFeedException("Error deserializing: " + e.getMessage(), e);
        }
    }
}
