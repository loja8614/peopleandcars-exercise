package training.peopleandcars.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import training.peopleandcars.model.mapper.ConverterMapper;
import training.peopleandcars.model.modelDao.CarDao;
import training.peopleandcars.model.modelapi.Car;
import training.peopleandcars.model.modelapi.People;
import training.peopleandcars.repository.CarRepository;
import training.peopleandcars.util.CarDataTest;
import training.peopleandcars.exception.ModelNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


public class CarServiceTest {


    @Test
    void givenCar_whenSave_thenReturnSavedCar() {
        // given:
        CarRepository carRepository = Mockito.mock(CarRepository.class);
        ConverterMapper converterMapper = Mockito.mock(ConverterMapper.class);
        RegistryService registryService = Mockito.mock(RegistryService.class);
        CarServiceImpl carService = new CarServiceImpl(carRepository, registryService, converterMapper);

        Car carMocked = CarDataTest.getMockCar();
        CarDao carDaoMocked = CarDataTest.getMockCarDao();

        Mockito.when(converterMapper.toCar(carDaoMocked)).thenReturn(carMocked);
        Mockito.when(converterMapper.toCarDao(carMocked)).thenReturn(carDaoMocked);
        Mockito.when(carRepository.save(carDaoMocked)).thenReturn(carDaoMocked);

        // when:
        Car carSaved = carService.save(carMocked);

        //then:
        assertEquals(carMocked.getVin(), carSaved.getVin());
    }


    @Test
    void givenCarSaved_whenSave_thenReturnSavedCarUpdated() {

        // given:
        CarRepository carRepository = Mockito.mock(CarRepository.class);
        ConverterMapper converterMapper = Mockito.mock(ConverterMapper.class);
        RegistryService registryService = Mockito.mock(RegistryService.class);
        CarServiceImpl carService = new CarServiceImpl(carRepository, registryService, converterMapper);

        Car carMocked = CarDataTest.getMockCar();
        CarDao carDaoMocked = CarDataTest.getMockCarDao();

        Mockito.when(converterMapper.toCar(carDaoMocked)).thenReturn(carMocked);
        Mockito.when(converterMapper.toCarDao(carMocked)).thenReturn(carDaoMocked);
        Mockito.when(carRepository.save(carDaoMocked)).thenReturn(carDaoMocked);

        // when:
        Car carSaved = carService.save(carMocked);
        //then:
        assertEquals(carMocked.getVin(), carSaved.getVin());
        assertEquals(carMocked.getBrand(), carSaved.getBrand());
    }

    @Test
    void givenCars_whenGetAll_thenReturnSize3() {
        // given:
        CarRepository carRepository = Mockito.mock(CarRepository.class);
        ConverterMapper converterMapper = Mockito.mock(ConverterMapper.class);
        RegistryService registryService = Mockito.mock(RegistryService.class);
        CarServiceImpl carService = new CarServiceImpl(carRepository, registryService, converterMapper);

        CarDao carDaoMocked = CarDataTest.getMockCarDao();
        Car carMocked = CarDataTest.getMockCar();
        List<CarDao> listCarDaoMocked = CarDataTest.getAllCarsDaoMocked();

        Mockito.when(converterMapper.toCar(carDaoMocked)).thenReturn(carMocked);
        Mockito.when(carRepository.findAll()).thenReturn(listCarDaoMocked);

        // when:
        List<Car> lstCars = carService.getAll();
        //then:
        assertEquals(3, lstCars.size());
    }

    @Test
    void givenCar_whenGetById_thenReturnSameCar() {

        CarRepository carRepository = Mockito.mock(CarRepository.class);
        ConverterMapper converterMapper = Mockito.mock(ConverterMapper.class);
        RegistryService registryService = Mockito.mock(RegistryService.class);
        CarServiceImpl carService = new CarServiceImpl(carRepository, registryService, converterMapper);

        CarDao carDaoMocked = CarDataTest.getMockCarDao();
        Car carMocked = CarDataTest.getMockCar();

        Mockito.when(converterMapper.toCar(carDaoMocked)).thenReturn(carMocked);
        Mockito.when(carRepository.findById("VINID01")).thenReturn(Optional.of(carDaoMocked));

        // when:
        Car car = carService.getById("VINID01");

        //then:
        assertEquals("Ford", car.getBrand());
    }

