package uz.pdp.appjpawarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appjpawarehouse.entity.Measurement;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.repositort.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    final MeasurementRepository measurementRepository;

    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public Result addMeasurementService(Measurement measurement) {

        boolean existsByName = measurementRepository.existsByName(measurement.getName());

        if (existsByName) {
            return new Result(measurement.getName() + " is measurement already exist", false);
        }
        measurementRepository.save(measurement);
        return new Result("Measurement successfully add", true);
    }

    public Result getAllMeasurement() {

        List<Measurement> measurementList = measurementRepository.findAll();
        if (measurementList.isEmpty())
            return new Result("There is a not measurement yet.", false);
        return new Result("All measurements List", true, measurementList);
    }

    public Measurement getOneMeasurement(Integer measurementId) {

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(measurementId);
        if (optionalMeasurement.isPresent())
            return optionalMeasurement.get();
        return new Measurement();
    }

    public Result editMeasurement(Integer measurementId, Measurement measurement) {

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(measurementId);
        boolean existsByName = measurementRepository.existsByName(measurement.getName());

        if (!optionalMeasurement.isPresent())
            return new Result("Invalid measurement Id!", false);
        if (existsByName)
            return new Result(measurement.getName() + " is already exist!", false);

        Measurement editedMeasurement = optionalMeasurement.get();
        editedMeasurement.setName(measurement.getName());

        measurementRepository.save(editedMeasurement);
        return new Result("New measurement successfully add", true);
    }

    public Result deleteMeasurement(Integer measurementId) {

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(measurementId);
        if (!optionalMeasurement.isPresent())
            return new Result("Invalid measurement Id", false);

        measurementRepository.deleteById(measurementId);
        return new Result("Measurement deleted", true);
    }
}
