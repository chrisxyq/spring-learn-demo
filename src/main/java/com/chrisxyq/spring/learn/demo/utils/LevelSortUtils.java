package com.chrisxyq.spring.learn.demo.utils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会员等级映射
 */
public final class LevelSortUtils {
    private static final Map<Integer, Integer> LEVEL_SORT_WEIGHT_MAP = new HashMap();

    static {
        LEVEL_SORT_WEIGHT_MAP.put(1, 1);
        LEVEL_SORT_WEIGHT_MAP.put(7, 2);
        LEVEL_SORT_WEIGHT_MAP.put(2, 3);
        LEVEL_SORT_WEIGHT_MAP.put(3, 4);
        LEVEL_SORT_WEIGHT_MAP.put(4, 5);
        LEVEL_SORT_WEIGHT_MAP.put(6, 6);
        LEVEL_SORT_WEIGHT_MAP.put(5, 7);
    }

    public static int getLevelSortWeight(Integer level) {
        return LEVEL_SORT_WEIGHT_MAP.get(level);
    }

    public static int getLevelSortWeightByList(List<Integer> ctripLevelList) {
        int res = 10;
        for (Integer level : ctripLevelList) {
            res = Math.min(res, LEVEL_SORT_WEIGHT_MAP.get(level));
        }
        return res;
    }
}
