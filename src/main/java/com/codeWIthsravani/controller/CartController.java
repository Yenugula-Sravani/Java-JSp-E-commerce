package com.codeWIthsravani.controller;

import com.codeWIthsravani.controller.implementation.ICartController;
import com.codeWIthsravani.database.DBConnection;
import com.codeWIthsravani.utils.AppException;
import com.codeWIthsravani.utils.QueryUtils;
import com.codeWIthsravani.utils.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.codeWIthsravani.controller.AuthController.loggedInUserId;
import static com.codeWIthsravani.database.DBConnection.getDBConnection;
import static com.codeWIthsravani.utils.AppInput.enterInt;
import static com.codeWIthsravani.utils.Utils.println;
import static java.lang.Integer.parseInt;

public class CartController implements ICartController {
    HomeController homeController;
    OrderController orderController;
    private final DBConnection dbConnection;

    private static final Connection con= getDBConnection();
    public CartController(HomeController homeController) {
        this.homeController = homeController;
        orderController =new OrderController(homeController);
        dbConnection = new DBConnection();

    }
    //dbdone
    public int getCount(int choice){
        int count=1;
        PreparedStatement ps= null;
        try {
            ps = con.prepareStatement(QueryUtils.CHECK_CARTS);
            ps.setInt(1,choice);
            ps.setInt(2,loggedInUserId);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                count=rs.getInt("counts");
                System.out.println(count);
                count+=1;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return count;
    }

    //dbdone
    public boolean isAvailable(int choice){
        boolean isFound = false;
        try {
            PreparedStatement ps=con.prepareStatement(QueryUtils.CHECK_CARTS);
            ps.setInt(1,choice);
            ps.setInt(2,loggedInUserId);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                isFound=true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return isFound;
    }
    //dbdone
    @Override
    public void addToCart(int categoryChoice) {
        try {
            if(!isAvailable(categoryChoice)){
                PreparedStatement ps= con.prepareStatement(QueryUtils.ADD_TO_CART);
                ps.setInt(1, loggedInUserId);
                ps.setInt(2, categoryChoice);
                ps.setInt(3, getCount(categoryChoice));
                ps.executeUpdate();
            }
            else{
                PreparedStatement ps= con.prepareStatement(QueryUtils.UPDATE_CART);
                ps.setInt(1, getCount(categoryChoice));
                ps.setInt(2, categoryChoice);
                ps.setInt(3, loggedInUserId);
                ps.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //dbdone
    @Override
    public void ShowCartProducts() {
        try {
            int total=0;
            System.out.println(loggedInUserId);
            PreparedStatement ps=con.prepareStatement(QueryUtils.SELECT_ALL_CARTS);
            ps.setString(1,String.valueOf(loggedInUserId));
            ResultSet rs=ps.executeQuery();
            int prodId,i=1;
            while (rs.next()){
                prodId=rs.getInt("productId");
                PreparedStatement ps1=con.prepareStatement(QueryUtils.SELECT_PARTICULAR_CART);
                ps1.setString(1,String.valueOf(prodId));
                ResultSet rs1=ps1.executeQuery();
                while(rs1.next()){
                    System.out.println(i+"."+rs1.getString("name")+"x"+rs.getInt("counts")+"-->"+rs.getInt("counts")*rs1.getInt("price"));
                    total+=rs.getInt("counts")*rs1.getInt("price");
                    i++;
                }
            }
            println("Total price = "+ total);
            println("1.checkOut");
            println("99.Back");
            int choice = enterInt(StringUtil.ENTER_CHOICE);
            if (choice == 99) {
                homeController.printMenu();
            } else if(choice==1) {
                OrderController.orderfun();
            }
            else{
                homeController.invalidChoice(new AppException(StringUtil.INVALID_CHOICE));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}