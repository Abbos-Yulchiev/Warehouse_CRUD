package uz.pdp.appjpawarehouse.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appjpawarehouse.entity.*;
import uz.pdp.appjpawarehouse.payload.InputProductDTO;
import uz.pdp.appjpawarehouse.payload.OutputProductDTO;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.repositort.*;

import java.util.Optional;

@Service
public class OutputProductService {

    final OutputProductRepository outputProductRepository;
    final OutputRepository outputRepository;
    final ProductRepository productRepository;

    public OutputProductService(OutputProductRepository outputProductRepository, OutputRepository outputRepository,
                                ProductRepository productRepository) {
        this.outputProductRepository = outputProductRepository;
        this.outputRepository = outputRepository;

        this.productRepository = productRepository;
    }

    public Result addOutputProduct(@RequestBody OutputProductDTO outputProductDTO) {

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDTO.getOutputId());
        if (!optionalOutput.isPresent())
            return new Result("Invalid output Id", false);

        Optional<Product> optionalProduct = productRepository.findById(outputProductDTO.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Invalid product Id", false);

        OutputProduct outputProduct = new OutputProduct();

        outputProduct.setAmount(outputProductDTO.getAmount());
        outputProduct.setPrice(outputProductDTO.getPrice());
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setProduct(optionalProduct.get());
        return new Result("New OutputProduct saved.", true);
    }

    public Page<OutputProduct> getOutputProductPage(Integer page) {

        Pageable pageable = PageRequest.of(page, 15);
        Page<OutputProduct> productPage = outputProductRepository.findAll(pageable);
        return productPage;
    }

    public Result editOutputProduct(Integer outputProductId, OutputProductDTO outputProductDTO) {

        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(outputProductId);
        if (!optionalOutputProduct.isPresent())
            return new Result("Invalid OutputProduct Id.", false);

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDTO.getOutputId());
        if (!optionalOutput.isPresent())
            return new Result("Invalid output Id", false);

        Optional<Product> optionalProduct = productRepository.findById(outputProductDTO.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Invalid product Id", false);

        OutputProduct outputProduct = optionalOutputProduct.get();

        outputProduct.setAmount(outputProductDTO.getAmount());
        outputProduct.setPrice(outputProductDTO.getPrice());
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setProduct(optionalProduct.get());
        return new Result("OutputProduct edited.", true);
    }
    public Result deleteOutputProduct(@PathVariable Integer outputProductId){

        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(outputProductId);
        if (!optionalOutputProduct.isPresent())
            return new Result("Invalid OutputProduct Id.", false);

        outputProductRepository.deleteById(outputProductId);
        return new Result("OutputProduct deleted.", true);
    }
}
