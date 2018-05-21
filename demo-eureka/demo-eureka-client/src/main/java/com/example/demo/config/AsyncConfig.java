package com.example.demo.config;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import org.apache.log4j.NDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {


  @Bean
  public ThreadPoolTaskExecutor initTaskPool() {
    ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
    // spring version 4.3+, could user taskDecorator
    pool.setTaskDecorator(runnable -> {
      String ndc = NDC.peek();
      return wrapRunnable(runnable, ndc);
    });
    return pool;
  }

  private static Runnable wrapRunnable(Runnable runnable, String context) {
    return () -> {
      try {
        NDC.push(context);
        runnable.run();
      } finally {
        NDC.clear();
      }
    };
  }

  private static <V> Callable wrapCallable(Callable<V> callable, String context) {
    return () -> {
      try {
        NDC.push(context);
        return callable.call();
      } finally {
        NDC.clear();
      }
    };
  }

  // spring version 4.3-, could override executor
  public static class NDCThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    @Override
    public Future<?> submit(Runnable task) {
      return super.submit(wrapRunnable(task, NDC.peek()));
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
      return super.submit(wrapCallable(task, NDC.peek()));
    }
  }
}
