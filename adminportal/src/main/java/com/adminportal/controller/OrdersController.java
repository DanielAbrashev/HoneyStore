package com.adminportal.controller;


import com.adminportal.domain.*;
import com.adminportal.service.CartItemService;
import com.adminportal.service.OrderService;
import com.adminportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class OrdersController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private OrderService orderService;


    @RequestMapping("/orders")
    public String orders(Model model, Principal principal) {
        List<Order> orderList = orderService.findAll();
        model.addAttribute("orderList", orderList);

        return "orders";
    }

    @RequestMapping("/updateOrderStatus")
    public String updateOrderStatus(@RequestParam("id") Long orderId, Model model) {
        Order order = orderService.getOne(orderId);

        model.addAttribute("order", order);

        return "updateOrderStatus";
    }


    @RequestMapping(value = "/updateOrderStatus", method = RequestMethod.POST)
    public String updateOrderStatusPost(
            @ModelAttribute("order") Order order
            ,HttpServletRequest request, Model model
    ) {
        Order order1 = orderService.getOne(order.getId());
        order1.setOrderStatus(order.getOrderStatus());
        orderService.save(order1);
        List<Order> orderList = orderService.findAll();
        model.addAttribute("orderList", orderList);


        return "orders";
    }




    @RequestMapping("/orderDetail")
    public String orderDetail(
            @RequestParam("id") Long orderId,
            Principal principal, Model model
    ) {
        User user = userService.findByUsername(principal.getName());
        Order order = orderService.getOne(orderId);

        List<Order> orderList = orderService.findAll();

        List<CartItem> cartItemList = cartItemService.findByOrder(order);
        model.addAttribute("cartItemList", cartItemList);
        model.addAttribute("user", user);
        model.addAttribute("order", order);

        model.addAttribute("userPaymentList", user.getUserPaymentList());
        model.addAttribute("userShippingList", user.getUserShippingList());
        model.addAttribute("orderList", orderList);

        UserShipping userShipping = new UserShipping();
        model.addAttribute("userShipping", userShipping);


        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("listOfShippingAddresses", true);


        model.addAttribute("classActiveOrders", true);
        model.addAttribute("displayOrderDetail", true);

        return "orders";

    }

}
