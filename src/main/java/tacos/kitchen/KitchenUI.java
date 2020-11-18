package tacos.kitchen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tacos.Order;

@Component
@Slf4j
public class KitchenUI {

  public void displayOrder(Order order) {
    log.info("RECEIVED ORDER:  " + order);
  }
  
}
