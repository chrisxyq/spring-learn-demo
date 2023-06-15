package com.chrisxyq.spring.learn.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 我们以@EnableAsync为入口开始分析异步过程，除了基本的配置方法外，
 * 我们重点关注下配置类AsyncConfigurationSelector的内部逻辑，由于默认条件下我们使用JDK接口代理，
 * 这里重点看看ProxyAsyncConfiguration类的代码逻辑：
 * ProxyAsyncConfiguration继承了父类AbstractAsyncConfiguration的方法，
 * 重点定义了一个AsyncAnnotationBeanPostProcessor的异步注解bean后置处理器。
 * 看到这里我们可以知道，@Async主要是通过后置处理器生成一个代理对象来实现异步的执行逻辑，
 * 接下来我们重点关注AsyncAnnotationBeanPostProcessor是如何实现异步的：
 * 从类图中我们可以直观地看到AsyncAnnotationBeanPostProcessor同时实现了BeanFactoryAware的接口，
 * 因此我们进入setBeanFactory()方法，可以看到对AsyncAnnotationAdvisor异步注解切面进行了构造，
 * 再接着进入AsyncAnnotationAdvisor的buildAdvice()方法中可以看AsyncExecutionInterceptor类，
 * 再看类图发现AsyncExecutionInterceptor实现了MethodInterceptor接口，
 * 而MethodInterceptor是AOP中切入点的处理器，对于interceptor类型的对象，
 * 处理器中最终被调用的是invoke方法，所以我们重点看看invoke的代码逻辑：
 * 我们再接着看看@Async用了什么线程池，重点关注determineAsyncExecutor方法中getExecutorQualifier指定获取的默认线程池是哪一个：
 * 至此，我们了解到在未指定线程池的情况下调用被标记为@Async的方法时，
 * Spring会自动创建SimpleAsyncTaskExecutor线程池来执行该方法，从而完成异步执行过程。
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@EnableAsync
public class AsyncServiceTest {
    @Resource
    private AsyncService demoService;

    @Test
    public void testTestAsy() throws InterruptedException {
        String mainThreadName = Thread.currentThread().getName();
        long mainThreadId = Thread.currentThread().getId();
        System.out.println("------We got main thread: " + mainThreadName + " - "
                + mainThreadId + "----------");
        demoService.testAsy();
        Thread.sleep(3000);
    }
}
