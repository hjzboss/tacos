package tacos.kitchen.messaging.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/*配置rabbitmq的初始化*/
@Profile({"rabbitmq-template", "rabbitmq-listener"})
@Configuration
public class RabbitConfig {

    /*创建一个消息队列*/
    @Bean
    public Queue directQueue(){
        return new Queue("tacocloud.order.queue",false);
    }

    /*创建一个exchange*/
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("tacocloud.orders",false,true);
    }

    /*要将消息队列和exchange和routingKey绑定，否则会接收不到消息！！*/
    @Bean
    public Binding bindingDirect(){
        return BindingBuilder.bind(directQueue()).to(directExchange())
                .with("tacocloud.order");
    }
}
