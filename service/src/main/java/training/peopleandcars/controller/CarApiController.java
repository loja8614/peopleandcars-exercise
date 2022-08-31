package training.peopleandcars.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import training.peopleandcars.model.modelapi.Car;
import training.peopleandcars.model.modelapi.People;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import training.peopleandcars.services.CarService;
import training.peopleandcars.services.RegistryService;

import javax.validation.Valid;

import java.util.List;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-08-22T19:22:29.543238700-05:00[America/Mexico_City]")
@Controller
@RestControllerAdvice
@RequestMapping("${openapi.peopleAndCars.base-path:/api}")
public class CarApiController implements CarApi {

    private final NativeWebRequest request;
    private CarService carService;
    private RegistryService registryService;

    public CarApiController(CarService carService,RegistryService registryService,NativeWebRequest request ) {
        this.carService = carService;
        this.registryService=registryService;
        this.request = request;
    }

    @Override
    public ResponseEntity<Car> addUpdateCar(@Valid Car car) {
        return new ResponseEntity<>(carService.save(car), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Car> deleteCar(String carVin) {
        carService.delete(carVin);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Car>> getAllCars() {
        return new ResponseEntity<>(carService.getAll(), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<List<People>> getPeopleByCar(String carVin) {
        return new ResponseEntity<>(registryService.getPeopleByCar(carVin), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Car> getCarByVin(String carVin) {
        return new ResponseEntity<>(carService.getById(carVin), HttpStatus.OK);
    }
}
