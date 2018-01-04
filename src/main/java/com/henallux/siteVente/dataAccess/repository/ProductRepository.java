package com.henallux.siteVente.dataAccess.repository;

import com.henallux.siteVente.dataAccess.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
    public ProductEntity findById(int id);
}