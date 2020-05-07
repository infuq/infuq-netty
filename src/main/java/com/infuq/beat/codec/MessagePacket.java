package com.infuq.beat.codec;


import lombok.Data;

import java.io.Serializable;

/**
 * 消息帧
 *
 */
@Data
public class MessagePacket implements Serializable {

    private int version;

    private int extend;

    private int contentLength;

    private Message content;

}
