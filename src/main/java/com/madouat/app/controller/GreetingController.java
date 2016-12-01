package com.madouat.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GreetingController {

  @RequestMapping(value = "/test", method = RequestMethod.GET)
  public String greeting() {
    return "greeting";
  }

  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public String helloo() {
    return "Helloo madouat";
  }


}
