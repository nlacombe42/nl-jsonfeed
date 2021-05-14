package net.nlacombe.jsonfeed.api;

import java.io.Reader;
import java.io.Writer;

public interface JsonFeedJsonConverter {

    <BeanType> void writeBeanToJson(Writer writer, BeanType bean);

    <BeanType> BeanType readBeanFromJson(Reader reader, Class<BeanType> beanClass);
}
