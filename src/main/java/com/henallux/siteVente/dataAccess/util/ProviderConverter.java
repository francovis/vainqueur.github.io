package com.henallux.siteVente.dataAccess.util;

import com.henallux.siteVente.dataAccess.entity.*;
import com.henallux.siteVente.model.*;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ProviderConverter {
    private Mapper mapper = new DozerBeanMapper();

    public UserEntity userModelToUserEntity(User user){
        return mapper.map(user,UserEntity.class);
    }

    public User userEntityToUserModel(UserEntity userEntity){
        return mapper.map(userEntity,User.class);
    }

    public LocalityEntity localityModelToLocalityEntity(Locality locality){
        return mapper.map(locality,LocalityEntity.class);
    }

    public Locality localityEntityToLocalityModel(LocalityEntity localityEntity){
        return mapper.map(localityEntity,Locality.class);
    }

    public OrderEntity orderModelToOrderEntity(Order order){
        return mapper.map(order,OrderEntity.class);
    }

    public Order orderEntityToOrderModel(OrderEntity orderEntity){
        return mapper.map(orderEntity,Order.class);
    }

    public CategoryEntity categoryModelToCategoryEntity(Category category){
        return mapper.map(category,CategoryEntity.class);
    }

    public Category categoryEntityToCategoryModel(CategoryEntity categoryEntity){
        return mapper.map(categoryEntity,Category.class);
    }
    public LanguageEntity languageModelToLanguageEntity(Language language){
        return mapper.map(language,LanguageEntity.class);
    }

    public Language languageEntityToLanguageModel(LanguageEntity languageEntity){
        return mapper.map(languageEntity,Language.class);
    }

    public ProductEntity productModelToProductEntity(Product product){
        return mapper.map(product,ProductEntity.class);
    }

    public Product productEntityToProductModel(ProductEntity productEntity){
        return mapper.map(productEntity,Product.class);
    }

    public OrderDetailsEntity orderDetailsModelToOrderDetailsEntity(OrderDetails orderDetails){
        return mapper.map(orderDetails,OrderDetailsEntity.class);
    }

    public OrderDetails orderDetailsEntityToOrderDetailsModel(OrderDetailsEntity orderDetailsEntity){
        return mapper.map(orderDetailsEntity,OrderDetails.class);
    }
    public TranslationEntity translationDetailsModelToTranslationEntity(Translation translation){
        return mapper.map(translation,TranslationEntity.class);
    }

    public Translation translationEntityToTranslationModel(TranslationEntity translationEntity){
        return mapper.map(translationEntity,Translation.class);
    }
}