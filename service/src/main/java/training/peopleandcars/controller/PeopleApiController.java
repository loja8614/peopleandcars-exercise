package training.peopleandcars.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import training.peopleandcars.model.modelapi.Car;
import training.peopleandcars.model.modelapi.People;

import java.util.UUID;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import training.peopleandcars.services.PeopleService;
import training.peopleandcars.services.RegistryService;

import javax.validation.Valid;

import java.util.List;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-08-22T19:22:29.543238700-05:00[America/Mexico_City]")
@Controller
@RestControllerAdvice
@RequestMapping("${openapi.peopleAndCars.base-path:/api}")
public class PeopleApiController implements PeopleApi {
    private final NativeWebRequest request;

    private PeopleService peopleService;
    private RegistryService registryService;

    public PeopleApiController(PeopleService peopleService,RegistryService registryService,NativeWebRequest request) {
        this.peopleService = peopleService;
        this.registryService=registryService;
        this.request = request;
    }

    @Override
    public ResponseEntity<People> addUpdatePeople(@Valid People people) {
        return new ResponseEntity(peopleService.save(people), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<People> deletePeople(UUID peopleId) {
        peopleService.delete(peopleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<People>> getAllPeople() {
        return new ResponseEntity(peopleService.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Car>> getCarsByPeople(UUID peopleId) {
        return  new ResponseEntity(registryService.getCarsByPeople(peopleId),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<People> getPeopleById(UUID peopleId) {
        return new ResponseEntity<>(peopleService.getById(peopleId), HttpStatus.OK);
    }
}
