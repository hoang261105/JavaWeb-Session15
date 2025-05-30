package com.example.repository.cart;

import com.example.config.ConnectionDB;
import com.example.model.Cart;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CartRepositoryImp implements CartRepository {
    @Override
    public boolean addToCart(Cart cart) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_to_cart(?,?)}");

            callSt.setInt(1, cart.getUserId());
            callSt.setInt(2, cart.getProductId());

            callSt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Cart> getAllCarts(int userId) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Cart> carts = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_all_carts(?)}");

            callSt.setInt(1, userId);
            
            ResultSet rs = callSt.executeQuery();
            carts = new ArrayList<>();

            while (rs.next()) {
                Cart cart = new Cart();
                cart.setCartId(rs.getInt("cart_id"));
                cart.setUserId(rs.getInt("user_id"));
                cart.setProductId(rs.getInt("product_id"));
                cart.setQuantity(rs.getInt("quantity"));
                carts.add(cart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carts;
    }

    @Override
    public boolean updateCart(int userId, int productId) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_cart(?,?)}");

            callSt.setInt(1, userId);
            callSt.setInt(2, productId);

            callSt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getNameById(int productId) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_name_by_id(?)}");

            callSt.setInt(1, productId);

            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                return rs.getString("product_name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public BigDecimal getPriceById(int productId) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_price_by_id(?)}");

            callSt.setInt(1, productId);

            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                return rs.getBigDecimal("price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BigDecimal.valueOf(0);
    }
}
