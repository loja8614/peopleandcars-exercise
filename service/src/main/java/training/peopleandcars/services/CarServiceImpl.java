package training.peopleandcars.services;


import training.peopleandcars.exception.ModelNotFoundException;
import training.peopleandcars.model.mapper.ConverterMapper;
import training.peopleandcars.model.modelDao.CarDao;
import training.peopleandcars.model.modelapi.Car;
import training.peopleandcars.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;
    private RegistryService registryService;
    private ConverterMapper converterMapper;

    public CarServiceImpl(CarRepository carRepository,RegistryService registryService, ConverterMapper converterMapper) {
        this.carRepository = carRepository;
        this.registryService =registryService;
        this.converterMapper=converterMapper;
    }

    public Car save(Car car) {
        CarDao carDao= carRepository.save(converterMapper.toCarDao(car));
        return converterMapper.toCar(carDao);
    }

    public List<Car> getAll() {
        List<Car> lstCar = new ArrayList<>();
        List<CarDao> lstCarDao=carRepository.findAll();
        for (CarDao carDao: lstCarDao
             ) {
            lstCar.add(converterMapper.toCar(carDao));
        }

        return lstCar;
    }

    public void delete(String vin) {
        Car car = getById(vin);
        if (registryService.getPeopleByCar(vin).size() > 0)
            throw new ModelNotFoundException("The car is assigned, it cannot be deleted");
        carRepository.deleteById(vin);
    }

    public Car getById(String vin) {
        Optional<CarDao> carDao = carRepository.findById(vin);
        if (carDao == null)
            throw new ModelNotFoundException("Car not found");
        return converterMapper.toCar(carDao.get());
    }
}
