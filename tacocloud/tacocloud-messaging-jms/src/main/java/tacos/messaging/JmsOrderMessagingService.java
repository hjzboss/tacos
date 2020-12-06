package tacos.messaging;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import tacos.Order;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {
    private final JmsTemplate jms;
    private final Destination orderQueue;

    public JmsOrderMessagingService(JmsTemplate jms, Destination orderQueue) {
        this.jms = jms;
        this.orderQueue = orderQueue;
    }

    //发送消息
    @Override
    public void sendOrder(Order order) {
        //第一个参数为目的地
        //第二个参数为对象，jms会使用MappingJackson2MessageConverter来将对象转换为消息
        //第三个参数表示对消息的后期处理
        jms.convertAndSend(orderQueue, order, this::addOrderSource);
    }

    //将消息加上额外的信息，在这里给订单消息加上了来源，来自web
    private Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }
}
