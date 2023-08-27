package com.chrisxyq.spring.learn.demo.config.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * job
 */
@Configuration
public class ThreadPoolConfig {
    /**
     * new ThreadPoolExecutor.AbortPolicy():拒绝并且抛异常
     * 动态化线程池提供了多个维度的监控和告警能力，包括：线程池活跃度、任务的执行Transaction（频率、耗时）、Reject异常、线程池内部统计信息等等
     * 程池活跃度计算公式为：线程池活跃度 = activeCount/maximumPoolSize。这个公式代表当活跃线程数趋向于maximumPoolSize的时候，代表线程负载趋高
     * @return
     */
    @Bean
    @Qualifier("abortPolicyExecutor")
    public ThreadPoolExecutor abortPolicyExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4,
                60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1),
                new ThreadFactoryBuilder().setNameFormat("abortPolicyExecutor-%d").build(),
                new ThreadPoolExecutor.AbortPolicy());
        executor.allowCoreThreadTimeOut(true);
        return executor;
    }

    /**
     * new ThreadPoolExecutor.CallerRunsPolicy():由调用主线程执行
     *
     * @return
     */
    @Bean
    @Qualifier("callerRunsPolicyExecutor")
    public ThreadPoolExecutor callerRunsPolicyExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4,
                60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1),
                new ThreadFactoryBuilder().setNameFormat("callerRunsPolicyExecutor-%d").build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        executor.allowCoreThreadTimeOut(true);
        return executor;
    }
}
