package vn.fis.training.ordermanagement.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.fis.training.ordermanagement.dto.CreateOrderItemDTO;
import vn.fis.training.ordermanagement.dto.OrderDTO;
import vn.fis.training.ordermanagement.model.Order;
import vn.fis.training.ordermanagement.model.OrderItem;
import vn.fis.training.ordermanagement.model.OrderStatus;
import vn.fis.training.ordermanagement.model.Product;
import vn.fis.training.ordermanagement.repository.OrderRepository;
import vn.fis.training.ordermanagement.repository.ProductRepository;
import vn.fis.training.ordermanagement.service.OrderService;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository){
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }
    @Override
    @Transactional(readOnly = true)
    public Page<OrderDTO> findAll(Pageable pageable) {
        log.info("Query all Order. PageNumber: {}, PageSize: {}", pageable.getPageNumber(), pageable.getPageSize());
        return orderRepository.findAll(pageable).map(OrderDTO.Mapper::fromEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<OrderDTO> findAllPaidOrders(Pageable pageable) {
        return orderRepository.findAllByStatus(OrderStatus.PAID, pageable).map(OrderDTO.Mapper::fromEntity);
    }

    @Override
    public OrderDTO findById(Long orderId) {
        return OrderDTO.Mapper.fromEntity(orderRepository.findById(orderId).orElseThrow(
                () -> {throw new IllegalArgumentException(String.format("Not found order with id %s",orderId));}));
    }

    @Override
    @Transactional
    public Order addOrderItem(CreateOrderItemDTO createOrderItemDTO) {
        Order order = orderRepository.findById(createOrderItemDTO.getOrderId()).orElseThrow(()-> new IllegalArgumentException("Order Not Found. OrderID: " + createOrderItemDTO.getOrderId()));
        Product product = productRepository.findById(createOrderItemDTO.getProductId()).orElseThrow(()-> new IllegalArgumentException("Product Not Found. ProductId: " + createOrderItemDTO.getProductId()));
        OrderItem orderItem = OrderItem.builder().order(order).quantity(createOrderItemDTO.getQuantity())
                .product(product)
                .amount(product.getPrice() * createOrderItemDTO.getQuantity())
                .build();
        order.getOrderItems().add(orderItem);
        Double totalAmount = order.getOrderItems().stream().mapToDouble(item -> item.getAmount()).reduce(0,(amountOne, amountTwo)-> amountOne + amountTwo);
        order.setTotalAmount(totalAmount);
//        return orderRepository.save(order);
        return order;
    }
}
