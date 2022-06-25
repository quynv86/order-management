package vn.fis.training.ordermanagement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import vn.fis.training.ordermanagement.OrderManagementApplication;
import vn.fis.training.ordermanagement.dto.CreateOrderItemDTO;
import vn.fis.training.ordermanagement.model.Order;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes={OrderManagementApplication.class})
@Slf4j
public class OrderControllerTest {
    public static final String ADD_ORDER_ITEM_URI = "/api/order/addOrderItem";
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private  ObjectMapper objectMapper;

    @Test
    void find_By_Id() throws Exception{
        MvcResult result =  this.mockMvc.perform(MockMvcRequestBuilders.get("/api/order/findById/1")).andReturn();
//        log.info(result.getResponse().getContentAsString());
        log.info("Order: {}", objectMapper.readValue(result.getResponse().getContentAsString(), Order.class));

//        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/order/findById/1")).andExpect(status().isOk());
//        log.info(result.getResponse().getContentAsString());
    }

    @Test
    void add_A_Valid_OrderItem_Should_Return_HTTP_OK_Status() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ADD_ORDER_ITEM_URI).contentType(MediaType.APPLICATION_JSON_VALUE).content(
                objectMapper.writeValueAsString(CreateOrderItemDTO.builder()
                        .orderId(1L).productId(2L).quantity(10).build()
                ))
        ).andExpect(status().isOk());
    }

    @Test
    void add_A_OrderItem_With_NotFoundProduct_Should_Return_HTTP_4XX_Status() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ADD_ORDER_ITEM_URI).contentType(MediaType.APPLICATION_JSON_VALUE).content(
                objectMapper.writeValueAsString(CreateOrderItemDTO.builder()
                        .orderId(1L).productId(20L).quantity(10).build()
                ))
        ).andExpect(status().is4xxClientError());
    }
}
