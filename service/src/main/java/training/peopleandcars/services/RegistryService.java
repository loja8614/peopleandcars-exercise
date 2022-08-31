package training.peopleandcars.services;

import training.peopleandcars.model.modelapi.Car;
import training.peopleandcars.model.modelapi.People;
import training.peopleandcars.model.modelapi.Registry;

import java.util.List;
import java.util.UUID;

public interface RegistryService {
    Registry save(Registry newRegistry);
    List<Car> getCarsByPeople(UUID peopleId);
    List<People> getPeopleByCar(String vin);
}
