package com.henallux.siteVente.Business;


import com.henallux.siteVente.model.OrderDetails;
import com.henallux.siteVente.model.Product;
import com.henallux.siteVente.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class PromotionTest {
    private Promotion promotion;

    @Before
    public void setUp() throws Exception {
        promotion = new Promotion();
    }

    @Test
    public void promo() throws Exception {
        User user = new User();
        OrderDetails orderDetail;
        double total=0;
        ArrayList<OrderDetails> orderDetails = new ArrayList<>();
        for(int i=1; i<=5;i++){
            orderDetail = new OrderDetails();
            orderDetail.setRealPrice(i*10.);
            orderDetail.setQuantity(i*4);
            total+=i*4*i*10.;
            orderDetails.add(orderDetail);
        }

        Assert.assertEquals(total,promotion.total(orderDetails),1.0);

        user.setRefundable(false);
        Assert.assertTrue(promotion.userRefundable(100.,user).getRefundable());
        //user.getRefundable() -> true
        Assert.assertFalse(promotion.userRefundable(25.,user).getRefundable());

        user.setRefundable(true);
        Assert.assertTrue(promotion.userRefundable(19.,user).getRefundable());
        Assert.assertTrue(promotion.userRefundable(102.,user).getRefundable());
        Assert.assertFalse(promotion.userRefundable(99.99,user).getRefundable());

        user.setRefundable(true);
        Assert.assertFalse(promotion.userRefundable(20.,user).getRefundable());

        Assert.assertEquals(total>=100,promotion.userRefundable(total,user).getRefundable());
    }

    @Test
    public void reduction50pourcent() throws Exception {
        OrderDetails orderDetail = new OrderDetails();
        OrderDetails bestSale= new OrderDetails();
        ArrayList<OrderDetails> orderDetails = new ArrayList<>();

        Product product = new Product();
        product.setId(1);
        bestSale.setProduct(product);
        orderDetail.setProduct(product);
        orderDetail.setRealPrice(29.99);
        orderDetails.add(bestSale);

        Assert.assertEquals(Promotion.AUGMENTATION_POUR_2_DECIMAL+orderDetail.getRealPrice()/2,
                promotion.verifOrderdetails(orderDetails,orderDetail).getRealPrice(),
                1.0);

        orderDetail.setRealPrice(30.);
        Assert.assertEquals(Promotion.AUGMENTATION_POUR_2_DECIMAL+orderDetail.getRealPrice()/2,
                promotion.verifOrderdetails(orderDetails,orderDetail).getRealPrice(),
                1.0);

        Assert.assertNotEquals(orderDetail.getRealPrice(),
                promotion.verifOrderdetails(orderDetails,orderDetail).getRealPrice(),
                1.0);
    }

}