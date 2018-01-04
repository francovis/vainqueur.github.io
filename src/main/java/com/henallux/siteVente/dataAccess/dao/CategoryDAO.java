package com.henallux.siteVente.dataAccess.dao;

import com.henallux.siteVente.dataAccess.entity.CategoryEntity;
import com.henallux.siteVente.dataAccess.repository.CategoryRepository;
import com.henallux.siteVente.dataAccess.repository.LocalityRepository;
import com.henallux.siteVente.dataAccess.util.ProviderConverter;
import com.henallux.siteVente.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class CategoryDAO {
    private CategoryRepository categoryRepository;
    private ProviderConverter providerConverter;

    @Autowired
    public CategoryDAO(CategoryRepository categoryRepository, ProviderConverter providerConverter){
        this.categoryRepository = categoryRepository;
        this.providerConverter = providerConverter;
    }

    public ArrayList<Category> getAllCategory(){
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        ArrayList<Category> categories= new ArrayList<>();
        for(CategoryEntity entity : categoryEntities){
            categories.add(providerConverter.categoryEntityToCategoryModel(entity));
        }
        return categories;
    }
}
