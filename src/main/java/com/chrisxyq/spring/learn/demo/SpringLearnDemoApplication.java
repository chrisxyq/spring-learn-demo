package com.chrisxyq.spring.learn.demo;

import com.chrisxyq.spring.learn.demo.config.MyApplicationContextInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLearnDemoApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringLearnDemoApplication.class);
        // 通过 SpringApplication 注册 ApplicationContextInitializer
        application.addInitializers(new MyApplicationContextInitializer());
        application.run(args);
    }

}
