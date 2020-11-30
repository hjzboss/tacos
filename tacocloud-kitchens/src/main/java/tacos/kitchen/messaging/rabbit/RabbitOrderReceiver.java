package tacos.kitchen.messaging.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tacos.Order;

@Profile("rabbitmq-template")
@Component
public class RabbitOrderReceiver {
    private final RabbitTemplate rabbit;
    private final MessageConverter converter;

    public RabbitOrderReceiver(RabbitTemplate rabbit, MessageConverter converter) {
        this.rabbit = rabbit;
        this.converter = converter;
    }

    public Order receiveOrder() {
        //第二个参数为等待时长,此时已在yml文件中配置，不需要提供
        return (Order) rabbit.receiveAndConvert("tacocloud.order.queue");
    }
}
