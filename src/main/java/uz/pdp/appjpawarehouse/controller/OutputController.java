package uz.pdp.appjpawarehouse.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpawarehouse.entity.Output;
import uz.pdp.appjpawarehouse.payload.OutputDTO;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.service.OutputService;

@RestController
@RequestMapping(value = "/output")
public class OutputController {

    final OutputService outputService;

    public OutputController(OutputService outputService) {
        this.outputService = outputService;
    }

    @PostMapping(value = "/upload")
    public Result addInput(@RequestBody OutputDTO outputDTO){

        Result result = outputService.addOutput(outputDTO);
        return result;
    }

    @GetMapping(value = "/get")
    public Page<Output> outputList(@PathVariable Integer page){

        Page<Output> outputPage = outputService.outputList(page);
        return outputPage;
    }
    @PutMapping(value = "/edit/{outputId}")
    public Result editOutput(@PathVariable Integer outputId, @RequestBody OutputDTO outputDTO){

        Result result = outputService.editOutput(outputId, outputDTO);
        return result;
    }

    @DeleteMapping(value = "/delete/{outputId}")
    public Result deleteOutput(@PathVariable Integer outputId){

        Result result = outputService.deleteOutput(outputId);
        return result;
    }
}
