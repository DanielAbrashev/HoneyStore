package com.honeystore.controller;

import com.honeystore.config.SecurityConfig;
import com.honeystore.domain.*;
/*import com.honeystore.domain.security.PasswordResetToken;*/
import com.honeystore.domain.security.Role;
import com.honeystore.domain.security.UserRole;
import com.honeystore.repository.ProductRepository;
import com.honeystore.service.*;
import com.honeystore.service.impl.ProductServiceImpl;
import com.honeystore.service.impl.UserSecurityService;
import com.honeystore.utility.MailConstructor;
import com.honeystore.utility.SecurityUtility;
import com.honeystore.utility.USConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailConstructor mailConstructor;

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserShippingService userShippingService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductRepository productRepository;


    @RequestMapping("/")
    public String index(Model model, Principal principal,String categoryIndex) {
        List<Product> productList = productService.findAll();

        List<Product> productListIndexBestSold = productService.findByCategoryIndex("BestSold");
        List<Product> productListIndexNew = productService.findByCategoryIndex("New");
        List<Product> productListIndexPromotion = productService.findByCategoryIndex("Promotion");
        List<Product> productListIndexRecommended = productService.findByCategoryIndex("Recommended");
        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            ShoppingCart shoppingCart = user.getShoppingCart();

            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());
            shoppingCartService.updateShoppingCart(shoppingCart);

            model.addAttribute("cartItemList", cartItemList);

            model.addAttribute("shoppingCart", shoppingCart);

        }

        model.addAttribute("productList", productList);
        model.addAttribute("productListIndexBestSold", productListIndexBestSold);
        model.addAttribute("productListIndexNew", productListIndexNew);
        model.addAttribute("productListIndexPromotion", productListIndexPromotion);
        model.addAttribute("productListIndexRecommended", productListIndexRecommended);
        model.addAttribute("activeAll", true);
       /* if (principal != null) {
            product = productService.getOne(product.getId());
            User user = userService.findByUsername(principal.getName());

            if (Integer.parseInt(qty) > product.getInStockNumber()) {
                model.addAttribute("notEnoughStock", true);
                return "forward:/productDetail?id=" + product.getId();
            }

            CartItem cartItem = cartItemService.addProductToCartItem(product, user, Integer.parseInt(qty));
            model.addAttribute("addProductSuccess", true);

            return "forward:/productDetail?id=" + product.getId();
        }*/

        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("classActiveLogin", true);
        return "myAccount";
    }

    @RequestMapping("/shipping")
    public String shipping(Principal principal, Model model) {
        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            ShoppingCart shoppingCart = user.getShoppingCart();

            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());
            shoppingCartService.updateShoppingCart(shoppingCart);

            model.addAttribute("cartItemList", cartItemList);

            model.addAttribute("shoppingCart", shoppingCart);
        }
        return "shipping";
    }

    @RequestMapping("/aboutUs")
    public String aboutUs(Model model, Principal principal) {
        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

            model.addAttribute("cartItemList", cartItemList);
            ShoppingCart shoppingCart = user.getShoppingCart();
            model.addAttribute("shoppingCart", shoppingCart);
        }
        return "aboutUs";
    }

    @RequestMapping("/badRequest")
    public String badRequest(Principal principal, Model model) {
        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            ShoppingCart shoppingCart = user.getShoppingCart();

            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());
            shoppingCartService.updateShoppingCart(shoppingCart);

            model.addAttribute("cartItemList", cartItemList);

            model.addAttribute("shoppingCart", shoppingCart);
        }
        return "badRequestPage";
    }

    @GetMapping("/productList")
    public String productList(HttpServletRequest request, Model model, Principal principal) {

        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            ShoppingCart shoppingCart = user.getShoppingCart();

            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());
            shoppingCartService.updateShoppingCart(shoppingCart);

            model.addAttribute("cartItemList", cartItemList);

            model.addAttribute("shoppingCart", shoppingCart);
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
        return "productList";
    }

    @RequestMapping("/productDetail")
    public String productDetail(
            @PathParam("id") Long id,
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
/*
        productService.findById(id).ifPresent(o -> model.addAttribute("product", o));
*/
        Product product = productService.getOne(id);
        model.addAttribute("product", product);

        List<Integer> qtyList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        model.addAttribute("qtyList", qtyList);
        model.addAttribute("qty", 1);

        return "productDetail";

    }


    @RequestMapping("/forgetPassword")
    public String forgottenPassword(
            HttpServletRequest request,
            @ModelAttribute("email") String userEmail,
            Model model) {

        model.addAttribute("classActiveForgottenPassword", true);

        User user = userService.findByEmail(userEmail);


        if (user == null) {
            model.addAttribute("emailNotExists", true);
            return "myAccount";
        } else {

            String password = SecurityUtility.randomPassword();

            String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
            user.setPassword(encryptedPassword);

            userService.save(user);

            String token = UUID.randomUUID().toString();
            /*userService.createPasswordResetTokenForUser(user, token);*/

            String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

            SimpleMailMessage newEmail = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, user, password);

            mailSender.send(newEmail);
            model.addAttribute("forgetPasswordEmailSent", true);
        }
        return "myAccount";
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String newUserPost(
            HttpServletRequest request,
            @ModelAttribute("username") String username,
            @ModelAttribute("createPassword") String password,
            Model model) throws Exception {
        model.addAttribute("classActiveNewAccount", true);
        model.addAttribute("username", username);

        if (userService.findByEmail(username) != null) {
            model.addAttribute("emailExists", true);

            return "myAccount";
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(user.getUsername());



        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);

        Role role = new Role();
        role.setRoleId(1);
        role.setName("ROLE_USER");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, role));
        userService.createUser(user, userRoles);
       /* String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);
        String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();*/

        SimpleMailMessage email = mailConstructor.constructWelcomeEmail(user, request.getLocale());

        mailSender.send(email);
        model.addAttribute("emailSent", true);
        model.addAttribute("orderList", user.getOrderList());

        return "myAccount";
    }


    @RequestMapping("/newUser")
    public String newUser(Locale locale,
                          @RequestParam("token") String token,
                          Model model) {
        /*PasswordResetToken passwordResetToken = userService.getPasswordResetToken(token);*/

        /*if (passwordResetToken == null) {
            String message = "Invalid Token.";
            model.addAttribute("message", message);
            return "redirect:/badRequest";
        }*/
        /*User user = passwordResetToken.getUser();*/
        /*String username = user.getUsername();*/

        /*UserDetails userDetails = userSecurityService.loadUserByUsername(username);*/

        /*Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());*/

        /*SecurityContextHolder.getContext().setAuthentication(authentication);*/
        model.addAttribute("classActiveEdit", true);

        /*model.addAttribute("user", user);
*/

        return "myProfile";
    }

    @RequestMapping(value = "/user/addNewUser", method = RequestMethod.GET)
    public String addNewUser(
            Locale locale, Model model,
            @RequestParam("token") String token) {

        /*PasswordResetToken passToken = userService.getPasswordResetToken(token);*/
        /*if (passToken == null) {
            String message = "Invalid Token.";
            model.addAttribute("message", message);
            return "redirect:/badRequest";
        }*/

        Calendar cal = Calendar.getInstance();
       /* if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            model.addAttribute("message", "Token has expired.");
            return "redirect:/badRequest";
        }*/

        /*User user = passToken.getUser();*/

        /*String username = user.getUsername();*/

        /*UserDetails userDetails = userSecurityService.loadUserByUsername(username);*/

/*
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
*/

        /*SecurityContextHolder.getContext().setAuthentication(authentication);*/

        /*model.addAttribute("user", user);*/

        model.addAttribute("classActiveEdit", true);

        return "myProfile";
    }

    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public String updateUserInfo(
            @ModelAttribute("user") User user,
            @ModelAttribute("newPassword") String newPassword, Principal principal,
            Model model) throws Exception {

        User currentUser = userService.getOne(user.getId());


        if (currentUser == null) {
            throw new Exception("User not found");
        }

        if (userService.findByEmail(user.getEmail()) != null) {
            if (userService.findByEmail(user.getEmail()).getId() != currentUser.getId()) {
                model.addAttribute("emailExists", true);
                return "myProfile";
            }
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            if (userService.findByUsername(user.getUsername()).getId() != currentUser.getId()) {
                model.addAttribute("usernameExists", true);
                return "myProfile";
            }
        }

        if (newPassword != null && !newPassword.isEmpty() && !newPassword.equals("")) {
            BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
            String dbPassword = currentUser.getPassword();
                currentUser.setPassword(SecurityUtility.passwordEncoder().encode(newPassword));
        }

        SecurityConfig securityConfig = new SecurityConfig();

        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setUsername(user.getUsername());
        currentUser.setEmail(user.getEmail());

        userService.save(currentUser);
        model.addAttribute("updateSuccess", true);
        model.addAttribute("user", currentUser);
        model.addAttribute("classActiveEdit", true);
        model.addAttribute("orderList", user.getOrderList());
        model.addAttribute("listOfShippingAddresses", true);

        UserDetails userDetails = userSecurityService.loadUserByUsername(currentUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);


        List<CartItem> cartItemList = cartItemService.findByShoppingCart(currentUser.getShoppingCart());

        model.addAttribute("cartItemList", cartItemList);
        ShoppingCart shoppingCart = currentUser.getShoppingCart();
        model.addAttribute("shoppingCart", shoppingCart);


        return "myProfile";

    }


    @RequestMapping("/myProfile")
    public String myProfile(Model model, Principal principal) {


        User user = userService.findByUsername(principal.getName());
        if (principal != null) {

            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

            model.addAttribute("cartItemList", cartItemList);
            ShoppingCart shoppingCart = user.getShoppingCart();
            model.addAttribute("shoppingCart", shoppingCart);
        }

        model.addAttribute("user", user);
        model.addAttribute("userShippingList", user.getUserShippingList());
        model.addAttribute("orderList", user.getOrderList());

        UserShipping userShipping = new UserShipping();
        model.addAttribute("userShipping", userShipping);

        model.addAttribute("listOfShippingAddresses", true);
        model.addAttribute("classActiveEdit", true);

        return "myProfile";
    }

    @RequestMapping("/orderDetail")
    public String orderDetail(
            @RequestParam("id") Long orderId,
            Principal principal, Model model
    ) {
        User user = userService.findByUsername(principal.getName());
        Order order = orderService.getOne(orderId);

        if (principal != null) {

            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

            model.addAttribute("cartItemList", cartItemList);
            ShoppingCart shoppingCart = user.getShoppingCart();
            model.addAttribute("shoppingCart", shoppingCart);
        }

        if (order.getUser().getId() != user.getId()) {
            return "badRequestPage";
        } else {
            List<CartItem> cartItemList = cartItemService.findByOrder(order);
            model.addAttribute("cartItemList", cartItemList);
            model.addAttribute("user", user);
            model.addAttribute("order", order);

            model.addAttribute("userShippingList", user.getUserShippingList());
            model.addAttribute("orderList", user.getOrderList());

            UserShipping userShipping = new UserShipping();
            model.addAttribute("userShipping", userShipping);


            model.addAttribute("listOfShippingAddresses", true);


            model.addAttribute("classActiveOrders", true);
            model.addAttribute("displayOrderDetail", true);

            return "myProfile";
        }
    }

    @RequestMapping("/listOfShippingAddresses")
    public String listOfShippingAddresses(
            Model model, Principal principal, HttpServletRequest request
    ) {

        User user = userService.findByUsername(principal.getName());

        if (principal != null) {

            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

            model.addAttribute("cartItemList", cartItemList);
            ShoppingCart shoppingCart = user.getShoppingCart();
            model.addAttribute("shoppingCart", shoppingCart);
        }
        model.addAttribute("user", user);
        model.addAttribute("userShippingList", user.getUserShippingList());
        model.addAttribute("orderList", user.getOrderList());

        model.addAttribute("listOfShippingAddresses", true);
        model.addAttribute("classActiveShipping", true);

        return "myProfile";
    }

    @RequestMapping("/addNewShippingAddress")
    public String addNewShippingAddress(
            Model model, Principal principal
    ) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);

        if (principal != null) {

            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

            model.addAttribute("cartItemList", cartItemList);
            ShoppingCart shoppingCart = user.getShoppingCart();
            model.addAttribute("shoppingCart", shoppingCart);
        }

        model.addAttribute("addNewShippingAddress", true);
        model.addAttribute("classActiveShipping", true);

        UserShipping userShipping = new UserShipping();
        //for getting the model
        model.addAttribute("userShipping", userShipping);

        model.addAttribute("userShippingList", user.getUserShippingList());
        model.addAttribute("orderList", user.getOrderList());

        return "myProfile";
    }

    @RequestMapping(value = "/addNewShippingAddress", method = RequestMethod.POST)
    public String addNewShippingAddress(
            @ModelAttribute("userShipping") UserShipping userShipping,
            Principal principal,
            Model model
    ) {

        User user = userService.findByUsername(principal.getName());
        userService.updateUserShipping(userShipping, user);

        if (principal != null) {

            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

            model.addAttribute("cartItemList", cartItemList);
            ShoppingCart shoppingCart = user.getShoppingCart();
            model.addAttribute("shoppingCart", shoppingCart);
        }

        model.addAttribute("user", user);
        model.addAttribute("classActiveShipping", true);
        model.addAttribute("userShippingList", user.getUserShippingList());
        model.addAttribute("listOfShippingAddresses", true);
        model.addAttribute("orderList", user.getOrderList());

        return "myProfile";

    }

    @RequestMapping("/updateUserShipping")
    public String updateUserShipping(
            @ModelAttribute("id") Long shippingAddressId,
            Principal principal,
            Model model
    ) {
        User user = userService.findByUsername(principal.getName());

        if (principal != null) {

            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

            model.addAttribute("cartItemList", cartItemList);
            ShoppingCart shoppingCart = user.getShoppingCart();
            model.addAttribute("shoppingCart", shoppingCart);
        }

        UserShipping userShipping = userShippingService.getOne(shippingAddressId);

        if (user.getId() != userShipping.getUser().getId()) {
            return "badRequestPage";
        } else {

            List<String> stateList = USConstants.listOfUSStatesCode;
            Collections.sort(stateList);
            model.addAttribute("stateList", stateList);

            model.addAttribute("userShipping", userShipping);
            model.addAttribute("user", user);
            model.addAttribute("userShippingList", user.getUserShippingList());
            model.addAttribute("classActiveShipping", true);
            model.addAttribute("addNewShippingAddress", true);
            model.addAttribute("orderList", user.getOrderList());


            return "myProfile";
        }
    }

    @RequestMapping(value = "/setDefaultShipping", method = RequestMethod.POST)
    public String setDefaultShipping(
            @ModelAttribute("defaultShipping") Long shippingAddressId,
            Principal principal,
            Model model
    ) {
        User user = userService.findByUsername(principal.getName());
        userService.setUserDefaultShipping(shippingAddressId, user);

        if (principal != null) {

            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

            model.addAttribute("cartItemList", cartItemList);
            ShoppingCart shoppingCart = user.getShoppingCart();
            model.addAttribute("shoppingCart", shoppingCart);
        }

        model.addAttribute("user", user);
        model.addAttribute("userShippingList", user.getUserShippingList());

        model.addAttribute("listOfShippingAddresses", true);
        model.addAttribute("classActiveShipping", true);
        model.addAttribute("orderList", user.getOrderList());

        return "myProfile";

    }

    @RequestMapping("/removeUserShipping")
    public String removeUserShipping(
            @ModelAttribute("id") Long shippingId,
            Principal principal,
            Model model
    ) {
        User user = userService.findByUsername(principal.getName());

        if (principal != null) {

            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

            model.addAttribute("cartItemList", cartItemList);
            ShoppingCart shoppingCart = user.getShoppingCart();
            model.addAttribute("shoppingCart", shoppingCart);
        }
        UserShipping userShipping = userShippingService.getOne(shippingId);

        if (user.getId() != userShipping.getUser().getId()) {
            return "badRequestPage";
        } else {
            userShippingService.removeById(shippingId);
            model.addAttribute("userShipping", userShipping);
            model.addAttribute("user", user);
            model.addAttribute("userShippingList", user.getUserShippingList());
            model.addAttribute("classActiveShipping", true);
            model.addAttribute("listOfShippingAddresses", true);
            model.addAttribute("orderList", user.getOrderList());


            return "myProfile";
        }

    }


}
