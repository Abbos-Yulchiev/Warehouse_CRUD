package uz.pdp.appjpawarehouse.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.appjpawarehouse.entity.Attachment;
import uz.pdp.appjpawarehouse.entity.Category;
import uz.pdp.appjpawarehouse.entity.Measurement;
import uz.pdp.appjpawarehouse.entity.Product;
import uz.pdp.appjpawarehouse.payload.ProductDTO;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.repositort.AttachmentRepository;
import uz.pdp.appjpawarehouse.repositort.CategoryRepository;
import uz.pdp.appjpawarehouse.repositort.MeasurementRepository;
import uz.pdp.appjpawarehouse.repositort.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {

    final ProductRepository productRepository;
    final CategoryRepository categoryRepository;
    final AttachmentRepository attachmentRepository;
    final MeasurementRepository measurementRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, AttachmentRepository attachmentRepository, MeasurementRepository measurementRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.attachmentRepository = attachmentRepository;
        this.measurementRepository = measurementRepository;
    }

    public Result addProduct(ProductDTO productDTO) {

        boolean existsByNameAndCategoryId = productRepository.existsByNameAndCategoryId(productDTO.getName(), productDTO.getCategoryId());

        if (existsByNameAndCategoryId)
            return new Result("There is already exit like product name", false);

        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("There is not like category", false);

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDTO.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new Result("There is not like picture", false);

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDTO.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("There is not like measurement", false);

        Product product = new Product();
        Integer maxProductId = productRepository.maxProductId();

        product.setName(productDTO.getName());
        product.setCode(String.valueOf(maxProductId + 1));
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());

        productRepository.save(product);
        return new Result("Product saved", true);
    }

    public Page<Product> getAllProducts(Integer page) {

        Pageable pageable = PageRequest.of(page, 15);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage;
    }

    public Result getOneProduct(@PathVariable Integer productId) {

        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent())
            return new Result("Invalid Product ID", false);
        return new Result("Product information", true, optionalProduct);
    }

    public Result editProduct(Integer productId, ProductDTO productDTO) {

        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent())
            return new Result("Invalid product ID", false);

        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("There is not like category!", false);

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDTO.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new Result("There is not like picture", false);

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDTO.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("There is not lik measurement", false);

        Product editedProduct = optionalProduct.get();

        editedProduct.setCategory(optionalCategory.get());
        editedProduct.setCode("2");
        editedProduct.setMeasurement(optionalMeasurement.get());
        editedProduct.setPhoto(optionalAttachment.get());
        editedProduct.setName(productDTO.getName());
        productRepository.save(editedProduct);

        return new Result("Product edited.", true);
    }

    public Result deleteProduct(@PathVariable Integer productId) {

        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent())
            return new Result("Invalid product ID", false);

        productRepository.deleteById(productId);
        return new Result("Product deleted", true);
    }
}
