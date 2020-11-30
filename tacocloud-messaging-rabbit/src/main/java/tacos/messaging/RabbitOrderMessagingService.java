package tacos.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import tacos.Order;

@Service
public class RabbitOrderMessagingService implements OrderMessagingService {
    private final RabbitTemplate rabbit;

    public RabbitOrderMessagingService(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    @Override
    public void sendOrder(Order order) {
        //第一个参数为rootingkey,第三个参数为MessagePostProcessor，可以为消息添加头信息
        rabbit.convertAndSend("tacocloud.order", order,
                message -> {
                    message.getMessageProperties().setHeader("X_ORDER_SOURCE", "WEB");
                    return message;
                });
    }
}
