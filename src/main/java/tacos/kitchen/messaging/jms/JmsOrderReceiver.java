package tacos.kitchen.messaging.jms;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import tacos.Order;

import javax.jms.Destination;
import javax.jms.JMSException;

@Component
public class JmsOrderReceiver implements OrderReceiver{
    private final JmsTemplate jms;
    private final Destination des;

    public JmsOrderReceiver(JmsTemplate jms, Destination des) {
        this.jms = jms;
        this.des = des;
    }

    @Override
    public Order receiveOrder() throws JMSException {
        return (Order) jms.receiveAndConvert(des);
    }

}
