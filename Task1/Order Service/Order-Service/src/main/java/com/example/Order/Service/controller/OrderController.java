package com.example.Order.Service.controller;

import com.example.Order.Service.serviceImpl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.Order.Service.dto.OrderRequest;
import com.example.Order.Service.dto.OrderResponse;

import java.util.UUID;

@RestController //since this is a web service we have to add annotations
@RequestMapping("/orders") //web service path
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;
    @PostMapping(consumes = "application/json", produces = "application/json")
    public @ResponseBody OrderResponse createOrder(@RequestBody OrderRequest request) {

        System.out.println("Order details: " + request);


        return orderService.createOrder(request);
    }
}
