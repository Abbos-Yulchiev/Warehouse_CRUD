package uz.pdp.appjpawarehouse.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpawarehouse.entity.Supplier;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.service.SupplierService;

@RestController
@RequestMapping(value = "supplier")
public class SupplierController {


    final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping(value = "/upload")
    public Result addSupplier(@RequestBody Supplier supplier) {

        Result result = supplierService.addSupplier(supplier);
        return result;
    }

    @GetMapping(value = "/get")
    public Page<Supplier> getSuppliers(@RequestParam("page") Integer page) {

        Page<Supplier> suppliers = supplierService.getSuppliers(page);
        return suppliers;
    }

    @PutMapping("/edit/{supplierId}")
    public Result editSupplier(@PathVariable Integer supplierId, @RequestBody Supplier supplier) {

        Result result = supplierService.editSupplier(supplierId, supplier);
        return result;
    }

    @DeleteMapping(value = "/deleteSupplier/{supplierId}")
    public Result deleteSupplier(@PathVariable Integer supplierId){

        Result result = supplierService.deleteSupplier(supplierId);
        return result;
    }
}
