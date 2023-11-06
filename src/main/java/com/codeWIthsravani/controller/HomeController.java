package com.codeWIthsravani.controller;

import com.codeWIthsravani.controller.implementation.IHomeController;
import com.codeWIthsravani.utils.AppException;
import com.codeWIthsravani.utils.StringUtil;
import com.codeWIthsravani.view.HomePage;

import static com.codeWIthsravani.controller.AuthController.loggedInUserId;
import static com.codeWIthsravani.utils.AppInput.enterInt;
import static com.codeWIthsravani.utils.Utils.println;

public class HomeController implements IHomeController {

    HomePage homePage;
    AuthController authController;
    CategoriesController categoriesController;
    ProductController productController;
    CartController cartController;
    OrderController orderController;
    public HomeController(AuthController authController){
        homePage=new HomePage();
        this.authController=authController;
        categoriesController=new CategoriesController(this);
        productController=new ProductController(this);
        cartController=new CartController(this);
        orderController=new OrderController(this);
    }
    @Override
    public void printMenu() {
        homePage.printWelcome();
        homePage.printMenu();
        try {
            int choice=enterInt(StringUtil.ENTER_CHOICE);
                if(choice==1){
                    println(StringUtil.CATEGORIES);
                    categoriesController.showCategories();
                } else if (choice==2) {
                    println(StringUtil.PRODUCTS);
                    productController.showProducts();
                } else if (choice==3) {
                    cartController.ShowCartProducts();
                } else if (choice==4) {
                    System.out.println("under construction");
                } else if (choice==5) {
                    loggedInUserId=0;
                    authController.authMenu();
                } else {
                    invalidChoice(new AppException(StringUtil.INVALID_CHOICE));
                }
        } catch (AppException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void printAdminMenu() {
        homePage.printWelcome();
        homePage.printAdminMenu();
        try {
            int choice=enterInt(StringUtil.ENTER_CHOICE);
            if(choice==1){
//                categoriesController.adminCategoryFunctions();
            } else if (choice==2) {
//                productController.adminProductFunctions();
            } else if (choice==3) {
                OrderController.adminOrderFunctions();
            } else if (choice==4) {
                loggedInUserId=0;
                authController.authMenu();
            } else {
                invalidChoice(new AppException(StringUtil.INVALID_CHOICE));
            }
        } catch (AppException e) {
            throw new RuntimeException(e);
        }
    }

    void invalidChoice(AppException e) {
        println(e.toString());
        printMenu();
    }

}
