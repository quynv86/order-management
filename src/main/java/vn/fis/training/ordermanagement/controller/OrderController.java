package vn.fis.training.ordermanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import vn.fis.training.ordermanagement.dto.CreateOrderItemDTO;
import vn.fis.training.ordermanagement.dto.OrderDTO;
import vn.fis.training.ordermanagement.model.Order;
import vn.fis.training.ordermanagement.service.OrderService;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public Page<OrderDTO> findAll(@PathVariable(name="pageNumber") Integer pageNumber, @PathVariable(name="pageSize") Integer pageSize) {
        log.info("Request All Order. PageNumber: {}, PageSize: {}", pageNumber, pageSize);
        return orderService.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @GetMapping("/findById/{orderId}")
    public OrderDTO findById(@PathVariable(name="orderId")Long orderId) {
        return orderService.findById(orderId);
    }

    @PostMapping(value = "/addOrderItem", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Order addOrderItem(@RequestBody  CreateOrderItemDTO createOrderItemDTO) {
        return orderService.addOrderItem(createOrderItemDTO);
    }

}

/*
* GET http://localhost:8899/api/order/{pageNumber}/pageSize
* GET http://localhost:8899/api/order?pageNumber=0&pageSiz=10
* Tat ca succ: 200
* Exception : 500 : Internal Server Error
* Http.NotFound --> Customer Not Found
* */

