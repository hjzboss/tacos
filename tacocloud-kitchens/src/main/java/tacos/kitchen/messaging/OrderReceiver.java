package tacos.kitchen.messaging;

import tacos.Order;

import javax.jms.JMSException;

public interface OrderReceiver {

    Order receiveOrder() throws JMSException;

}