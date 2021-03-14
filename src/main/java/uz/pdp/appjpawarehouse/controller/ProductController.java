package uz.pdp.appjpawarehouse.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpawarehouse.entity.Product;
import uz.pdp.appjpawarehouse.payload.ProductDTO;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.service.ProductService;

/**
 * Product CRUD
 */

@RestController
@RequestMapping("/product")
public class ProductController {

    final
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping(value = "/upload")
    public Result addProduct(@RequestBody ProductDTO productDTO) {

        Result result = productService.addProduct(productDTO);
        return result;
    }

    @GetMapping("/get")
    public Page<Product> getAllProducts(@RequestParam("page") Integer page) {

        Page<Product> allProducts = productService.getAllProducts(page);
        return allProducts;
    }

    @GetMapping(value = "/get/{productId}")
    public Result getOneProduct(@PathVariable Integer productId){

        Result oneProduct = productService.getOneProduct(productId);
        return oneProduct;
    }

    @PutMapping(value = "edit/{productId}")
    public Result editProduct(@PathVariable Integer productId, @RequestBody ProductDTO productDTO){

        Result result = productService.editProduct(productId, productDTO);
        return result;
    }

    @DeleteMapping(value = "/delete/{productId}")
    public Result deleteProduct(@PathVariable Integer productId){

        Result result = productService.deleteProduct(productId);
        return result;
    }
}
