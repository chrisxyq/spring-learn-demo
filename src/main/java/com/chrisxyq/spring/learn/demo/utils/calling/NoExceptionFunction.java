package com.chrisxyq.spring.learn.demo.utils.calling;

public interface NoExceptionFunction<T, R, E extends Throwable> {
    R apply(T var1) throws E;
}
