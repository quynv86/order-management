package vn.fis.training.ordermanagement.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    void loadAllOrder() {
        log.info("ORDER: {}",orderRepository.findById(1L).get());
    }
}
