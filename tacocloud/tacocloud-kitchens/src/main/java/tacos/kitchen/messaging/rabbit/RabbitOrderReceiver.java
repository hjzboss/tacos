package tacos.kitchen.messaging.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tacos.Order;
import tacos.kitchen.messaging.OrderReceiver;

@Profile("rabbitmq-template")
@Component
public class RabbitOrderReceiver implements OrderReceiver {
    private final RabbitTemplate rabbit;

    public RabbitOrderReceiver(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    public Order receiveOrder() {
        //第二个参数为等待时长,此时已在yml文件中配置，不需要提供
        return (Order) rabbit.receiveAndConvert("tacocloud.order.queue");
    }
}
