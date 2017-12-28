package com.example.demo.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by liuyanyu on 2017/12/28.
 */
@FeignClient(value = "eureka-c-1", fallback = HytrixHomeService.class)
public interface HomeService {
  @GetMapping("/hello")
  String hello(@RequestParam("name") String name);
}
