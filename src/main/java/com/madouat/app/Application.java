package com.madouat.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import de.codecentric.boot.admin.config.EnableAdminServer;

@EnableZuulProxy
@SpringBootApplication
@EnableAdminServer
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
