package training.peopleandcars.services;


import org.modelmapper.ModelMapper;
import training.peopleandcars.modelapi.Car;
import training.peopleandcars.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public void delete(String vin) {
        carRepository.deleteById(vin);
    }

    public Optional<Car> getById(String vin) {
        return carRepository.findById(vin);
    }

}
