package com.henallux.siteVente.dataAccess.repository;

import com.henallux.siteVente.dataAccess.entity.TranslationCategoryEntity;
import com.henallux.siteVente.dataAccess.entity.TranslationProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TranslationCategoryRepository extends JpaRepository<TranslationCategoryEntity,Integer> {

}
