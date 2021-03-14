package uz.pdp.appjpawarehouse.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appjpawarehouse.entity.Warehouse;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.repositort.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public Result addWarehouse(@RequestBody Warehouse warehouse) {

        boolean existsByName = warehouseRepository.existsByName(warehouse.getName());
        if (existsByName)
            return new Result("The Warehouse name already exist!", false);

        Warehouse newWarehouse = new Warehouse();

        newWarehouse.setName(warehouse.getName());
        warehouseRepository.save(newWarehouse);
        return new Result("New Warehouse successfully saved.", true);
    }

    public List<Warehouse> getWarehouses() {

        List<Warehouse> warehouseList = warehouseRepository.findAll();
        return warehouseList;
    }

    public Result editWarehouse(Integer warehouseId, Warehouse warehouse) {

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouseId);
        if (!optionalWarehouse.isPresent())
            return new Result("Invalid Warehouse Id", false);

        boolean existsByName = warehouseRepository.existsByName(warehouse.getName());
        if (existsByName)
            return new Result("The Warehouse name already exist!", false);

        Warehouse editedWarehouse = optionalWarehouse.get();

        editedWarehouse.setName(warehouse.getName());
        warehouseRepository.save(editedWarehouse);
        return new Result("Warehouse edited.", true);
    }

    public Result deleteWarehouse(@PathVariable Integer warehouseId){

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouseId);
        if (!optionalWarehouse.isPresent())
            return new Result("Invalid Warehouse Id",false);

        warehouseRepository.deleteById(warehouseId);
        return new Result("Warehouse deleted." ,true);
    }
}
