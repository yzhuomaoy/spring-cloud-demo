package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuyanyu on 2017/12/28.
 */
@RestController
public class HomeController {

  @Value("${server.port}")
  String port;

  @GetMapping("/hello")
  public String hello(@RequestParam String name) {
    return "say hello: " + name + ", my port: " + port;
  }
}
