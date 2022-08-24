/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.0.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package training.peopleandcars.controller;

import training.peopleandcars.modelapi.Registry;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-08-22T19:22:29.543238700-05:00[America/Mexico_City]")
@Validated
public interface RegistryApi {

    /**
     * POST /registry
     * add registry car to people
     *
     * @param registry Create registry (optional)
     * @return Successful request (status code 200)
     *         or Invalid input (status code 405)
     */
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/registry",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    ResponseEntity<Registry> createRegistryCarPeople(
         @Valid @RequestBody(required = false) Registry registry
    );

}