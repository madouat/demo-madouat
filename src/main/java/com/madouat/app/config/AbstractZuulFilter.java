package com.madouat.app.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public abstract class AbstractZuulFilter extends ZuulFilter {

  private static Logger log = LoggerFactory.getLogger(AbstractZuulFilter.class);

  @Override
  public String filterType() {
    return "post";
  }

  @Override
  public int filterOrder() {
    return 1;
  }

  public abstract boolean shouldFilter();

  public abstract String buildResponse(RequestContext context);

  public Object run() {

    RequestContext context = RequestContext.getCurrentContext();

    log.info(
        String.format("Route= %s", context.getRouteHost().toString()));


    if (context.getResponseBody() != null || context.getResponseDataStream() != null) {
      context.setResponseBody(buildResponse(context));

    }

    return null;
  }

  protected String writeAsString(InputStream inputStream, Charset charset) {

    String data = null;
    try {
      data = IOUtils.toString(inputStream, charset);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return data;

  }
}
