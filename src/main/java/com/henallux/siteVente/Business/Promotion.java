package com.henallux.siteVente.Business;

import com.henallux.siteVente.dataAccess.dao.OrderDetailsDAO;
import com.henallux.siteVente.dataAccess.dao.ProductDAO;
import com.henallux.siteVente.dataAccess.dao.UserDAO;
import com.henallux.siteVente.model.Order;
import com.henallux.siteVente.model.OrderDetails;
import com.henallux.siteVente.model.Product;
import com.henallux.siteVente.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class Promotion{
    private static final int COUSSIN_PETEUR=15;
    public static final double AUGMENTATION_POUR_2_DECIMAL = 0.005;
    private OrderDetailsDAO orderDetailsDAO;
    private ProductDAO productDAO;
    private Product coussinPeteur;
    private UserDAO userDAO;

    @Autowired
    public Promotion(OrderDetailsDAO orderDetailsDAO, ProductDAO productDAO,UserDAO userDAO){
        this.orderDetailsDAO=orderDetailsDAO;
        this.productDAO=productDAO;
        this.userDAO=userDAO;
        coussinPeteur=productDAO.getProductById(COUSSIN_PETEUR);
    }

    public Promotion(){}

    public ArrayList<OrderDetails> promo (ArrayList<OrderDetails> orderDetails,
                                          User user){
        double total=total(orderDetails);
        user = userRefundable(total,user);
        orderDetails=orderDetailsUpdatePromo(total, orderDetails);
        userDAO.save(user);
        return orderDetails;
    }

    public ArrayList<OrderDetails> orderDetailsUpdatePromo(double total,
                                                           ArrayList<OrderDetails> orderDetails) {
        if (total >= 40) {
            orderDetails.add(new OrderDetails(null, null, coussinPeteur, 1, 0.00));
        }
        return orderDetails;
    }

    public User userRefundable(double total, User user){
        if(total>=20) {
            user.setRefundable(false);
                if (total >= 100) {
                    user.setRefundable(true);
                }
        }
        return user;
    }

    public double total(ArrayList<OrderDetails> orderDetails){
        double total=0;
        for (OrderDetails orderDetail : orderDetails){
            total+=orderDetail.getQuantity()*orderDetail.getRealPrice();
        }
        return total;
    }

    public OrderDetails reduction50pourcent(OrderDetails orderDetails){
        ArrayList<OrderDetails> bestSales = orderDetailsDAO.getBestSales();
        orderDetails = verifOrderdetails(bestSales,orderDetails);
        return orderDetails;
    }

    public OrderDetails verifOrderdetails( ArrayList<OrderDetails> bestSales,
                                                      OrderDetails orderDetails){
        for(OrderDetails sale : bestSales){
            if(sale.getProduct().getId().equals(orderDetails.getProduct().getId())){
                orderDetails.setRealPrice(AUGMENTATION_POUR_2_DECIMAL+orderDetails.getRealPrice()/2);
            }
        }
        return orderDetails;
    }
}
