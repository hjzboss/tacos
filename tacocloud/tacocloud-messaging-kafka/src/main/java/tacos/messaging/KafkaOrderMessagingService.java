package tacos.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tacos.Order;

@Service
public class KafkaOrderMessagingService implements OrderMessagingService {
    private final KafkaTemplate kafkaTemplate;

    public KafkaOrderMessagingService(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public void sendOrder(Order order) {
        /*因为默认topic已经在yml中配好了，可以不用指定*/
        kafkaTemplate.sendDefault(order);
    }
}
