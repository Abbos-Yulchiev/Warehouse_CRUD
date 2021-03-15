package uz.pdp.appjpawarehouse.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpawarehouse.entity.InputProduct;
import uz.pdp.appjpawarehouse.payload.InputProductDTO;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.service.InputProductService;

@RestController
@RequestMapping(value = "/inputProduct")
public class InputProductController {

    final InputProductService inputProductService;

    public InputProductController(InputProductService inputProductService) {
        this.inputProductService = inputProductService;
    }

    @PostMapping(value = "/upload")
    public Result addInputProduct(@RequestBody InputProductDTO inputProductDTO) {

        Result result = inputProductService.addInputProduct(inputProductDTO);
        return result;
    }

    @GetMapping(value = "/get")
    public Page<InputProduct> getInputProductPage(@PathVariable Integer page) {

        Page<InputProduct> inputProductPage = inputProductService.getInputProductPage(page);
        return inputProductPage;
    }

    @PutMapping(value = "/edit/{inputProductId}")
    public Result editInputProduct(@PathVariable Integer inputProductId, @RequestBody InputProductDTO inputProductDTO) {

        Result result = inputProductService.editInputProduct(inputProductId, inputProductDTO);
        return result;
    }

    @DeleteMapping(value = "/delete/{inputProductId}")
    public Result deleteInputProduct(@PathVariable Integer inputProductId){

        Result result = inputProductService.deleteInputProduct(inputProductId);
        return result;
    }

}
