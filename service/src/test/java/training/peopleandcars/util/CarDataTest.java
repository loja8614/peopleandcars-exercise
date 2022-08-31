package training.peopleandcars.util;


import training.peopleandcars.model.modelDao.CarDao;
import training.peopleandcars.model.modelapi.Car;

import java.util.ArrayList;
import java.util.List;

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
    public static CarDao getMockCarDao(){
        CarDao car = new CarDao();
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

    public static List<CarDao> getAllCarsDaoMocked(){
        List<CarDao> carsMocked = new ArrayList<>();
        CarDao car = new CarDao("VINID", "Ford", "Escape", 2022, "black");
        carsMocked.add(car);
        car = new CarDao("VINID01", "Mercedes", "SUV", 2020, "white");
        carsMocked.add(car);
        car = new CarDao("VINID02", "Honda", "Sedan", 2021, "green");
        carsMocked.add(car);

        return carsMocked;
    }

}
