package tacos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tacos.messaging.OrderMessagingService;

import java.util.Date;

@RestController
@RequestMapping("/orders")
public class KafkaController {
    private final OrderMessagingService service;

    public KafkaController(OrderMessagingService service) {
        this.service = service;
    }

    @GetMapping("/send")
    public String sendMessage() {
        System.out.println("-----send-----");
        service.sendOrder(new Order("fuck", new Date()));
        return "发送成功！";
    }
}
