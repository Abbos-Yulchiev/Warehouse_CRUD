package uz.pdp.appjpawarehouse.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appjpawarehouse.entity.*;
import uz.pdp.appjpawarehouse.payload.InputDTO;
import uz.pdp.appjpawarehouse.payload.OutputDTO;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.repositort.*;

import java.util.Optional;

@Service
public class OutputService {

    final OutputRepository outputRepository;
    final WarehouseRepository warehouseRepository;
    final ClientRepository clientRepository;
    final SupplierRepository supplierRepository;
    final CurrencyRepository currencyRepository;

    public OutputService(OutputRepository outputRepository, WarehouseRepository warehouseRepository,
                         ClientRepository clientRepository, SupplierRepository supplierRepository,
                         CurrencyRepository currencyRepository) {
        this.outputRepository = outputRepository;
        this.warehouseRepository = warehouseRepository;
        this.clientRepository = clientRepository;
        this.supplierRepository = supplierRepository;
        this.currencyRepository = currencyRepository;
    }

    public Result addOutput(@RequestBody OutputDTO outputDTO) {

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDTO.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Invalid Warehouse Id", false);

        Optional<Client> optionalClient = clientRepository.findById(outputDTO.getSupplierId());
        if (!optionalClient.isPresent())
            return new Result("Invalid Client Id", false);

        Optional<Supplier> optionalSupplier = supplierRepository.findById(outputDTO.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("Invalid Supplier Id", false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDTO.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Invalid Currency Id", false);

        Output output = new Output();

        output.setCode(outputDTO.getCode());
        output.setCurrency(optionalCurrency.get());
        output.setDate(outputDTO.getDate());
        output.setFactureNumber(output.getFactureNumber());
        output.setWarehouse(optionalWarehouse.get());
        outputRepository.save(output);
        return new Result("New Output saved", true);
    }

    public Page<Output> outputList(Integer page) {

        Pageable pageable = PageRequest.of(page, 15);
        Page<Output> outputPage = outputRepository.findAll(pageable);
        return outputPage;
    }

    public Result editOutput(Integer outputId, OutputDTO outputDTO){

        Optional<Output> optionalOutput = outputRepository.findById(outputId);
        if (!optionalOutput.isPresent())
            return new Result("Invalid output Id", false);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDTO.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Invalid Warehouse Id", false);

        Optional<Client> optionalClient = clientRepository.findById(outputDTO.getSupplierId());
        if (!optionalClient.isPresent())
            return new Result("Invalid Client Id", false);

        Optional<Supplier> optionalSupplier = supplierRepository.findById(outputDTO.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("Invalid Supplier Id", false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDTO.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Invalid Currency Id", false);

        Output editedOutput = optionalOutput.get();

        editedOutput.setCode(outputDTO.getCode());
        editedOutput.setCurrency(optionalCurrency.get());
        editedOutput.setDate(outputDTO.getDate());
        editedOutput.setFactureNumber(outputDTO.getFactureNumber());
        editedOutput.setWarehouse(optionalWarehouse.get());
        outputRepository.save(editedOutput);
        return new Result("Output edited", true);
    }
    public Result deleteOutput(@PathVariable Integer outputId){

        Optional<Output> optionalOutput = outputRepository.findById(outputId);
        if (!optionalOutput.isPresent())
            return new Result("Invalid Output Id", false);

        outputRepository.deleteById(outputId);
        return new Result("Output deleted.", true);
    }
}
