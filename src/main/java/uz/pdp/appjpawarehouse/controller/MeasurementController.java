package uz.pdp.appjpawarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpawarehouse.entity.Measurement;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    /*Posting*/
    @PostMapping
    public Result addMeasurement(@RequestBody Measurement measurement) {

        Result result = measurementService.addMeasurementService(measurement);
        return result;
    }

    /*Getting all measurements*/
    @GetMapping("/get")
    public Result getMeasurement() {

        Result allMeasurement = measurementService.getAllMeasurement();
        return allMeasurement;
    }

    /*Get measurement by Id*/
    @GetMapping(value = "get/{measurementId}")
    public Measurement getOneMeasurement(@PathVariable Integer measurementId) {

        Measurement oneMeasurement = measurementService.getOneMeasurement(measurementId);
        return oneMeasurement;
    }

    /*Edit measurement by id*/
    @PutMapping(value = "/edit/{measurementId}")
    public Result editMeasurement(@PathVariable Integer measurementId, @RequestBody Measurement measurement) {

        Result result = measurementService.editMeasurement(measurementId, measurement);
        return result;
    }

    /*Delete Measurement by id*/
    @DeleteMapping(value = "/delete/{measurementId}")
    public Result deleteMeasurement(@PathVariable Integer measurementId) {

        Result result = measurementService.deleteMeasurement(measurementId);
        return result;
    }
}
