package uz.pdp.appjpawarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpawarehouse.entity.Warehouse;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping(value = "/upload")
    public Result addWarehouse(@RequestBody Warehouse warehouse) {

        Result result = warehouseService.addWarehouse(warehouse);
        return result;
    }

    @GetMapping(value = "/get")
    public List<Warehouse> getWarehouses() {

        List<Warehouse> warehouses = warehouseService.getWarehouses();
        return warehouses;
    }

    @PutMapping(value = "/edit/{warehouseId}")
    public Result editWarehouse(@PathVariable Integer warehouseId, @RequestBody Warehouse warehouse) {

        Result result = warehouseService.editWarehouse(warehouseId, warehouse);
        return result;
    }
    @DeleteMapping(value = "/delete/{warehouseId}")
    public Result deleteWarehouse(@PathVariable Integer warehouseId){

        Result result = warehouseService.deleteWarehouse(warehouseId);
        return result;
    }
}
