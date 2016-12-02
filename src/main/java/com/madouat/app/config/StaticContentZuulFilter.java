package com.madouat.app.config;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.context.RequestContext;

public class StaticContentZuulFilter extends AbstractZuulFilter {

  private List<String> patterns;

  public StaticContentZuulFilter(List<String> pattern) {
    this.patterns = pattern;
  }

  @Override
  public String buildResponse(RequestContext context) {
    String data = "{"
        + " \"data\":[  {  "
        + "  \"id\":214917, \"code\":\"450\", \"description\":{ "
        + "\"en\":\"CAMPING\",\"fr\":\"CAMPING\" } } ]}";
    return data;
  }

  @Override
  public boolean shouldFilter() {
    RequestContext context = RequestContext.getCurrentContext();
    HttpServletRequest request = context.getRequest();
    return patterns.stream()
        .anyMatch(pp -> request.getRequestURI().contains(pp));
  }
}
