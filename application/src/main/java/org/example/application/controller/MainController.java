package org.example.application.controller;

import org.example.application.entity.Order;
import org.example.application.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    private final OrderService orderService;

    public MainController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("orders", orderService.findAll());

        return "index";
    }

    @GetMapping("/addNew")
    public String addNewOrder(Model model) {
        Order order = new Order();
        model.addAttribute("order", order);

        return "addNewOrder";
    }

    @PostMapping("/save")
    public String saveOrder(@ModelAttribute("order") Order order) {
        orderService.save(order);
        return "redirect:/";
    }
}
