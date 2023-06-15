package com.chrisxyq.spring.learn.demo;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目介绍：
 * <p>
 * 两个玩家交替，在横的，竖的，或者斜的一条线上的划去一点或者多点，谁划最后一点谁输。
 * <p>
 * Players take turns to cross any number of dots that are on a horizontal, vertical, or diagonal line, Whoever crosses the last dot loses!
 * <p>
 * 例如：下面这题，我一次性把下面三个划掉，则对手只能划掉最后一个，所以对手就输了。
 * ————————————————
 * 版权声明：本文为CSDN博主「失落夏天」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/rzleilei/article/details/100042381
 */
public class DotGame {

    static Map<String, Boolean>     resultMap = new HashMap<>();//记录结果，先走的有这种场景，是输是赢
    static Map<String, Integer[][]> intsMap   = new HashMap<>();//记录数组


    public static void main(String[] args) {

        Integer[][] ints = {
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        };
        ChessPlayer playerA = new ChessPlayer(1);
        ChessPlayer playerB = new ChessPlayer(2);
        playerA.opponent = playerB;
        playerB.opponent = playerA;
        playerA.takeStep(ints, 0);

    }

    @Test
    public void test13() {
        Integer[][] ints = {
                {0, 0, 4, 1},
                {0, 0, 4, 1},
                {1, 1, 4, 0},
                {0, 0, 0, 0}
        };
        ChessPlayer playerA = new ChessPlayer(1);
        ChessPlayer playerB = new ChessPlayer(2);
        playerA.opponent = playerB;
        playerB.opponent = playerA;
        playerA.takeStep(ints, 0);
    }

    @Test
    public void test2() {
        Integer[][] ints = {
                {0, 1, 0},
                {0, 1, 0},
                {1, 1, 1}
        };
        ChessPlayer playerA = new ChessPlayer(1);
        ChessPlayer playerB = new ChessPlayer(2);
        playerA.opponent = playerB;
        playerB.opponent = playerA;
        playerA.takeStep(ints, 0);
    }

    @Test
    public void test6() {
        Integer[][] ints = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        ChessPlayer playerA = new ChessPlayer(1);
        ChessPlayer playerB = new ChessPlayer(2);
        playerA.opponent = playerB;
        playerB.opponent = playerA;
        playerA.takeStep(ints, 0);
    }

