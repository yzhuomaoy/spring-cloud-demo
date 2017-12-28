package com.example.demo.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by liuyanyu on 2017/12/28.
 */
@Component
public class HytrixHomeService implements HomeService {

  @Override
  public String hello(String name) {
    return "Hytrix(feign) error say hello: " + name;
  }
}
