package com.honeystore.controller;

import com.honeystore.domain.CartItem;
import com.honeystore.domain.Product;
import com.honeystore.domain.ShoppingCart;
import com.honeystore.domain.User;
import com.honeystore.service.CartItemService;
import com.honeystore.service.ProductService;
import com.honeystore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @RequestMapping("/searchByCategory")
    public String searchByCategory(
            @RequestParam("category") String category,
            Model model, Principal principal
    ) {
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

            model.addAttribute("cartItemList", cartItemList);
            ShoppingCart shoppingCart = user.getShoppingCart();
            model.addAttribute("shoppingCart", shoppingCart);
        }

        String classActiveCategory = "active" + category;
        classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
        classActiveCategory = classActiveCategory.replaceAll("&", "");
        model.addAttribute(classActiveCategory, true);

        List<Product> productList = productService.findByCategory(category);

        if (productList.isEmpty()) {
            model.addAttribute("emptyList", true);
            return "productshelf";
        }

        model.addAttribute("productList", productList);

        return "productshelf";
    }

    @RequestMapping("/searchProduct")
    public String searchProduct(
            @ModelAttribute("keyword") String keyword,
            Principal principal, Model model
    ) {
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

            model.addAttribute("cartItemList", cartItemList);
            ShoppingCart shoppingCart = user.getShoppingCart();
            model.addAttribute("shoppingCart", shoppingCart);
        }

        List<Product> productList = productService.blurrySearch(keyword);

        if (productList.isEmpty()) {
            model.addAttribute("emptyList", true);
            return "productshelf";
        }

        model.addAttribute("productList", productList);

        return "productshelf";
    }
}
