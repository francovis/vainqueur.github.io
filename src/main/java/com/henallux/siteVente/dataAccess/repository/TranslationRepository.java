package com.henallux.siteVente.dataAccess.repository;

import com.henallux.siteVente.dataAccess.entity.TranslationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TranslationRepository extends JpaRepository<TranslationEntity,Integer> {
}
