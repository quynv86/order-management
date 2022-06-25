package vn.fis.training.ordermanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fis.training.ordermanagement.model.Order;
import vn.fis.training.ordermanagement.model.OrderStatus;

import java.time.LocalDateTime;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByStatus(OrderStatus orderStatus, Pageable page);
    Page<Order> findAllByOrOrderDateTimeBetween(LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable);
}
