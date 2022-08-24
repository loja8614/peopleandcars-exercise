package training.peopleandcars.controller;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import training.peopleandcars.modelapi.Car;
import training.peopleandcars.modelapi.People;
import training.peopleandcars.modelapi.Registry;
import training.peopleandcars.services.RegistryService;
import training.peopleandcars.util.CarDataTest;
import training.peopleandcars.util.PeopleDataTest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class RegistryControllerTest {
    @InjectMocks
    RegistryApiController registryController;

    @Mock
    RegistryService registryService;


    @Test
    void givenNewCarAndNewPeople_whenCreateRelation_thenReturnRegistry(){
        //given:
        Car car = CarDataTest.getMockCar();
        People people= PeopleDataTest.getPeopleMocked();
        Registry registryMock = new Registry(1,people,car);
        Mockito.when(registryService.save(registryMock)).thenReturn(registryMock);
        //when:
        ResponseEntity<Registry> registrySaved = registryController.createRegistryCarPeople(registryMock);
        //then:
        assertEquals(1,registrySaved.getBody().getId());
        assertEquals(201,registrySaved.getStatusCodeValue());
    }

}