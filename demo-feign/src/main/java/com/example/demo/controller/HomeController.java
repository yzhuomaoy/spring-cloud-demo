package com.example.demo.controller;

import com.example.demo.service.HomeService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuyanyu on 2017/12/28.
 */
@RestController
public class HomeController {

  @Resource
  private HomeService homeService;

  @GetMapping("/hello")
  public String hello(@RequestParam String name) {
    return homeService.hello(name);
  }
}
