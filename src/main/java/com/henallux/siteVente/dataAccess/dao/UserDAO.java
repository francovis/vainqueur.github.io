package com.henallux.siteVente.dataAccess.dao;

import com.henallux.siteVente.dataAccess.entity.UserEntity;
import com.henallux.siteVente.dataAccess.repository.UserRepository;
import com.henallux.siteVente.dataAccess.util.ProviderConverter;
import com.henallux.siteVente.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
@Transactional
public class UserDAO {
    private UserRepository userRepository;
    private ProviderConverter providerConverter;

    @Autowired
    public UserDAO(UserRepository userRepository, ProviderConverter providerConverter){
        this.userRepository=userRepository;
        this.providerConverter=providerConverter;
    }

    public User save(User user){
        UserEntity userEntity = providerConverter.userModelToUserEntity(user);
        userEntity=userRepository.save(userEntity);
        return providerConverter.userEntityToUserModel(userEntity);
    }

    public Boolean connectionValidation(User user){
        UserEntity userEntity = userRepository.findByName(user.getName());
        if(userEntity==null)return false;
        return new BCryptPasswordEncoder().matches(user.getPassword(),userEntity.getPassword());
    }

    public User getUser(String name){
        UserEntity userEntity = userRepository.findByName(name);
        return providerConverter.userEntityToUserModel(userEntity);
    }
}