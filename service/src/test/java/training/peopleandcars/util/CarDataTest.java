package training.peopleandcars.util;


import training.peopleandcars.modelapi.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDataTest {
    public static Car getMockCar(){
        Car car = new Car();
        car.setVin("VINID");
        car.setBrand("Ford");
        car.setColor("Black");
        car.setModel("Escape");
        car.setYear(2022);
        return car;

    }
    public static Car getMockCarUpdated(){
        Car car = new Car();
        car.setVin("VINID");
        car.setModel("RANGER");
        car.setBrand("FORD");
        car.setColor("Green");
        car.setYear(2021);
        return car;

    }
    public static List<Car> getAllCarsMocked(){
        List<Car> carsMocked = new ArrayList<>();
        Car car = new Car("VINID", "Ford", "Escape", 2022, "black");
        carsMocked.add(car);
        car = new Car("VINID01", "Mercedes", "SUV", 2020, "white");
        carsMocked.add(car);
        car = new Car("VINID02", "Honda", "Sedan", 2021, "green");
        carsMocked.add(car);

        return carsMocked;
    }

}
