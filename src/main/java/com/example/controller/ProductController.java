package com.example.controller;

import com.example.dto.ProductDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    private List<ProductDTO> productDTOS;

    @GetMapping("list-products")
    public String listProducts(Model model) {
        String fullName = "Phanh";
        String address = "Hà Nội";

        productDTOS = new ArrayList<>();
        productDTOS.add(new ProductDTO(1, "Kẹo", 1100));
        productDTOS.add(new ProductDTO(2, "Bim bim", 1200));

        model.addAttribute("fullName", fullName);
        model.addAttribute("address", address);
        model.addAttribute("products", productDTOS);
        return "list_products";
    }

    @GetMapping("product-detail")
    public String productDetail(Model model) {
        productDTOS.add(new ProductDTO(3, "Nuớc lọc", 1300));
        return "product_detail";
    }
    
    @GetMapping("search")
    public String search(@RequestParam("productName") String name, Model model) {
        List<ProductDTO> filteredProducts = productDTOS.stream()
                .filter(product -> product.getName().contains(name))
                .toList();
        
        if (filteredProducts.isEmpty()){
            model.addAttribute("message", "Không có kết quả phù hợp!");
        }

        model.addAttribute("products", filteredProducts);
        return "list_products";
    }
}
