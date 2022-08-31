package training.peopleandcars.controller;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import training.peopleandcars.exception.ModelNotFoundException;
import training.peopleandcars.model.modelapi.Car;
import training.peopleandcars.model.modelapi.People;
import training.peopleandcars.services.PeopleService;
import training.peopleandcars.services.RegistryService;
import training.peopleandcars.util.MapperJson;
import training.peopleandcars.util.PeopleDataTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(PeopleApiController.class)
class PeopleControllerTest {
    @Autowired
    private
    MockMvc mockMvc;

    @MockBean
    private
    PeopleService peopleService;

    @MockBean
    private
    RegistryService registryService;

    @MockBean
    CarApiController carApiController;

    @Test
    void givenPeople_whenCreatePeople_thenPeopleCreated() throws Exception {
        // given:
        People peopleMocked = PeopleDataTest.getPeopleMocked();
        String inputJson = MapperJson.mapToJson(peopleMocked);

        String URI= "http://localhost:8080/api/people";
        Mockito.when(peopleService.save(peopleMocked)).thenReturn(peopleMocked);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);

        //when:
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputJson =result.getResponse().getContentAsString();

        //then:
        assertEquals(outputJson,inputJson);
        assertEquals(HttpStatus.OK.value(),response.getStatus());

    }

    @Test
    void givenCars_whenGetAllCars_thenGetCars() throws Exception{
        // given:
        List<People> lstPeopleMocked = PeopleDataTest.getLstPeopleMocked();
        String inputJson = MapperJson.mapToJson(lstPeopleMocked);

        String URI= "http://localhost:8080/api/people";
        Mockito.when(peopleService.getAll()).thenReturn(lstPeopleMocked);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);

        // when:
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputJson =result.getResponse().getContentAsString();

        //then:
        assertEquals(outputJson,inputJson);
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }

    @Test
    void givenId_whenGetPeopleById_thenGetPeople() throws Exception{

        // given:
        UUID id = UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce");
        People peopleMocked =PeopleDataTest.getPeopleMocked();
        String inputJson = MapperJson.mapToJson(peopleMocked);

        String URI= "http://localhost:8080/api/people/32611be5-33f6-4e5c-996d-9ad88bcb2bce";
        Mockito.when(peopleService.getById(id)).thenReturn(peopleMocked);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);

        // when:
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputJson =result.getResponse().getContentAsString();


        //then:
        assertEquals(outputJson,inputJson);
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }

    @Test
    void givenIdNotExists_whenGetPeopleById_thenNotFound() throws Exception{

        // given:
        UUID id = UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce");

        Map<String,String> errorPayLoad = new HashMap<>();
        errorPayLoad.put("messageError","Person not found");
        String inputJson = MapperJson.mapToJson(errorPayLoad);

        String URI= "http://localhost:8080/api/people/32611be5-33f6-4e5c-996d-9ad88bcb2bce";
        Mockito.when(peopleService.getById(id)).thenThrow(new ModelNotFoundException("Person not found"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);

        // when:
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputJson =result.getResponse().getContentAsString();


        //then:
        assertEquals(outputJson,inputJson);
        assertEquals(HttpStatus.NOT_FOUND.value(),response.getStatus());
    }

    @Test
    void givenPeople_whenDelete_thenReturnOkStatus() throws Exception {

        // given:
        String URI= "http://localhost:8080/api/people/32611be5-33f6-4e5c-996d-9ad88bcb2bce";
        Mockito.doNothing().when(peopleService).delete(UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce"));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        // when:
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        //then:
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }
    @Test
    void givenPeopleNotExits_whenDelete_thenReturnNotFound() throws Exception {

        // given:
        Map<String,String> errorPayLoad = new HashMap<>();
        errorPayLoad.put("messageError","Person not found");
        String inputJson = MapperJson.mapToJson(errorPayLoad);

        String URI= "http://localhost:8080/api/people/32611be5-33f6-4e5c-996d-9ad88bcb2bce";
        Mockito.doThrow(new ModelNotFoundException("Person not found")).when(peopleService).delete(UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce"));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(URI)
                .accept(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);

        // when:
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputJson =result.getResponse().getContentAsString();
        //then:
        assertEquals(outputJson,inputJson);
        assertEquals(HttpStatus.NOT_FOUND.value(),response.getStatus());
    }

    @Test
    void givenPeopleAssigned_whenDelete_thenReturnPeopleAssignedNotFound() throws Exception {

        // given:
        Map<String,String> errorPayLoad = new HashMap<>();
        errorPayLoad.put("messageError","The person is assigned, it cannot be deleted");
        String inputJson = MapperJson.mapToJson(errorPayLoad);

        String URI= "http://localhost:8080/api/people/32611be5-33f6-4e5c-996d-9ad88bcb2bce";
        Mockito.doThrow(new ModelNotFoundException("The person is assigned, it cannot be deleted")).when(peopleService).delete(UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce"));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(URI)
                .accept(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);

        // when:
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputJson =result.getResponse().getContentAsString();
        //then:
        assertEquals(outputJson,inputJson);
        assertEquals(HttpStatus.NOT_FOUND.value(),response.getStatus());
    }

    @Test
    void givenPeopleId_whenGetCars_thenReturnCars() throws Exception {

        // given:
        Car carMocked = new Car("VINID01", "Mercedes", "SUV", 2020, "white");
        List<Car> lstCarMocked = new ArrayList<>();
        lstCarMocked.add(carMocked);

        String inputJson = MapperJson.mapToJson(lstCarMocked);

        String URI= "http://localhost:8080/api/people/94e41df8-352c-4cc3-b166-1afbe7c251d5/cars";
        Mockito.when(registryService.getCarsByPeople(UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce"))).thenReturn(lstCarMocked);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);

        // when:
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputJson =result.getResponse().getContentAsString();

        //then:
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }
}