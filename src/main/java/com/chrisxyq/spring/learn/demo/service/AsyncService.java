package com.chrisxyq.spring.learn.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncService {
    /**
     * http://mp.weixin.qq.com/s?__biz=MjM5NjQ5MTI5OA==&mid=2651773342&idx=1&sn=0931e6fbecd692f8c9677094227e2b10&chksm=bd1200d38a6589c54e895cf5d1627b9d7f960931f2a39e5a17fbf72f072eafde92fa07534bfd&mpshare=1&scene=24&srcid=0526mQfaEGNO3zVAg6TBYeuW&sharer_sharetime=1685052306040&sharer_shareid=d484f14ba5ecdc101d8989427775ede5#rd
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
