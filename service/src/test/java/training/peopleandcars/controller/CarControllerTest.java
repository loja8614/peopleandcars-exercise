package training.peopleandcars.controller;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import training.peopleandcars.model.modelapi.Car;
import training.peopleandcars.model.modelapi.People;
import training.peopleandcars.services.CarService;
import training.peopleandcars.services.RegistryService;
import training.peopleandcars.util.CarDataTest;
import training.peopleandcars.util.MapperJson;
import training.peopleandcars.util.PeopleDataTest;


import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CarApiController.class)
class CarControllerTest {

    @Autowired
    private
    MockMvc mockMvc;

    @MockBean
    private
    CarService carService;

    @MockBean
    private
    RegistryService registryService;

    @MockBean
    PeopleApiController peopleApiController;


    @Test
    void givenCar_whenCreateCar_thenCarCreated() throws Exception {
        // given:
        Car carMocked = CarDataTest.getMockCar();
        String inputJson = MapperJson.mapToJson(carMocked);
        String URI = "http://localhost:8080/api/car";
        Mockito.when(carService.save(carMocked)).thenReturn(carMocked);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);

        // when:
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputJson = result.getResponse().getContentAsString();

        //then:
        assertEquals(outputJson, inputJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    @Test
    void givenCars_whenGetAllCars_thenSize3() throws Exception {
        // given:
        List<Car> carsMocked = CarDataTest.getAllCarsMocked();
        String inputJson = MapperJson.mapToJson(carsMocked);
        String URI = "http://localhost:8080/api/car";
        Mockito.when(carService.getAll()).thenReturn(carsMocked);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);

        // when:
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputJson = result.getResponse().getContentAsString();

        //then:
        assertEquals(outputJson, inputJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    @Test
    void givenVin_whenGetCarById_thenGetCar() throws Exception {

        // given:
        Car carMocked = CarDataTest.getMockCar();
        String inputJson = MapperJson.mapToJson(carMocked);
        String URI = "http://localhost:8080/api/car/VINID";
        Mockito.when(carService.getById("VINID")).thenReturn(carMocked);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);

        // when:
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputJson = result.getResponse().getContentAsString();

        //then:
        assertEquals(outputJson, inputJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void givenCar_whenDelete_thenReturnCodeResponse200() throws Exception {

        // given:
        String URI = "http://localhost:8080/api/car/VINID";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        // when:
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        //then:
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void givenCarVin_whenGetPeople_thenReturnPerson1() throws Exception {

        // given:
        People peopleMocked = PeopleDataTest.getPeopleMocked();
        List<People> lstPeopleMocked = new ArrayList<>();
        lstPeopleMocked.add(peopleMocked);
        String inputJson = MapperJson.mapToJson(lstPeopleMocked);

        String URI = "http://localhost:8080/api/car/VINID/people";
        Mockito.when(registryService.getPeopleByCar("VINID")).thenReturn(lstPeopleMocked);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);

        // when:
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        //then:
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}