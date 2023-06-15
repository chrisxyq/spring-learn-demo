package com.chrisxyq.spring.learn.demo.service;

import com.chrisxyq.spring.learn.demo.mapper.SystemConfigMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统属性服务
 * https://mp.weixin.qq.com/s?__biz=MzIzOTU0NTQ0MA==&mid=2247532958&idx=1&sn=d110c2c7f26b75c5a4cb87206092ccf9&chksm=e92a7c91de5df587404ac483aff0a744360852bc0c90f97e91f742cc74239b6f474add9e4e53&mpshare=1&scene=24&srcid=0426kevs8kDBOiTkq1Rn2DXr&sharer_sharetime=1682481051077&sharer_shareid=d484f14ba5ecdc101d8989427775ede5#rd
 * 在 SpringBoot 中如何让自己的某个指定的 Bean 在其他 Bean 前完成被 Spring 加载？
 * 首先要明白一点，Bean 创建的顺序是怎么来的，如果你对 Spring 的源码比较熟悉，
 * 你会知道在 AbstractApplicationContext 里面有个 refresh 方法， Bean 创建的大部分逻辑都在 refresh 方法里面，
 * 在 refresh 末尾的 finishBeanFactoryInitialization(beanFactory) 方法调用中，
 * 会调用 beanFactory.preInstantiateSingletons()，在这里对所有的 beanDefinitionNames 一一遍历，进行 bean 实例化和组装：
 **/
@Service
public class SystemConfigService {

    // 访问 db 的 mapper
    private final SystemConfigMapper systemConfigMapper;

    // 存放一些系统配置的缓存 map
    private static Map<String, String> SYS_CONF_CACHE = new HashMap<>();

    /**
     * Spring 对于依赖注入更推荐（is preferable）使用构造函数来注入必须的依赖，用 setter 方法来注入可选的依赖
     *
     * @param systemConfigMapper
     */
    public SystemConfigService(SystemConfigMapper systemConfigMapper) {
        this.systemConfigMapper = systemConfigMapper;
    }

    // Bean 的初始化方法，捞取数据库中的数据，放入缓存的 map 中
    @PostConstruct
    public void init() {
        // systemConfigMapper 访问 DB，捞取数据放入缓存的 map 中
        // SYS_CONF_CACHE.put(key, value);
        // ...
    }

    // 对外提供获得系统配置的 static 工具方法
    public String getSystemConfig(String key) {
        return SYS_CONF_CACHE.get(key);
    }

    // 省略了从 DB 更新缓存的代码
    // ...
}
