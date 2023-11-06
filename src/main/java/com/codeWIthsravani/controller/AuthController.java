package com.codeWIthsravani.controller;

import com.codeWIthsravani.controller.implementation.IAuthController;
import com.codeWIthsravani.database.DBConnection;
import com.codeWIthsravani.models.Role;
import com.codeWIthsravani.models.User;
import com.codeWIthsravani.utils.AppException;
import com.codeWIthsravani.utils.QueryUtils;
import com.codeWIthsravani.utils.StringUtil;
import com.codeWIthsravani.view.LoginPage;
import com.codeWIthsravani.view.RegisterPage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.codeWIthsravani.utils.AppInput.enterInt;
import static com.codeWIthsravani.utils.AppInput.enterString;
import static com.codeWIthsravani.utils.Utils.println;
import static java.lang.Integer.parseInt;

public class AuthController implements IAuthController {
    private final HomeController homeController;
    private final AppController appController;
    private final LoginPage loginPage;
    private final RegisterPage registerPage;
    private final DBConnection dbConnection;
    private static final Connection con= DBConnection.getDBConnection();
    public static int loggedInUserId;
    public AuthController(AppController appController) {
        this.appController = appController;
        homeController = new HomeController(this);
        loginPage = new LoginPage();
        registerPage = new RegisterPage();
        dbConnection = new DBConnection();
    }

    @Override
    public void login() {
        String email, password;
        email = enterString(StringUtil.ENTER_EMAIL);
        password = enterString(StringUtil.ENTER_PASSWORD);

        User user = validateUser(email, password);
        if (user != null) {
            if (user.getRole() == Role.ADMIN) {
                homeController.printAdminMenu();
            } else {
                homeController.printMenu();
            }
        } else {
            loginPage.invalidCredentials();
            authMenu();
        }
    }

    private User validateUser(String email, String password) {
        User user = new User();
        try {
            PreparedStatement ps=con.prepareStatement(QueryUtils.SELECT_USER);
            ps.setString(1,email);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                user.setId(parseInt(rs.getString("id")));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                loggedInUserId = rs.getInt("id");
            }
            if (user.getEmail().equals("admin@gmail.com"))
                user.setRole(Role.ADMIN);
            else
                user.setRole(Role.USER);
            return user;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void logout() {
        loggedInUserId = 0;
        authMenu();
    }
    //db done
    @Override
    public void register() {
        String name, email, password, c_password;
        name = enterString(StringUtil.ENTER_NAME);
        email = enterString(StringUtil.ENTER_EMAIL);
        password = enterString(StringUtil.ENTER_PASSWORD);
        c_password = enterString(StringUtil.ENTER_PASSWORD_AGAIN);

        if (password.equals(c_password)) {
            try {
                PreparedStatement ps=con.prepareStatement(QueryUtils.INSERT_NEWUSER);
                ps.setString(1,name);
                ps.setString(2,email);
                ps.setString(3,password);
                ps.executeUpdate();
                registerPage.printRegistrationSuccessful();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            registerPage.passwordMisMatch();
        }
        authMenu();
    }

    @Override
    public void authMenu() {
        appController.printAuthMenu();
        int choice;
        try {
            choice = enterInt(StringUtil.ENTER_CHOICE);
            if (choice == 1) {
                login();
            } else if (choice == 2) {
                register();
            } else {
                invalidChoice(new AppException(StringUtil.INVALID_CHOICE));
            }
        } catch (AppException appException) {
            invalidChoice(appException);
        }
    }

    private void invalidChoice(AppException e) {
        println(e.toString());
        authMenu();
    }
}
