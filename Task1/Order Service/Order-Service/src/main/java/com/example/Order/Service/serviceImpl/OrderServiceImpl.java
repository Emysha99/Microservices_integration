package com.example.Order.Service.serviceImpl;

import com.example.Order.Service.dto.OrderRequest;
import com.example.Order.Service.dto.OrderResponse;
import com.example.Order.Service.dto.UserCreationRequest;
import com.example.Order.Service.dto.UserCreationResponse;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class OrderServiceImpl {
    @Autowired
    private RestTemplate restTemplate;

    public OrderResponse createOrder(OrderRequest orderRequest){
        UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setAge(orderRequest.getAge());
        userCreationRequest.setName(orderRequest.getName());
        ResponseEntity<UserCreationResponse> userCreationResponse= restTemplate.postForEntity("http://localhost:8080/users",userCreationRequest, UserCreationResponse.class);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(UUID.randomUUID().toString());
        System.out.println();
        orderResponse.setUserId(userCreationResponse.getBody().getUserID());
        orderResponse.setMessage("Successfully created the order");

        return orderResponse;
    }



}
