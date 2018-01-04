package com.henallux.siteVente.dataAccess.dao;

import com.henallux.siteVente.dataAccess.entity.OrderEntity;
import com.henallux.siteVente.dataAccess.repository.OrderRepository;
import com.henallux.siteVente.dataAccess.util.ProviderConverter;
import com.henallux.siteVente.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderDAO {
    private OrderRepository orderRepository;
    private ProviderConverter providerConverter;

    @Autowired
    public OrderDAO(OrderRepository orderRepository, ProviderConverter providerConverter){
        this.orderRepository=orderRepository;
        this.providerConverter=providerConverter;
    }

    public Order save(Order order){
        OrderEntity orderEntity = providerConverter.orderModelToOrderEntity(order);
        orderEntity = orderRepository.save(orderEntity);
        return providerConverter.orderEntityToOrderModel(orderEntity);
    }
}
