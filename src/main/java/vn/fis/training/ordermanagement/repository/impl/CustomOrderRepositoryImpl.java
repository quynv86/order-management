package vn.fis.training.ordermanagement.repository.impl;

import org.springframework.stereotype.Component;
import vn.fis.training.ordermanagement.model.Order;
import vn.fis.training.ordermanagement.repository.CustomOrderRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CustomOrderRepositoryImpl implements CustomOrderRepository {

    @PersistenceContext
    private EntityManager entityManager;
    public CustomOrderRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public List<Order> findAllOrdersUsingCustomRepository() {
        return entityManager.createQuery("select o from Order o", Order.class).getResultList();
    }
}