    @Test
    public void test8() {
        Integer[][] ints = {
                {1, 1, 0},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}

        };
        ChessPlayer playerA = new ChessPlayer(1);
        ChessPlayer playerB = new ChessPlayer(2);
        playerA.opponent = playerB;
        playerB.opponent = playerA;
        playerA.takeStep(ints, 0);
    }

    @Test
    public void test7() {
        Integer[][] ints = {
                {1, 1, 1},
                {0, 1, 0},
                {1, 1, 1},
                {1, 1, 1}

        };
        ChessPlayer playerA = new ChessPlayer(1);
        ChessPlayer playerB = new ChessPlayer(2);
        playerA.opponent = playerB;
        playerB.opponent = playerA;
        playerA.takeStep(ints, 0);
    }

    @Test
    public void test9() {
        Integer[][] ints = {
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}

        };
        ChessPlayer playerA = new ChessPlayer(1);
        ChessPlayer playerB = new ChessPlayer(2);
        playerA.opponent = playerB;
        playerB.opponent = playerA;
        playerA.takeStep(ints, 0);
    }

    @Test
    public void test10() {
        Integer[][] ints = {
                {1, 1, 1, 0},
                {1, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 1, 1}

        };
        ChessPlayer playerA = new ChessPlayer(1);
        ChessPlayer playerB = new ChessPlayer(2);
        playerA.opponent = playerB;
        playerB.opponent = playerA;
        playerA.takeStep(ints, 0);
    }

    @Test
    public void test12() {
        Integer[][] ints = {
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1}

        };
        ChessPlayer playerA = new ChessPlayer(1);
        ChessPlayer playerB = new ChessPlayer(2);
        playerA.opponent = playerB;
        playerB.opponent = playerA;
        playerA.takeStep(ints, 0);
    }

    @Test
    public void test5() {
        Integer[][] ints = {
                {1, 1, 1},
                {0, 1, 1},
                {1, 1, 1}

        };
        ChessPlayer playerA = new ChessPlayer(1);
        ChessPlayer playerB = new ChessPlayer(2);
        playerA.opponent = playerB;
        playerB.opponent = playerA;
        playerA.takeStep(ints, 0);
    }

    @Test
    public void test11() {
        Integer[][] ints = {
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 0},
                {1, 0, 1, 1}

        };
        ChessPlayer playerA = new ChessPlayer(1);
        ChessPlayer playerB = new ChessPlayer(2);
        playerA.opponent = playerB;
        playerB.opponent = playerA;
        playerA.takeStep(ints, 0);
    }

    static class ChessPlayer {
        public int         name;
        public ChessPlayer opponent;

        public ChessPlayer(int id) {
            name = id;
        }

        public boolean takeStep(Integer[][] ints, int step) {
            int height = ints.length;
            int width = ints[0].length;

            //记录状态值，命中缓存的，也直接返回结果
            Boolean cacheResult = getCacheResult(ints);
            if (cacheResult != null) {
                return cacheResult;
            }
            //计算数量
            int count = getCount(ints);

            if (count == 1) {
                return false;
            }
            if (name == 1 && step == 0) {
//                System.out.println("x0");
            }

            //这里递归计算所有的选项
            //计算所有只选1个的场景
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Integer integer = ints[i][j];
                    if (integer == 1) {
                        Integer[][] copy = copy(ints);
                        copy[i][j] = 0;
                        boolean result = opponent.takeStep(copy, step + 1);//对手的结果
                        showResult(copy, 1, result, step);
                        if (!result) {
                            return true;
                        }
                    }
                }
            }
            if (name == 1 && step == 0) {
                boolean flag = true;
            }
            //计算所有只选2个的场景
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Integer integer = ints[i][j];
                    if (integer == 1) {
                        if ((j + 1) < width) {
                            if (ints[i][j + 1] == 1) {
                                Integer[][] copy = copy(ints);
                                copy[i][j] = 0;
                                copy[i][j + 1] = 0;
                                boolean result = opponent.takeStep(copy, step + 1);
                                showResult(copy, 2, result, step);
                                if (!result) {
                                    return true;
                                }
                            }
                        }
                        if ((i + 1) < height) {
                            if (ints[i + 1][j] == 1) {
                                Integer[][] copy = copy(ints);
                                copy[i][j] = 0;
                                copy[i + 1][j] = 0;
                                boolean result = opponent.takeStep(copy, step + 1);
                                showResult(copy, 2, result, step);
                                if (!result) {
                                    return true;
                                }
                            }
                        }
                        if ((j + 1) < width && (i + 1) < height) {
                            if (ints[i + 1][j + 1] == 1) {
                                Integer[][] copy = copy(ints);
                                copy[i][j] = 0;
                                copy[i + 1][j + 1] = 0;
                                boolean result = opponent.takeStep(copy, step + 1);
                                showResult(copy, 2, result, step);
                                if (!result) {
                                    return true;
                                }
                            }
                        }
                        if ((i + 1) < height && (j - 1) >= 0) {
                            if (ints[i + 1][j - 1] == 1) {
                                Integer[][] copy = copy(ints);
                                copy[i][j] = 0;
                                copy[i + 1][j - 1] = 0;
                                boolean result = opponent.takeStep(copy, step + 1);
                                showResult(copy, 2, result, step);
                                if (!result) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            if (name == 1 && step == 0) {
                boolean flag = true;
            }
            //计算所有只选3个的场景
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Integer integer = ints[i][j];
                    if (integer == 1) {
                        if ((i + 2) < height) {
                            if (ints[i + 1][j] == 1 && ints[i + 2][j] == 1) {
                                Integer[][] copy = copy(ints);
                                copy[i][j] = 0;
                                copy[i + 1][j] = 0;
                                copy[i + 2][j] = 0;
                                boolean result = opponent.takeStep(copy, step + 1);
                                showResult(copy, 3, result, step);
                                if (!result) {
                                    return true;
                                }
                            }
                        }
                        if ((j + 2) < width) {
                            if (ints[i][j + 1] == 1 && ints[i][j + 2] == 1) {
                                Integer[][] copy = copy(ints);
                                copy[i][j] = 0;
                                copy[i][j + 1] = 0;
                                copy[i][j + 2] = 0;
                                boolean result = opponent.takeStep(copy, step + 1);
                                showResult(copy, 3, result, step);
                                if (!result) {
                                    return true;
                                }
                            }
                        }
                        if ((j + 2) < width && (i + 2) < height) {
                            if (ints[i + 1][j + 1] == 1 && ints[i + 2][j + 2] == 1) {
                                Integer[][] copy = copy(ints);
                                copy[i][j] = 0;
                                copy[i + 1][j + 1] = 0;
                                copy[i + 2][j + 2] = 0;
                                boolean result = opponent.takeStep(copy, step + 1);
                                showResult(copy, 3, result, step);
                                //无论如何，缓存结果
                                if (!result) {
                                    return true;
                                }
                            }
                        }
                        if ((i + 2) < height && (j - 2) >= 0) {
                            if (ints[i + 1][j - 1] == 1 && ints[i + 2][j - 2] == 1) {
                                Integer[][] copy = copy(ints);
                                copy[i][j] = 0;
                                copy[i + 1][j - 1] = 0;
                                copy[i + 2][j - 2] = 0;
                                boolean result = opponent.takeStep(copy, step + 1);
                                showResult(copy, 3, result, step);
                                //无论如何，缓存结果
                                if (!result) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            if (name == 1 && step == 0) {
                boolean flag = true;
            }
            //计算所有只选4个的场景
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Integer integer = ints[i][j];
                    if (integer == 1) {
                        if ((i + 3) < height) {
                            if (ints[i + 3][j] == 1 && ints[i + 2][j] == 1 && ints[i + 1][j] == 1) {
                                Integer[][] copy = copy(ints);
                                copy[i][j] = 0;
                                copy[i + 1][j] = 0;
                                copy[i + 2][j] = 0;
                                copy[i + 3][j] = 0;
                                boolean result = opponent.takeStep(copy, step + 1);
                                showResult(copy, 4, result, step);
                                if (!result) {
                                    return true;
                                }
                            }
                        }
                        if ((j + 3) < width) {
                            if (ints[i][j + 1] == 1 && ints[i][j + 2] == 1 && ints[i][j + 3] == 1) {
                                Integer[][] copy = copy(ints);
                                copy[i][j] = 0;
                                copy[i][j + 1] = 0;
                                copy[i][j + 2] = 0;
                                copy[i][j + 3] = 0;
                                boolean result = opponent.takeStep(copy, step + 1);
                                showResult(copy, 4, result, step);
                                if (!result) {
                                    return true;
                                }
                            }
                        }
                        if ((j + 3) < width && (i + 3) < height) {
                            if (ints[i + 1][j + 1] == 1 && ints[i + 2][j + 2] == 1 && ints[i + 3][j + 3] == 1) {
                                Integer[][] copy = copy(ints);
                                copy[i][j] = 0;
                                copy[i + 1][j + 1] = 0;
                                copy[i + 2][j + 2] = 0;
                                copy[i + 3][j + 3] = 0;
                                boolean result = opponent.takeStep(copy, step + 1);
                                showResult(copy, 4, result, step);
                                //无论如何，缓存结果
                                if (!result) {
                                    return true;
                                }
                            }
                        }
                        if ((i + 3) < height && (j - 3) >= 0) {
                            if (ints[i + 1][j - 1] == 1 && ints[i + 2][j - 2] == 1 && ints[i + 3][j - 3] == 1) {
                                Integer[][] copy = copy(ints);
                                copy[i][j] = 0;
                                copy[i + 1][j - 1] = 0;
                                copy[i + 2][j - 2] = 0;
                                copy[i + 3][j - 3] = 0;
                                boolean result = opponent.takeStep(copy, step + 1);
                                showResult(copy, 4, result, step);
                                //无论如何，缓存结果
                                if (!result) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }

            //如果所有可能性都不能返回true，那只能返回false
            return false;
        }


        /**
         * @param copy
         * @param num    取数的数量
         * @param result 对手的结果
         * @param step
         */
        public void showResult(Integer[][] copy, int num, boolean result, int step) {
            if (name == 1) {
                setCacheResult(copy, result);//result=true代表对方lost，则意味当前对象win
            }
            if (name == 1 && step == 0 && !result) {
                int height = copy.length;
                int width = copy[0].length;
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        System.out.print(copy[i][j]);
                    }
                    System.out.println();
                }
                if (result) {
                    System.out.println("----user:" + name + " lost,step:" + step);
                } else {
                    System.out.println("----user:" + name + " win,step:" + step);
                }
                System.out.println();
            }
        }

        public void setCacheResult(Integer[][] ints, boolean result) {
            String key = getKey(ints);
            Boolean value = resultMap.get(key);
            if (value == null) {
                resultMap.put(key, result);
            }
        }

        public Boolean getCacheResult(Integer[][] ints) {
            String key = getKey(ints);
            Boolean result = resultMap.get(key);
            return result;
        }

        public String getKey(Integer[][] ints) {
            StringBuffer str = new StringBuffer();
            for (int i = 0; i < ints.length; i++) {
                for (int j = 0; j < ints[0].length; j++) {
                    str.append(ints[i][j]);
                }
                str.append(",");
            }
            return str.toString();
        }

        public Integer[][] copy(Integer[][] ints) {
            Integer[][] copy = new Integer[ints.length][ints[0].length];
            for (int i = 0; i < ints.length; i++) {
                System.arraycopy(ints[i], 0, copy[i], 0, ints[i].length);
            }
            return copy;
        }

        public int getCount(Integer[][] ints) {
            int count = 0;
            for (int i = 0; i < ints.length; i++) {
                for (int j = 0; j < ints[0].length; j++) {
                    Integer integer = ints[i][j];
                    if (integer == 1) {
                        count++;
                    }
                }
            }
            return count;
        }

    }

}