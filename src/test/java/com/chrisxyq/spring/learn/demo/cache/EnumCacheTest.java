package com.chrisxyq.spring.learn.demo.cache;


import com.chrisxyq.spring.learn.demo.enums.OrderTypeEnum;
import com.chrisxyq.spring.learn.demo.enums.StatusEnum;
import org.junit.jupiter.api.Test;

public class EnumCacheTest {
    private static final OrderTypeEnum DEF   = OrderTypeEnum._00;
    private static final int           TIMES = 10000000;

    static void compare(String code) {
        long s = System.currentTimeMillis();
        for (int idx = 0; idx < TIMES; idx++) {
            OrderTypeEnum.getEnumByCode(code, DEF);
        }
        long t = System.currentTimeMillis() - s;
        System.out.println(String.format("枚举->%s : %s", code, t));

        s = System.currentTimeMillis();
        for (int idx = 0; idx < TIMES; idx++) {
            EnumCache.findByValue(OrderTypeEnum.class, code, DEF);
        }
        t = System.currentTimeMillis() - s;
        System.out.println(String.format("缓存->%s : %s", code, t));
        System.out.println();
    }

    @Test
    public void test() {
        System.out.println(EnumCache.findByName(StatusEnum.class, "SUCCESS", null));
        // 返回默认值StatusEnum.INIT
        System.out.println(EnumCache.findByName(StatusEnum.class, null, StatusEnum.INIT));
        // 返回默认值StatusEnum.INIT
        System.out.println(EnumCache.findByName(StatusEnum.class, "ERROR", StatusEnum.INIT));


        System.out.println(EnumCache.findByValue(StatusEnum.class, "S", null));
        // 返回默认值StatusEnum.INIT
        System.out.println(EnumCache.findByValue(StatusEnum.class, null, StatusEnum.INIT));
        // 返回默认值StatusEnum.INIT
        System.out.println(EnumCache.findByValue(StatusEnum.class, "ERROR", StatusEnum.INIT));
    }

    @Test
    public void test1() {
        for (int idx = 0; idx < 2; idx++) {
            compare("NotExist");
            for (OrderTypeEnum value : OrderTypeEnum.values()) {
                compare(value.getCode());
            }
            System.out.println("=================");
        }
    }
}
