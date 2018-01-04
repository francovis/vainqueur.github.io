package com.henallux.siteVente.dataAccess.repository;

import com.henallux.siteVente.dataAccess.entity.TranslationProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TranslationProductRepository extends JpaRepository<TranslationProductEntity,Integer> {

    /*@Query("select idproduct from orderDetails group by idproduct order by sum(quantity) desc")
    public List<TranslationProductEntity> getBestSales();*/
}
