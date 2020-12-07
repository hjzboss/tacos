package tacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class OrderSubmitMessageHandler implements GenericHandler<Order> {
    private final ApiProperties apiProperties;
    private final RestTemplate restTemplate;

    public OrderSubmitMessageHandler(ApiProperties apiProperties, RestTemplate restTemplate) {
        this.apiProperties = apiProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public Object handle(Order order, Map<String, Object> map) {
        restTemplate.postForObject(apiProperties.getUrl(), order, String.class);
        return null;
    }
}
