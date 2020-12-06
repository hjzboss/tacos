package tacos.kitchen.messaging.jms;

import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import tacos.Order;
import tacos.kitchen.messaging.OrderReceiver;


/**
 * 拉取模式
 */
@Profile("jms-template")
@Component
public class JmsOrderReceiver implements OrderReceiver {
    private final JmsTemplate jms;

    public JmsOrderReceiver(JmsTemplate jms) {
        this.jms = jms;
    }

    @Override
    public Order receiveOrder() {
        return (Order) jms.receiveAndConvert("tacocloud.order.queue");
    }

}
