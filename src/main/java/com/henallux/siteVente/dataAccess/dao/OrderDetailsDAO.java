package com.henallux.siteVente.dataAccess.dao;

import com.henallux.siteVente.dataAccess.entity.OrderDetailsEntity;
import com.henallux.siteVente.dataAccess.repository.OrderDetailsRepository;
import com.henallux.siteVente.dataAccess.util.ProviderConverter;
import com.henallux.siteVente.model.Order;
import com.henallux.siteVente.model.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class OrderDetailsDAO {

    private OrderDetailsRepository orderDetailsRepository;
    private OrderDAO orderDAO;
    private ProviderConverter providerConverter;

    @Autowired
    public OrderDetailsDAO(OrderDetailsRepository orderDetailsRepository, ProviderConverter providerConverter,
                           OrderDAO orderDAO){
        this.orderDetailsRepository=orderDetailsRepository;
        this.providerConverter=providerConverter;
        this.orderDAO=orderDAO;
    }

    public void save(ArrayList<OrderDetails> orderDetails, Order order){
        order=orderDAO.save(order);
        for(OrderDetails orderDetails2 : orderDetails){
            orderDetails2.setOrder(order);
            OrderDetailsEntity orderDetailsEntity = providerConverter.orderDetailsModelToOrderDetailsEntity(orderDetails2);
            orderDetailsRepository.save(orderDetailsEntity);
        }
    }

    public ArrayList<OrderDetails> getBestSales(){
        List<OrderDetailsEntity> productEntities = orderDetailsRepository.findBestSales();
        ArrayList<OrderDetails> products = new ArrayList<>();
        if(productEntities.size()<3) {
            for (OrderDetailsEntity entity : productEntities) {
                products.add(providerConverter.orderDetailsEntityToOrderDetailsModel(entity));
            }
        }
        else{
            for(int i=0;i<3;i++){
                products.add(providerConverter.orderDetailsEntityToOrderDetailsModel(productEntities.get(i)));
            }
        }
        return products;
    }
}
