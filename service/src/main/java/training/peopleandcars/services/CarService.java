package training.peopleandcars.services;

import training.peopleandcars.model.modelapi.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    Car save(Car car);
    List<Car> getAll();
    void delete(String vin);
    Car getById(String vin);
}
