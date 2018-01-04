package com.henallux.siteVente.dataAccess.repository;

import com.henallux.siteVente.dataAccess.entity.OrderDetailsEntity;
import com.henallux.siteVente.dataAccess.entity.ProductEntity;
import com.henallux.siteVente.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity,Integer> {

    @Query("select orderDetails from OrderDetailsEntity orderDetails group by orderDetails.product order by sum(orderDetails.quantity) desc")
    public List<OrderDetailsEntity> findBestSales();

}