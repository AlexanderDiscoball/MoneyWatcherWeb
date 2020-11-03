package alex.disco.ball.web.controllers;

import alex.disco.ball.entity.Product;

import alex.disco.ball.service.jpa.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    @Qualifier("productServiceJpa")
    ProductService productService;

    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public String home(Model model){
        List<Product> products =  productService.findAll();
        model.addAttribute("products", products);
        return "home";
    }
}
