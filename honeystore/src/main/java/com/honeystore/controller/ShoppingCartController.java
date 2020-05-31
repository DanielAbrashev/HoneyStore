package com.honeystore.controller;

import com.honeystore.domain.Product;
import com.honeystore.domain.CartItem;
import com.honeystore.domain.ShoppingCart;
import com.honeystore.domain.User;
import com.honeystore.repository.ProductRepository;
import com.honeystore.service.ProductService;
import com.honeystore.service.CartItemService;
import com.honeystore.service.ShoppingCartService;
import com.honeystore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/cart")
    public String shoppingCart(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        ShoppingCart shoppingCart = user.getShoppingCart();

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        shoppingCartService.updateShoppingCart(shoppingCart);

        model.addAttribute("cartItemList", cartItemList);
        model.addAttribute("shoppingCart", shoppingCart);

        return "shoppingCart";
    }

    @RequestMapping("/addItem")
    public String addItem(
            @ModelAttribute("product") Product product,
            @ModelAttribute("qty") String qty,
            Model model, Principal principal
    ) {
        User user = userService.findByUsername(principal.getName());

        product = productService.getOne(product.getId());


        if (Integer.parseInt(qty) > product.getInStockNumber()) {
            model.addAttribute("notEnoughStock", true);
            return "forward:/productDetail?id=" + product.getId();
        }

        CartItem cartItem = cartItemService.addProductToCartItem(product, user, Integer.parseInt(qty));
        model.addAttribute("addProductSuccess", true);

        if (principal != null) {


            ShoppingCart shoppingCart = user.getShoppingCart();

            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());
            shoppingCartService.updateShoppingCart(shoppingCart);

            model.addAttribute("cartItemList", cartItemList);

            model.addAttribute("shoppingCart", shoppingCart);
        }

        return "forward:/productDetail?id=" + product.getId();
    }

    @RequestMapping("/addItemIndex")
    public String addItemIndex(
            Product product, Model model, Principal principal
    ) {
        User user = userService.findByUsername(principal.getName());
        int qty = 1;
        product = productService.getOne(product.getId());

        if (qty > product.getInStockNumber()) {
            model.addAttribute("notEnoughStock", true);
            return "index";
        }

        List<Product> productList = productService.findAll();

        List<Product> productListIndexBestSold = productService.findByCategoryIndex("BestSold");
        List<Product> productListIndexNew = productService.findByCategoryIndex("New");
        List<Product> productListIndexPromotion = productService.findByCategoryIndex("Promotion");
        List<Product> productListIndexRecommended = productService.findByCategoryIndex("Recommended");

        model.addAttribute("productList", productList);
        model.addAttribute("activeAll", true);
        model.addAttribute("productListIndexBestSold", productListIndexBestSold);
        model.addAttribute("productListIndexNew", productListIndexNew);
        model.addAttribute("productListIndexPromotion", productListIndexPromotion);
        model.addAttribute("productListIndexRecommended", productListIndexRecommended);


        CartItem cartItem = cartItemService.addProductToCartItem(product, user, qty);
        model.addAttribute("addProductSuccess", true);
        if (principal != null) {


            ShoppingCart shoppingCart = user.getShoppingCart();

            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());
            shoppingCartService.updateShoppingCart(shoppingCart);

            model.addAttribute("cartItemList", cartItemList);

            model.addAttribute("shoppingCart", shoppingCart);
        }

        return "index";


    }

    @RequestMapping("/addItemProductList")
    public String addItemProductList(
            Product product, Model model, Principal principal, HttpServletRequest request
    ) {
        User user = userService.findByUsername(principal.getName());
        int qty = 1;
        product = productService.getOne(product.getId());

        if (qty > product.getInStockNumber()) {
            model.addAttribute("notEnoughStock", true);
            return "index";
        }

        int page = 0; //default page number is 0 (yes it is weird)
        int size = 2; //default page size is 10

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }


        model.addAttribute("productList", productRepository.findAll(PageRequest.of(page, size)));

        List<Product> productListIndexBestSold = productService.findByCategoryIndex("BestSold");
        List<Product> productListIndexNew = productService.findByCategoryIndex("New");
        List<Product> productListIndexPromotion = productService.findByCategoryIndex("Promotion");
        List<Product> productListIndexRecommended = productService.findByCategoryIndex("Recommended");


        model.addAttribute("activeAll", true);
        model.addAttribute("productListIndexBestSold", productListIndexBestSold);
        model.addAttribute("productListIndexNew", productListIndexNew);
        model.addAttribute("productListIndexPromotion", productListIndexPromotion);
        model.addAttribute("productListIndexRecommended", productListIndexRecommended);


        CartItem cartItem = cartItemService.addProductToCartItem(product, user, qty);
        model.addAttribute("addProductSuccess", true);
        if (principal != null) {


            ShoppingCart shoppingCart = user.getShoppingCart();

            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());
            shoppingCartService.updateShoppingCart(shoppingCart);

            model.addAttribute("cartItemList", cartItemList);

            model.addAttribute("shoppingCart", shoppingCart);
        }



        return "productList";


    }


    @RequestMapping("/removeItem")
    public String removeItem(@RequestParam("id") Long id) {
        cartItemService.removeCartItem(cartItemService.getOne(id));

        return "forward:/shoppingCart/cart";
    }

    @RequestMapping("/updateCartItem")
    public String updateShoppingCart(
            @ModelAttribute("id") Long cartItemId,
            @ModelAttribute("qty") int qty
    ) {
        CartItem cartItem = cartItemService.getOne(cartItemId);
        cartItem.setQty(qty);

        cartItemService.updateCartItem(cartItem);

        return "forward:/shoppingCart/cart";
    }
}
