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
     *
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
