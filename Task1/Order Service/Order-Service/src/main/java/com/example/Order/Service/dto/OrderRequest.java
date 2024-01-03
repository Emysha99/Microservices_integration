package com.example.Order.Service.dto;

public class OrderRequest {
    private String name;
    private String age;
    private String orderType;
    private String orderDetails;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", orderType='" + orderType + '\'' +
                ", orderDetails='" + orderDetails + '\'' +
                '}';
    }
}
