package training.peopleandcars.controller;

import org.springframework.http.HttpStatus;
import training.peopleandcars.modelapi.Registry;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import training.peopleandcars.services.RegistryService;


import javax.validation.Valid;

import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-08-22T19:22:29.543238700-05:00[America/Mexico_City]")
@Controller
@RequestMapping("${openapi.peopleAndCars.base-path:/api}")
public class RegistryApiController implements RegistryApi {

    private final NativeWebRequest request;
    private RegistryService registryService;

    public RegistryApiController(NativeWebRequest request, RegistryService registryService) {
        this.registryService = registryService;
        this.request = request;
    }


    @Override
    public ResponseEntity<Registry> createRegistryCarPeople(@Valid Registry registry) {
        return new ResponseEntity(registryService.save(registry), HttpStatus.CREATED);
    }
}
