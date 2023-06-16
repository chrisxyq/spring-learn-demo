package com.chrisxyq.spring.learn.demo.utils;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableMap;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Utility {
    /**
     * 推荐
     */
    private static final ImmutableMap<String, Integer> SAMPLE_MAP =
            ImmutableMap.of("One", 1, "Two", 2);
    /**
     * 需要警惕 public static final 数组的出现。很有可能是个安全漏洞！
     * 对数组使用final，只能是保证指向那个地址的指针不变了，但不能保证那个地址里的东西不会变了
     */
    private static final String[] head = new String[]{"hello","java"};
    /**
     * UnsupportedOperationException
     */
    private static final List<String> list= Collections.unmodifiableList(Arrays.asList(head));

    @VisibleForTesting
    static boolean isGreaterThan(int a, int b) {
        return a > b;
    }

    public static void main(String[] args) {

    }
}
