package com.chrisxyq.spring.learn.demo;

import com.chrisxyq.spring.learn.demo.config.thread.MetricThreadPoolTaskExecutor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootTest
class SpringLearnDemoApplicationTests {
    @Autowired
    ThreadPoolConfigDemo demo;

    @Test
    void executeAbortPolicy() throws InterruptedException {
        demo.executeAbortPolicy();
    }

    @Test
    void test() throws InterruptedException {
        LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>(100);
        queue.add(1);
        System.out.println("queue.size()" + queue.size() +
                "queue.remaincapacity" + queue.remainingCapacity());
    }

    @Test
    void executeCallerRunsPolicy() throws InterruptedException {
        demo.executeCallerRunsPolicy();
    }

    @Test
    void metricThreadPoolTaskExecutorTest() throws InterruptedException {
        MetricThreadPoolTaskExecutor executor = new MetricThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(2);
        executor.setThreadNamePrefix("MetricThreadPoolTaskExecutor");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        Thread.sleep(10000L);
    }
}
