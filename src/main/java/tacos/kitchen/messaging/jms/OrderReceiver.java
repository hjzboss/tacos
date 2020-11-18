package tacos.kitchen.messaging.jms;

import tacos.Order;

import javax.jms.JMSException;

public interface OrderReceiver {

    Order receiveOrder() throws JMSException;

}