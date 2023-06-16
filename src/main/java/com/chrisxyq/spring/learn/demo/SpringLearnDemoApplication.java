package com.chrisxyq.spring.learn.demo;

import com.chrisxyq.spring.learn.demo.config.context.MyApplicationContextInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan("com.chrisxyq.spring.learn.demo")
public class SpringLearnDemoApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringLearnDemoApplication.class);
        // 通过 SpringApplication 注册 ApplicationContextInitializer
        application.addInitializers(new MyApplicationContextInitializer());
        application.run(args);
    }

}
