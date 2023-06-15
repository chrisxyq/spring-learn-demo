package com.chrisxyq.spring.learn.demo.utils.calling;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yuanqixu
 */
@Slf4j
public class CatchCalling {
    public static <T, R, E extends Exception> R execute(T request, NoExceptionFunction<T, R, E> function) {
        R result = null;
        try {
            Gson gson = new Gson();
            log.info("ExternalCatchCalling.request", gson.toJson(request));
            result = function.apply(request);
            log.info("ExternalCatchCalling.response", gson.toJson(request));
        } catch (Exception ex) {
            log.error("ExternalCatchCalling.execute", ex);
        }
        return result;
    }
}
