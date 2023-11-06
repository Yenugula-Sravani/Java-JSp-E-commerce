package com.codeWIthsravani.controller;

import com.codeWIthsravani.database.DBConnection;
import com.codeWIthsravani.utils.AppException;
import com.codeWIthsravani.utils.QueryUtils;
import com.codeWIthsravani.utils.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Scanner;

import static com.codeWIthsravani.controller.AuthController.loggedInUserId;
import static com.codeWIthsravani.utils.AppInput.enterInt;

import static com.codeWIthsravani.utils.Utils.println;
import static java.lang.Integer.parseInt;

public class OrderController {
    static HomeController homeController;
    DBConnection dbConnection;
    static Connection con=DBConnection.getDBConnection();
    public OrderController(HomeController homeController){
        this.homeController=homeController;
        dbConnection=new DBConnection();
    }
    public static void adminOrderFunctions() {
        println(StringUtil.ENTER_ADMIN_ORDERS_CHOICES);
        try {
            int adminOrdersChoice=enterInt(StringUtil.ENTER_ADMIN_ORDERS_CHOICES);
            if(adminOrdersChoice==99){
                adminOrderFunctions();
            }
            else {
                if (adminOrdersChoice==1){
                    //showOrders();
                }
                else {
                    homeController.invalidChoice(new AppException(StringUtil.INVALID_CHOICE));
                }
            }
        } catch (AppException e) {
            throw new RuntimeException(e);
        }

    }

    public static void orderfun(){
//        try {
//            Date currentDate = new Date();
//            PreparedStatement ps=con.prepareStatement(QueryUtils.CHECKOUT);
//            PreparedStatement ps1=con.prepareStatement(QueryUtils.CHANGE_CART_STATUS);
//            ps.setInt(1,loggedInUserId);
//            ps.executeUpdate();
//
//            Scanner scanner = new Scanner(getCartsFile());
//            while (scanner.hasNext()) {
//                String value = scanner.next();
//                String[] cartsArray = value.split(",");
//                if (parseInt(cartsArray[0]) == loggedInUserId) {
//
//                   replaceCartFile(value,"");
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
    }
}
