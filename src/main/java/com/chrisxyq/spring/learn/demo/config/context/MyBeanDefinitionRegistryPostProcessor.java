package com.chrisxyq.spring.learn.demo.config.context;


import com.chrisxyq.spring.learn.demo.service.SystemConfigService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    /**
     * 用 MyBeanDefinitionRegistryPostProcessor 在 ConfigurationClassPostProcessor
     * 扫描前注册你需要的目标 bean 的 BeanDefinition 即可。
     * @param registry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        // 手动注册一个 BeanDefinition
        registry.registerBeanDefinition("systemConfigService", new RootBeanDefinition(SystemConfigService.class));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {}
}
