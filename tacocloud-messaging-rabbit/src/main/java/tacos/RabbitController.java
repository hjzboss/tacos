package tacos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tacos.messaging.OrderMessagingService;

import java.util.Date;

@RestController
@RequestMapping("/orders")
public class RabbitController {
    private final OrderMessagingService service;

    public RabbitController(OrderMessagingService service) {
        this.service = service;
    }

    @GetMapping("/send")
    public String sendMessage() {
        System.out.println("-----send-----");
        service.sendOrder(new Order(new Date()));
        return "发送成功！";
    }
}
