package uz.pdp.appjpawarehouse.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appjpawarehouse.entity.*;
import uz.pdp.appjpawarehouse.payload.InputDTO;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.repositort.*;

import java.util.Optional;

@Service
public class InputService {

    final InputRepository inputRepository;
    final WarehouseRepository warehouseRepository;
    final ClientRepository clientRepository;
    final SupplierRepository supplierRepository;
    final CurrencyRepository currencyRepository;

    public InputService(InputRepository inputRepository, WarehouseRepository warehouseRepository,
                        ClientRepository clientRepository, SupplierRepository supplierRepository,
                        CurrencyRepository currencyRepository) {
        this.inputRepository = inputRepository;
        this.warehouseRepository = warehouseRepository;
        this.clientRepository = clientRepository;
        this.supplierRepository = supplierRepository;
        this.currencyRepository = currencyRepository;
    }

    public Result addInput(@RequestBody InputDTO inputDTO) {

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDTO.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Invalid Warehouse Id", false);

        Optional<Client> optionalClient = clientRepository.findById(inputDTO.getSupplierId());
        if (!optionalClient.isPresent())
            return new Result("Invalid Client Id", false);

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDTO.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("Invalid Supplier Id", false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDTO.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Invalid Currency Id", false);


        Input input = new Input();

        input.setCode(inputDTO.getCode());
        input.setCurrency(optionalCurrency.get());
        input.setDate(inputDTO.getDate());
        input.setFactureNumber(inputDTO.getFactureNumber());
        input.setSupplier(optionalSupplier.get());
        input.setWarehouse(optionalWarehouse.get());
        inputRepository.save(input);
        return new Result("New Input saved", true);
    }

    public Page<Input> inputList(Integer page) {

        Pageable pageable = PageRequest.of(page, 15);
        Page<Input> inputPage = inputRepository.findAll(pageable);
        return inputPage;
    }

    public Result editInput(Integer inputId, InputDTO inputDTO){

        Optional<Input> optionalInput = inputRepository.findById(inputId);
        if (!optionalInput.isPresent())
            return new Result("Invalid input Id", false);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDTO.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Invalid Warehouse Id", false);

        Optional<Client> optionalClient = clientRepository.findById(inputDTO.getSupplierId());
        if (!optionalClient.isPresent())
            return new Result("Invalid Client Id", false);

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDTO.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("Invalid Supplier Id", false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDTO.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Invalid Currency Id", false);

        Input editedInput = optionalInput.get();

        editedInput.setCode(inputDTO.getCode());
        editedInput.setCurrency(optionalCurrency.get());
        editedInput.setDate(inputDTO.getDate());
        editedInput.setFactureNumber(inputDTO.getFactureNumber());
        editedInput.setSupplier(optionalSupplier.get());
        editedInput.setWarehouse(optionalWarehouse.get());
        inputRepository.save(editedInput);
        return new Result("Input edited", true);
    }
    public Result deleteInput(@PathVariable Integer inputId){

        Optional<Input> optionalInput = inputRepository.findById(inputId);
        if (!optionalInput.isPresent())
            return new Result("Invalid Input Id", false);

        inputRepository.deleteById(inputId);
        return new Result("Input deleted.", true);
    }
}
