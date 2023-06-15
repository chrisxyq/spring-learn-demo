package com.chrisxyq.spring.learn.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncService {
    /**
     * 需要在配置类上增加@EnableAsync注解；
     * @Async注解可以标记一个异步执行的方法，也可以用来标记一个类表明该类的所有方法都是异步执行；
     * 可以在@Async中自定义执行器。
     * 在未指定线程池的情况下调用被标记为@Async的方法时，
     * Spring会自动创建SimpleAsyncTaskExecutor线程池来执行该方法，从而完成异步执行过程。
     * @Async主要是通过后置处理器 AsyncAnnotationBeanPostProcessor 生成一个代理对象来实现异步的执行逻辑
     *
     */
    @Async("taskExecutor")
    public void testAsy(){
        String asyThreadName = Thread.currentThread().getName();
        long asyThreadId = Thread.currentThread().getId();
        System.out.println("======Async====");
        System.out.println("------We got asy thread: "+ asyThreadName + " - " +  asyThreadId + "----------");
    }
}
