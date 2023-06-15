package com.chrisxyq.spring.learn.demo.enums;


import com.chrisxyq.spring.learn.demo.cache.EnumCache;

public enum OrderTypeEnum {
    _00("00", "00"),
    _01("01", "01"),
    _02("02", "02"),
    _03("03", "03"),
    _04("04", "04"),
    _05("05", "05"),
    _06("06", "06"),
    _07("07", "07"),
    _08("08", "08"),
    _09("09", "09"),
    _10("10", "10")
    ;
    private String code;
    private String desc;

    OrderTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    static {
        EnumCache.registerByValue(OrderTypeEnum.class, OrderTypeEnum.values(), OrderTypeEnum::getCode);
    }

    public static OrderTypeEnum getEnumByCode(String code, OrderTypeEnum def) {
        OrderTypeEnum[] values = OrderTypeEnum.values();
        for (OrderTypeEnum value : values) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return def;
    }
}
