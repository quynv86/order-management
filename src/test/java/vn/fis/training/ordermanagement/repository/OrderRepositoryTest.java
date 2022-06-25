package vn.fis.training.ordermanagement.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    @Transactional
    void queryOrderWithAnID_Using_Normal() {
        log.info("Start query order with id: 1");
        log.info("ORDER: {}",orderRepository.findById(1L).get());
    }

    @Test
    @Transactional
    void queryOrderWithAnID_Using_EntitGraph() {
        log.info("Start query order with id: 1");
        // Using only one query to fetch all relationship
        log.info("ORDER: {}",orderRepository.findByIdUsingEntityGraph(1L).get());
    }
    @Test
    void queryOrderWithAnID_Using_LeftJoinFetch() {
        log.info("Start query order with id: 1");
        // Using only one query to fetch all relationship
        log.info("ORDER: {}",orderRepository.findByIdUsingLeftJoinFetch(1L).get());
    }

    @Test
    @Transactional
    void queryAllOrders_Using_CustomOrderRepository() {
        orderRepository.findAllOrdersUsingCustomRepository().forEach((order)->log.info("Order: {}", order));
    }

}
