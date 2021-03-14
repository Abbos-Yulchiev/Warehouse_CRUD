package uz.pdp.appjpawarehouse.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appjpawarehouse.entity.Input;
import uz.pdp.appjpawarehouse.entity.InputProduct;
import uz.pdp.appjpawarehouse.entity.Product;
import uz.pdp.appjpawarehouse.payload.InputProductDTO;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.repositort.InputProductRepository;
import uz.pdp.appjpawarehouse.repositort.InputRepository;
import uz.pdp.appjpawarehouse.repositort.ProductRepository;

import java.util.Optional;

@Service
public class InputProductService {

    final InputProductRepository inputProductRepository;
    final InputRepository inputRepository;
    final ProductRepository productRepository;

    public InputProductService(InputProductRepository inputProductRepository, InputRepository inputRepository, ProductRepository productRepository) {
        this.inputProductRepository = inputProductRepository;
        this.inputRepository = inputRepository;
        this.productRepository = productRepository;
    }

    public Result addInputProduct(@RequestBody InputProductDTO inputProductDTO) {

        Optional<Input> optionalInput = inputRepository.findById(inputProductDTO.getInputId());
        if (!optionalInput.isPresent())
            return new Result("Invalid input Id", false);

        Optional<Product> optionalProduct = productRepository.findById(inputProductDTO.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Invalid product Id", false);

        InputProduct inputProduct = new InputProduct();

        inputProduct.setAmount(inputProductDTO.getAmount());
        inputProduct.setPrice(inputProductDTO.getPrice());
        inputProduct.setInput(optionalInput.get());
        inputProduct.setExpireDate(inputProductDTO.getExpireDate());
        inputProduct.setProduct(optionalProduct.get());
        return new Result("New InputProduct saved.", true);
    }

    public Page<InputProduct> getInputProductPage(Integer page) {

        Pageable pageable = PageRequest.of(page, 15);
        Page<InputProduct> productPage = inputProductRepository.findAll(pageable);
        return productPage;
    }

    public Result editInputProduct(Integer inputProductId, InputProductDTO inputProductDTO) {

        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(inputProductId);
        if (!optionalInputProduct.isPresent())
            return new Result("Invalid InputProduct Id.", false);

        Optional<Input> optionalInput = inputRepository.findById(inputProductDTO.getInputId());
        if (!optionalInput.isPresent())
            return new Result("Invalid input Id", false);

        Optional<Product> optionalProduct = productRepository.findById(inputProductDTO.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Invalid product Id", false);

        InputProduct inputProduct = optionalInputProduct.get();

        inputProduct.setAmount(inputProductDTO.getAmount());
        inputProduct.setPrice(inputProductDTO.getPrice());
        inputProduct.setInput(optionalInput.get());
        inputProduct.setExpireDate(inputProductDTO.getExpireDate());
        inputProduct.setProduct(optionalProduct.get());
        return new Result("InputProduct edited.", true);
    }
    public Result deleteInputProduct(@PathVariable Integer inputProductId){

        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(inputProductId);
        if (!optionalInputProduct.isPresent())
            return new Result("Invalid InputProduct Id.", false);

        inputProductRepository.deleteById(inputProductId);
        return new Result("InputProduct deleted.", true);
    }

}
