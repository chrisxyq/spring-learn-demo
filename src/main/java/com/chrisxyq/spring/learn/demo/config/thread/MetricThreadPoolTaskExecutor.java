package com.chrisxyq.spring.learn.demo.config.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * 内置线程池，定时打印线程参数
 * 1.内置ScheduledExecutorService，定时上报线程池本身的信息metric
 * 2.继承spring线程池：ThreadPoolTaskExecutor
 * 3.捕获拒绝策略：捕获主线程执行或者拒绝抛异常事件，上报metric
 *
 * 线程池本身如果shutdown了，内置线程池也要shutdown
 *
 */
public class MetricThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
    private static final Logger logger   = LoggerFactory.getLogger(MetricThreadPoolTaskExecutor.class);
    private final static     long   TIME_SEC = 1L;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    public MetricThreadPoolTaskExecutor() {
        scheduledExecutorService.scheduleAtFixedRate(this::printMetric,
                TIME_SEC, TIME_SEC, TimeUnit.SECONDS);
    }

    /**
     * 依赖于线程池本身被初始化了才能执行
     * executor.initialize();
     */
    private void printMetric() {
        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();
        logger.info("{}, taskCount [{}], completedTaskCount [{}], activeCount [{}], queueSize [{}]",
                this.getThreadNamePrefix(),
                threadPoolExecutor.getTaskCount(),
                threadPoolExecutor.getCompletedTaskCount(),
                threadPoolExecutor.getActiveCount(),
                threadPoolExecutor.getQueue().size());
    }
}
