package vn.fis.training.ordermanagement.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="tbl_order")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@NamedEntityGraph(name="Order.orderItemEntityGraph", attributeNodes = @NamedAttributeNode("orderItems"))
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="order_datetime")
    private LocalDateTime orderDateTime;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

//    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @Column(name="total_amount")
    private Double totalAmount;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