    @Test
    void givenCar_whenDelete_thenReturnSize0() {

        CarRepository carRepository = Mockito.mock(CarRepository.class);
        ConverterMapper converterMapper = Mockito.mock(ConverterMapper.class);
        RegistryService registryService = Mockito.mock(RegistryService.class);
        CarServiceImpl carService = new CarServiceImpl(carRepository, registryService, converterMapper);

        CarDao carDaoMocked = CarDataTest.getMockCarDao();
        Car carMocked = CarDataTest.getMockCar();

        Mockito.when(carRepository.findById("VINID01")).thenReturn(Optional.of(carDaoMocked));
        Mockito.when(converterMapper.toCar(carDaoMocked)).thenReturn(carMocked);
        Mockito.when(carService.getById("VINID01")).thenReturn(carMocked);

        // when:
        carService.delete("VINID01");

        //then:
        Mockito.when(carRepository.findById("VINID01")).thenReturn(null);
        Optional<CarDao> car = carRepository.findById("VINID01");
        assertNull(car);
    }
    @Test()
    void givenCar_whenDeleteWithRegistry_thenExceptionNotDelete() {

        // given:
        CarRepository carRepository = Mockito.mock(CarRepository.class);
        ConverterMapper converterMapper = Mockito.mock(ConverterMapper.class);
        RegistryService registryService = Mockito.mock(RegistryService.class);
        CarServiceImpl carService = new CarServiceImpl(carRepository, registryService, converterMapper);

        People peopleMocked = new People(UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce"), "Person1", "LastName1", "person1@gmail.com", "F");
        List<People> lstPeopleMocked = new ArrayList<>();
        lstPeopleMocked.add(peopleMocked);

        CarDao carDaoMocked = CarDataTest.getMockCarDao();
        Car carMocked = CarDataTest.getMockCar();

        Mockito.when(registryService.getPeopleByCar("VINID01")).thenReturn(lstPeopleMocked);
        Mockito.when(converterMapper.toCar(carDaoMocked)).thenReturn(carMocked);
        Mockito.when(carRepository.findById("VINID01")).thenReturn(Optional.of(carDaoMocked));

        // when:
        ModelNotFoundException exceptionMessage = Assertions.assertThrows(ModelNotFoundException.class, () -> carService.delete("VINID01"));

        //then
        assertEquals("The car is assigned, it cannot be deleted", exceptionMessage.getMessage());

    }

    @Test()
    void givenCar_whenDeleteNotExisting_thenExceptionCarNotFound() {

        // given:
        CarRepository carRepository = Mockito.mock(CarRepository.class);
        ConverterMapper converterMapper = Mockito.mock(ConverterMapper.class);
        RegistryService registryService = Mockito.mock(RegistryService.class);
        CarServiceImpl carService = new CarServiceImpl(carRepository, registryService, converterMapper);

        List<People> lstPeopleMocked = new ArrayList<>();
        Car carMocked =new Car();
        CarDao carDaoMocked = CarDataTest.getMockCarDao();

        Mockito.when(registryService.getPeopleByCar("VINID01")).thenReturn(lstPeopleMocked);
        Mockito.when(converterMapper.toCar(carDaoMocked)).thenReturn(carMocked);
        Mockito.when(carRepository.findById("VINID01")).thenReturn(null);
        // when:
        ModelNotFoundException exceptionMessage = Assertions.assertThrows(ModelNotFoundException.class, () -> carService.delete("VINID01"));

        //then
        assertEquals("Car not found", exceptionMessage.getMessage());

    }

}
