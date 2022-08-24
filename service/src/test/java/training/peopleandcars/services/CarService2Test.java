package training.peopleandcars.services;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import training.peopleandcars.modelapi.Car;
import training.peopleandcars.repository.CarRepository;
import training.peopleandcars.util.CarDataTest;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
class CarRepositoryTest implements CarRepository{

    @Override
    public Car save(Car car) {
        return CarDataTest.getMockCar();
    }

    @Override
    public List<Car> findAll() {
        return null;
    }

    @Override
    public List<Car> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Car> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Car> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public <S extends Car> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Car> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Car> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Car> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> strings) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Car getOne(String s) {
        return null;
    }

    @Override
    public Car getById(String s) {
        return null;
    }

    @Override
    public Car getReferenceById(String s) {
        return null;
    }

    @Override
    public <S extends Car> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Car> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Car> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Car> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Car> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Car> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Car, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public void deleteById(String vin) {

    }

    @Override
    public void delete(Car entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Car> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Optional<Car> findById(String vin) {
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
        CarServiceImpl carService = new CarServiceImpl(carRepository);
        Car carMocked = CarDataTest.getMockCar();
        Car car = new Car("VINID", "Ford", "Escape", 2022, "black");
        Mockito.when(carRepository.save(car)).thenReturn(carMocked);
        // when:
        Car carSaved = carService.save(car);
        //then:
        assertEquals(car.getVin(), carSaved.getVin());
    }

    @Test
    void givenCarWithStub_whenSave_thenReturnSavedCar() {
        // given:

        CarRepository carRepositoryTest = new CarRepositoryTest();

        CarServiceImpl carService = new CarServiceImpl(carRepositoryTest);
        Car carMocked = CarDataTest.getMockCar();
        Car car = new Car("VINID", "Ford", "Escape", 2022, "black");
        // when:
        Car carSaved = carService.save(car);
        //then:
        assertEquals(car.getVin(), carSaved.getVin());
    }
}
