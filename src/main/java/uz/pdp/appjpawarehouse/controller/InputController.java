package uz.pdp.appjpawarehouse.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpawarehouse.entity.Input;
import uz.pdp.appjpawarehouse.payload.InputDTO;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.service.InputService;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@RequestMapping(value = "/input")
public class InputController {

    final InputService inputService;

    public InputController(InputService inputService) {
        this.inputService = inputService;
    }

    @PostMapping(value = "/upload")
    public Result addInput(@RequestBody InputDTO inputDTO){

        Result result = inputService.addInput(inputDTO);
        return result;
    }

    @GetMapping(value = "/get")
    public Page<Input> inputList(@PathVariable Integer page){

        Page<Input> inputList = inputService.inputList(page);
        return inputList;
    }
    @PutMapping(value = "/edit/{inputId}")
    public Result editInput(@PathVariable Integer inputId, @RequestBody InputDTO inputDTO){

        Result result = inputService.editInput(inputId, inputDTO);
        return result;
    }

    @DeleteMapping(value = "/delete/{inputId}")
    public Result deleteInput(@PathVariable Integer inputId){

        Result result = inputService.deleteInput(inputId);
        return result;
    }
}
