package home.ahmad.springMVC02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import home.ahmad.springMVC02.model.Product;
import home.ahmad.springMVC02.service.FileStorageService;
import home.ahmad.springMVC02.service.ProductService;
import jakarta.validation.Valid;

import java.util.Optional;

/**
 * 
 * @author Ahmad Alrefai
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, @RequestParam("file") MultipartFile file, Model model) {
        if (result.hasErrors()) {
            return "addProduct";
        }
        if (!file.isEmpty()) {
            String fileName = fileStorageService.storeFile(file);
            product.setImageUrl("/uploads/" + fileName);
        }
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable("id") Long id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "editProduct";
        } else {
            return "error";
        }
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, @Valid @ModelAttribute("product") Product product, BindingResult result, @RequestParam("file") MultipartFile file) {
        if (result.hasErrors()) {
            return "editProduct";
        }
        if (!file.isEmpty()) {
            String fileName = fileStorageService.storeFile(file);
            product.setImageUrl("/uploads/" + fileName);
        }
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }
}
