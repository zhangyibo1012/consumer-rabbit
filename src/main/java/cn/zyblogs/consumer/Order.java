package cn.zyblogs.consumer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Title: Order.java
 * @Package cn.zyblogs.consumer
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private String id;
    private String name;

    /**
     * 存储消息发送的唯一标识
     */
    private String messageId;
}

