package net.nlacombe.jsonfeedlib.api;

import java.io.Writer;

public interface JsonFeedJsonConverter {

    <BeanType> void writeBeanToJson(BeanType bean, Class<BeanType> beanClass, Writer writer);
}
