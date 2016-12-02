package com.madouat.app.config;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;
import com.netflix.zuul.context.RequestContext;

@Configuration
public class ZuulFilterConfig {


  private static final Charset CHARSET_UTF_8 = Charset.forName("UTF-8");

  @Bean
  public PreCustomZuulFilter preCustomZuulFilter() {
    return new PreCustomZuulFilter();
  }

  @Bean
  public AbstractZuulFilter nonDispoZuulFilter() {
    return new AbstractZuulFilter() {
      @Override
      public String buildResponse(RequestContext context) {
        InputStream inputStream = null;

        if (context.getResponseBody() != null) {
          inputStream =
              new ByteArrayInputStream(
                  context.getResponseBody().getBytes(CHARSET_UTF_8));
        } else {
          inputStream = context.getResponseDataStream();
        }
        return writeAsString(inputStream, CHARSET_UTF_8);
      }

      @Override
      public boolean shouldFilter() {
        return !RequestContext.getCurrentContext()
            .getRequest().getRequestURI().contains("dispo");
      }
    };
  }

  @Bean
  public AbstractZuulFilter staticContentZuulFilter() {
    return new StaticContentZuulFilter(Lists.newArrayList("dispo"));
  }


}
