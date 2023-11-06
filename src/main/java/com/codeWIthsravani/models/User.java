package com.codeWIthsravani.models;

import java.util.ArrayList;

public class User {
    private int id;
    private String name,email,password;
    private Role role;
    private Cart userCart;
    private  ArrayList<Order> userOrders;

    public Cart getUserCart() {
        return userCart;
    }

    public void setUserCart(Cart userCart) {
        this.userCart = userCart;
    }

    public ArrayList<Order> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(ArrayList<Order> userOrders) {
        this.userOrders = userOrders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
