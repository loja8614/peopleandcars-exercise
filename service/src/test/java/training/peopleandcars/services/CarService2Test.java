package training.peopleandcars.services;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import training.peopleandcars.model.mapper.ConverterMapper;
import training.peopleandcars.model.modelDao.CarDao;
import training.peopleandcars.model.modelapi.Car;
import training.peopleandcars.repository.CarRepository;
import training.peopleandcars.util.CarDataTest;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarRepositoryTest implements CarRepository{


    @Override
    public CarDao save(CarDao car) {
        return CarDataTest.getMockCarDao();
    }

    @Override
    public List<CarDao> findAll() {
        return null;
    }

    @Override
    public List<CarDao> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<CarDao> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<CarDao> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public <S extends CarDao> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends CarDao> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends CarDao> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<CarDao> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> strings) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public CarDao getOne(String s) {
        return null;
    }

    @Override
    public CarDao getById(String s) {
        return null;
    }

    @Override
    public CarDao getReferenceById(String s) {
        return null;
    }

    @Override
    public <S extends CarDao> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CarDao> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends CarDao> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends CarDao> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CarDao> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CarDao> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends CarDao, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public void deleteById(String vin) {

    }

    @Override
    public void delete(CarDao entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends CarDao> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Optional<CarDao> findById(String vin) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }
}

public class CarService2Test {

    @Test
    void givenCar_whenSave_thenReturnSavedCar() {
        // given:
        CarRepository carRepository = Mockito.mock(CarRepository.class);
        RegistryService registryService = Mockito.mock(RegistryService.class);
        ConverterMapper converterMapper=Mockito.mock(ConverterMapper.class);

        CarServiceImpl carService = new CarServiceImpl(carRepository,registryService,converterMapper);
        CarDao carDaoMocked = CarDataTest.getMockCarDao();
        Car carMocked = CarDataTest.getMockCar();

        Mockito.when(converterMapper.toCar(carDaoMocked)).thenReturn(carMocked);
        Mockito.when(converterMapper.toCarDao(carMocked)).thenReturn(carDaoMocked);
        Mockito.when(carRepository.save(carDaoMocked)).thenReturn(carDaoMocked);
        // when:
        Car carSaved = carService.save(carMocked);
        //then:
        assertEquals(carMocked.getVin(), carSaved.getVin());
    }

    @Test
    void givenCarWithStub_whenSave_thenReturnSavedCar() {
        // given:

        CarRepository carRepositoryTest = new CarRepositoryTest();
        RegistryService registryService = Mockito.mock(RegistryService.class);
        ConverterMapper converterMapper=Mockito.mock(ConverterMapper.class);
        CarServiceImpl carService = new CarServiceImpl(carRepositoryTest,registryService,converterMapper);

        Car car = new Car("VINID", "Ford", "Escape", 2022, "black");
        CarDao carDao = new CarDao("VINID", "Ford", "Escape", 2022, "black");

        Mockito.when(converterMapper.toCar(carDao)).thenReturn(car);
        Mockito.when(converterMapper.toCarDao(car)).thenReturn(carDao);

        // when:
        Car carSaved = carService.save(car);
        //then:
        assertEquals("VINID",car.getVin());
    }


}
