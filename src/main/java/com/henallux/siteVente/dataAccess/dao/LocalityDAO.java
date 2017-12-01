package com.henallux.siteVente.dataAccess.dao;

import com.henallux.siteVente.dataAccess.entity.LanguageEntity;
import com.henallux.siteVente.dataAccess.entity.LocalityEntity;
import com.henallux.siteVente.dataAccess.repository.LocalityRepository;
import com.henallux.siteVente.dataAccess.util.ProviderConverter;
import com.henallux.siteVente.model.Locality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LocalityDAO {
    private LocalityRepository localityRepository;
    private ProviderConverter providerConverter;

    @Autowired
    public LocalityDAO(LocalityRepository localityRepository,ProviderConverter providerConverter){
        this.localityRepository=localityRepository;
        this.providerConverter=providerConverter;
    }

    public ArrayList<Locality> getAllLocality(){
        List<LocalityEntity> localityEntities = localityRepository.findAll();
        ArrayList<Locality> localities= new ArrayList<>();
        for(LocalityEntity entity : localityEntities){
            localities.add(providerConverter.localityEntityToLocalityModel(entity));
        }
        return localities;
    }
}
