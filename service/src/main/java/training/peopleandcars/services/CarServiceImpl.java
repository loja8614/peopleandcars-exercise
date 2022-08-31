package training.peopleandcars.services;


import training.peopleandcars.exception.ModelNotFoundException;
import training.peopleandcars.model.mapper.ConverterMapper;
import training.peopleandcars.model.modelDao.CarDao;
import training.peopleandcars.model.modelapi.Car;
import training.peopleandcars.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;


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
        CarDao carDao = carRepository.findById(vin).orElseThrow(() -> new  ModelNotFoundException("Car not found"));
        return converterMapper.toCar(carDao);
    }
    public Car getById2(String vin) {

        CarDao carDao = carRepository.findById(vin).orElseThrow(new Supplier<ModelNotFoundException>() {
            @Override
            public ModelNotFoundException get() {
                return new  ModelNotFoundException("Car not found");
            }
        });
        return converterMapper.toCar(carDao);
    }
    public Car getById3(String vin) {
        CarNotFound exception= new CarNotFound();
        CarDao carDao = carRepository.findById(vin).orElseThrow(exception);
        return converterMapper.toCar(carDao);
    }
}

class CarNotFound implements Supplier<ModelNotFoundException> {

    @Override
    public ModelNotFoundException get() {
        return new  ModelNotFoundException("Car not found");
    }
}
