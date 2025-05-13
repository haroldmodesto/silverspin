package org.example.shippingservice.controller;

import org.example.shippingservice.dto.DeliveryDto;
import org.example.shippingservice.service.DeliveryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    private final DeliveryService deliveryService;

    public MainController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/orders/{id}")
    @ResponseBody
    public DeliveryDto processOrderForDelivery(
            @PathVariable("id") Long id
    ) {
        return deliveryService.getDeliveryInfoForOrder(id);
    }
}
