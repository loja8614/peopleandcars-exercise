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
import training.peopleandcars.model.modelapi.Registry;
import training.peopleandcars.services.RegistryService;
import training.peopleandcars.util.CarDataTest;
import training.peopleandcars.util.MapperJson;
import training.peopleandcars.util.PeopleDataTest;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(RegistryApiController.class)
class RegistryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RegistryService registryService;

    @MockBean
    CarApiController carApiController;

    @MockBean
    PeopleApiController peopleApiController;


    @Test
    void givenNewCarAndNewPeople_whenCreateRelation_thenReturnRegistry() throws Exception {
        //given:
        Car car = CarDataTest.getMockCar();
        People people= PeopleDataTest.getPeopleMocked();
        Registry registryMock = new Registry(1,people,car);
        String inputJson = MapperJson.mapToJson(registryMock);

        String URI= "http://localhost:8080/api/registry";
        Mockito.when(registryService.save(registryMock)).thenReturn(registryMock);
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
        assertEquals(HttpStatus.CREATED.value(),response.getStatus());
    }

}