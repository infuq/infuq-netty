package com.infuq.beat.codec;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * 业务消息
 */
@Data
public class Message implements Serializable {

    // 类型 1-心跳包 2-业务包
    private int type;

    private String content;

}
