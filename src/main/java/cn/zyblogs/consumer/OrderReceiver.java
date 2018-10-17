package cn.zyblogs.consumer;

import cn.zyblogs.entity.Order;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import sun.plugin.com.AmbientProperty;

import java.util.Map;

/**
 * @Title: OrderReceiver.java
 * @Package cn.zyblogs.consumer
 * @Description: TODO 接收消息
 * @Author ZhangYB
 * @Version V1.0
 */
@Component
public class OrderReceiver {

    /**
     *  durable = "true" 是否持久化 RabbitListener可以在自动创建Exchange和queue
     *  可从配置文件读取
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "order-queue" , durable = "true"),
            exchange = @Exchange(name = "order-exchange" , durable = "true" , type = "topic"),
            key = "order.*"
    ))
    @RabbitHandler
    public void onOrderMessage(@Payload Order order ,
                               @Headers Map<String ,Object> headers ,
                               Channel channel) throws Exception{
        // 消费者操作
        System.err.println("---------收到消息，开始消费-------------");
        System.err.println("消费端order：" + order.getId());

        //手工签收 是否支持批量签收
        long deliveryTag = (long)headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }
}
