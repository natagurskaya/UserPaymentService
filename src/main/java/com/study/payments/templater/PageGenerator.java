package com.study.payments.templater;

import com.study.payments.configuration.Constants;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class PageGenerator {
    public static PageGenerator pageGenerator;
    private final Configuration configuration;

    public static PageGenerator getInstance() {
        if (pageGenerator == null) {
            pageGenerator = new PageGenerator();
        }
        return pageGenerator;
    }

    public String getPage(String pathInfo, Map<String, Object> data) {
        Writer stream = new StringWriter();
        try {
            Template template = configuration.getTemplate(Constants.webAppPath + pathInfo);
            template.process(data, stream);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return stream.toString();
    }

    private PageGenerator() {
        configuration = new Configuration();
    }
}
