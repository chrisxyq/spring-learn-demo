package com.chrisxyq.spring.learn.demo.dto;

import lombok.Builder;

/**
 * 在处理请求的过程中，笔者也常常会担心误操作修改了请求的内容。所以，笔者倾向使用Builder模式。
 */
@Builder
public class SampleRequest {
    private String paramOne;
    private int paramTwo;
    private boolean paramThree;

    public static void main(String[] args) {
        SampleRequest request =
                    SampleRequest.builder().paramOne("one").paramTwo(2).paramThree(true).build();
    }
}
