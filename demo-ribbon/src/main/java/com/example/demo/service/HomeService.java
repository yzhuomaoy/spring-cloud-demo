package com.example.demo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by liuyanyu on 2017/12/28.
 */
@Service
public class HomeService {

  @Resource
  private RestTemplate restTemplate;

  @HystrixCommand(fallbackMethod = "helloError")
  public String hello(String name) {
    return restTemplate.getForObject("http://EUREKA-C-1/hello?name=" + name, String.class);
  }

  public String helloError(String name) {
    return "Hytrix error say hello: " + name;
  }
}
