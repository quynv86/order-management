package vn.fis.training.ordermanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.fis.training.ordermanagement.dto.CreateOrderItemDTO;
import vn.fis.training.ordermanagement.dto.OrderDTO;
import vn.fis.training.ordermanagement.model.Order;
import vn.fis.training.ordermanagement.model.OrderItem;

public interface OrderService {
    Page<OrderDTO> findAll(Pageable pageable);
    Page<OrderDTO> findAllPaidOrders(Pageable pageable);
    OrderDTO findById(Long orderId);
    Order addOrderItem(CreateOrderItemDTO createOrderItemDTO);
}
