package com.henallux.siteVente.dataAccess.dao;

import com.henallux.siteVente.dataAccess.entity.ProductEntity;
import com.henallux.siteVente.dataAccess.repository.ProductRepository;
import com.henallux.siteVente.dataAccess.util.ProviderConverter;
import com.henallux.siteVente.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ProductDAO {

    private ProductRepository productRepository;
    private ProviderConverter providerConverter;

    public ProductDAO(ProductRepository productRepository, ProviderConverter providerConverter){
        this.productRepository = productRepository;
        this.providerConverter = providerConverter;
    }

    public ArrayList<Product> getAll(){
        List<ProductEntity> productEntities = productRepository.findAll();
        ArrayList<Product> products =new ArrayList<>();
        for(ProductEntity entity : productEntities){
            products.add(providerConverter.productEntityToProductModel(entity));
        }
        return products;
    }

    public Product getProductById(int id){
        ProductEntity productEntity = productRepository.findById(id);
        return providerConverter.productEntityToProductModel(productEntity);
    }

    /*public ArrayList<Product> getBestSales(){
        List<ProductEntity> productEntities = productRepository.getBestSales();
        ArrayList<Product> products = new ArrayList<>();
        if(productEntities.size()<3) {
            for (ProductEntity entity : productEntities) {
                products.add(providerConverter.productEntityToProductModel(entity));
            }
        }
        else{
            for(int i=0;i<3;i++){
                products.add(providerConverter.productEntityToProductModel(productEntities.get(i)));
            }
        }
        return products;
    }*/
}