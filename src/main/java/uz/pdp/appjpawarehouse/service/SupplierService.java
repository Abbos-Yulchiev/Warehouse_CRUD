package uz.pdp.appjpawarehouse.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appjpawarehouse.entity.Supplier;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.repositort.SupplierRepository;

import java.util.Optional;

@Service
public class SupplierService {

    final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Result addSupplier(@RequestBody Supplier supplier) {

        boolean existsByPoneNumber = supplierRepository.existsByPoneNumber(supplier.getPoneNumber());
        if (existsByPoneNumber)
            return new Result(supplier.getPoneNumber() + " the number already exist", false);

        Supplier newSupplier = new Supplier();

        newSupplier.setActive(supplier.getActive());
        newSupplier.setName(supplier.getName());
        newSupplier.setPoneNumber(supplier.getPoneNumber());
        supplierRepository.save(newSupplier);
        return new Result("New Supplier successfully added", true);
    }

    public Page<Supplier> getSuppliers(Integer page) {

        Pageable pageable = PageRequest.of(page, 20);
        Page<Supplier> suppliers = supplierRepository.findAll(pageable);

        return suppliers;
    }

    public Result editSupplier(Integer supplierId, Supplier supplier) {

        Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
        if (!optionalSupplier.isPresent())
            return new Result("Invalid supplier Id", false);

        boolean existsByPoneNumber = supplierRepository.existsByPoneNumber(supplier.getPoneNumber());
        if (existsByPoneNumber)
            return new Result(supplier.getPoneNumber() + " the number already exist", false);

        Supplier editedSupplier = optionalSupplier.get();

        editedSupplier.setActive(supplier.getActive());
        editedSupplier.setName(supplier.getName());
        editedSupplier.setPoneNumber(supplier.getPoneNumber());
        supplierRepository.save(editedSupplier);

        return new Result("Supplier edited.", true);
    }
    public Result deleteSupplier(@PathVariable Integer supplierId){

        Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
        if (!optionalSupplier.isPresent())
            return new Result("Invalid Supplier Id", false);

        supplierRepository.deleteById(supplierId);
        return new Result("Supplier deleted.", true);
    }
}
