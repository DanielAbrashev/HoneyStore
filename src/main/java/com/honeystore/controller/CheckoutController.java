package com.honeystore.controller;

import com.honeystore.domain.*;
import com.honeystore.service.*;
import com.honeystore.utility.MailConstructor;
import com.honeystore.utility.USConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Controller
public class CheckoutController {

    private ShippingAddress shippingAddress = new ShippingAddress();

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailConstructor mailConstructor;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ShippingAddressService shippingAddressService;


    @Autowired
    private UserShippingService userShippingService;


    @Autowired
    private OrderService orderService;

    @RequestMapping("/checkout")
    public String checkout(
            @RequestParam("id") Long cartId,
            @RequestParam(value = "missingRequiredField", required = false) boolean missingRequiredField,
            @ModelAttribute("shippingMethod") String shippingMethod,

            Model model, Principal principal
    ) {
        User user = userService.findByUsername(principal.getName());
        /*if (cartId != user.getShoppingCart().getId()) {
            return "badRequestPage";
        }*/
        /*if (shippingMethod.equals("toAddress")) {*/
            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

            if (cartItemList.size() == 0) {
                model.addAttribute("emptyCart", true);
                return "forward:/shoppingCart/cart";
            }

            for (CartItem cartItem : cartItemList) {
                if (cartItem.getProduct().getInStockNumber() < cartItem.getQty()) {
                    model.addAttribute("notEnoughStock", true);
                    return "forward:/shoppingCart/cart";
                }
            }
            List<UserShipping> userShippingList = user.getUserShippingList();

            model.addAttribute("userShippingList", userShippingList);

            if (userShippingList.size() == 0) {
                model.addAttribute("emptyShippingList", true);
            } else {
                model.addAttribute("emptyShippingList", false);
            }

            ShoppingCart shoppingCart = user.getShoppingCart();

            for (UserShipping userShipping : userShippingList) {
                if (userShipping.isUserShippingDefault()) {
                    shippingAddressService.setByUserShipping(userShipping, shippingAddress);
                }
            }

            model.addAttribute("shippingAddress", shippingAddress);
            model.addAttribute("cartItemList", cartItemList);
            model.addAttribute("shoppingCart", user.getShoppingCart());
            model.addAttribute("classActiveShipping", true);

            if (missingRequiredField) {
                model.addAttribute("missingRequiredField", true);
            }
        /*} else {
            return "badRequestPage";
        }*/
        return "checkout";

    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String checkoutPost(
            @ModelAttribute("shippingAddress") ShippingAddress shippingAddress,
            @ModelAttribute("shippingMethod") String shippingMethod,
            Principal principal, Model model
    ) {
        ShoppingCart shoppingCart = userService.findByUsername(principal.getName()).getShoppingCart();
        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
        model.addAttribute("cartItemList", cartItemList);

        if (shippingAddress.getShippingAddressAddress().isEmpty() ||
                shippingAddress.getShippingAddressCity().isEmpty() ||
                shippingAddress.getShippingAddressName().isEmpty() ||
                shippingAddress.getShippingAddressPhone().isEmpty())
            return "redirect:/checkout?id=" + shoppingCart.getId() + "&missingRequiredField=true";
        User user = userService.findByUsername(principal.getName());

        Order order = orderService.createOrder(shoppingCart, shippingAddress, shippingMethod, user);

        mailSender.send(mailConstructor.constructOrderConfirmationEmail(user, order, Locale.ENGLISH));

        model.addAttribute("shoppingCart", shoppingCart);

        shoppingCartService.clearShoppingCart(shoppingCart);

        LocalDate today = LocalDate.now();

        LocalDate estimatedDeliveryDate;

        if (today.getDayOfWeek() == DayOfWeek.FRIDAY || today.getDayOfWeek() == DayOfWeek.SATURDAY) {
            estimatedDeliveryDate = today.plusDays(4);

        } else {
            estimatedDeliveryDate = today.plusDays(2);
        }
        model.addAttribute("estimatedDeliveryDate", estimatedDeliveryDate);
        return "orderSubmittedPage";
    }

    @RequestMapping("/setShippingAddress")
    public String setShippingAddress(
            @RequestParam("userShippingId") Long userShippingId,
            Principal principal, Model model
    ) {
        User user = userService.findByUsername(principal.getName());
        UserShipping userShipping = userShippingService.getOne(userShippingId);

        if (userShipping.getUser().getId() != user.getId()) {
            return "badRequestPage";
        } else {
            shippingAddressService.setByUserShipping(userShipping, shippingAddress);
            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());
//            BillingAddress billingAddress = new BillingAddress();


            model.addAttribute("cartItemList", cartItemList);
            model.addAttribute("shoppingCart", user.getShoppingCart());

            List<UserShipping> userShippingList = user.getUserShippingList();

            model.addAttribute("userShippingList", userShippingList);

            model.addAttribute("shippingAddress", shippingAddress);

            model.addAttribute("classActiveShipping", true);

            model.addAttribute("emptyShippingList", false);


            return "checkout";
        }
    }

}
