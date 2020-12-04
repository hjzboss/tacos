package tacos.kitchen.messaging.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tacos.Order;
import tacos.kitchen.KitchenUI;

import java.util.Optional;

@Profile("kafka-listener")
@Component
@Slf4j
public class OrderListener {
    private final KitchenUI ui;

    public OrderListener(KitchenUI ui) {
        this.ui = ui;
    }

    @KafkaListener(topics = "tacocloud.orders.topic")
    public void handle(Order order, ConsumerRecord<?,?> record){
        log.info("Received from partition {} with timestamp {}",
                record.partition(), record.timestamp());
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if(kafkaMessage.isPresent()){
            //得到Optional实例中的值
            Object message = kafkaMessage.get();
            ui.displayOrder((Order) message);
        }

    }
}
