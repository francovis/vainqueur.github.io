package com.henallux.siteVente.dataAccess.repository;

import com.henallux.siteVente.dataAccess.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<OrderEntity,Integer>{
}
