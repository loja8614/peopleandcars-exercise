package training.peopleandcars.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import training.peopleandcars.exception.ModelNotFoundException;
import training.peopleandcars.model.mapper.ConverterMapper;
import training.peopleandcars.model.modelDao.PeopleDao;
import training.peopleandcars.model.modelapi.Car;
import training.peopleandcars.model.modelapi.People;
import training.peopleandcars.repository.PeopleRepository;
import training.peopleandcars.util.PeopleDataTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PeopleServiceTest {


    @Test
    void givenPeople_whenSave_thenReturnPeopleSaved() {
        // given:
        PeopleRepository peopleRepository = Mockito.mock(PeopleRepository.class);
        ConverterMapper converterMapper = Mockito.mock(ConverterMapper.class);
        RegistryService registryService = Mockito.mock(RegistryService.class);
        PeopleServiceImpl peopleService = new PeopleServiceImpl(peopleRepository, registryService, converterMapper);

        People peopleMocked = PeopleDataTest.getPeopleMocked();
        PeopleDao peopleDaoMocked = PeopleDataTest.getPeopleDaoMocked();

        Mockito.when(converterMapper.toPeople(peopleDaoMocked)).thenReturn(peopleMocked);
        Mockito.when(converterMapper.toPeopleDao(peopleMocked)).thenReturn(peopleDaoMocked);
        Mockito.when(peopleRepository.save(peopleDaoMocked)).thenReturn(peopleDaoMocked);

        // when:
        People peopleSaved = peopleService.save(PeopleDataTest.getPeopleMocked());

        //then:
        assertEquals(peopleMocked.getId(), peopleSaved.getId());
    }

    @Test
    void givenLstPeople_whenGetAll_thenReturnSize3() {
        //given:
        PeopleRepository peopleRepository = Mockito.mock(PeopleRepository.class);
        ConverterMapper converterMapper = Mockito.mock(ConverterMapper.class);
        RegistryService registryService = Mockito.mock(RegistryService.class);
        PeopleServiceImpl peopleService = new PeopleServiceImpl(peopleRepository, registryService, converterMapper);

        List<PeopleDao> lstPeopleDaoMocked = PeopleDataTest.getLstPeopleDaoMocked();
        List<People> lstPeopleMocked = PeopleDataTest.getLstPeopleMocked();
        Mockito.when(converterMapper.toPeople(lstPeopleDaoMocked.get(0))).thenReturn(lstPeopleMocked.get(0));
        Mockito.when(peopleRepository.findAll()).thenReturn(lstPeopleDaoMocked);

        // when:
        List<People> ltsPeopleSaved = peopleService.getAll();

        //then:
        assertEquals(3, ltsPeopleSaved.size());
    }

    @Test
    void givenPeople_whenDelete_thenReturnNull() {
        // given:
        PeopleRepository peopleRepository = Mockito.mock(PeopleRepository.class);
        ConverterMapper converterMapper = Mockito.mock(ConverterMapper.class);
        RegistryService registryService = Mockito.mock(RegistryService.class);
        PeopleServiceImpl peopleService = new PeopleServiceImpl(peopleRepository, registryService, converterMapper);

        People people = PeopleDataTest.getPeopleMocked();
        PeopleDao peopleDao = PeopleDataTest.getPeopleDaoMocked();

        Mockito.when(peopleRepository.findById(UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce"))).thenReturn(Optional.of(peopleDao));
        Mockito.when(converterMapper.toPeople(peopleDao)).thenReturn(people);
        Mockito.when(peopleService.getById(UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce"))).thenReturn(people);

        // when:
        peopleService.delete(people.getId());

        //then:
        Mockito.when(peopleRepository.findById(UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce"))).thenReturn(null);
        Optional<PeopleDao> peopleDaoRes = peopleRepository.findById(people.getId());
        assertNull(peopleDaoRes);
    }

    @Test
    void givenPeople_whenGetById_thenGetPeople() {
        // given:
        PeopleRepository peopleRepository = Mockito.mock(PeopleRepository.class);
        ConverterMapper converterMapper = Mockito.mock(ConverterMapper.class);
        RegistryService registryService = Mockito.mock(RegistryService.class);
        PeopleServiceImpl peopleService = new PeopleServiceImpl(peopleRepository, registryService, converterMapper);

        People peopleMocked = PeopleDataTest.getPeopleMocked();
        PeopleDao peopleDao = PeopleDataTest.getPeopleDaoMocked();

        Mockito.when(converterMapper.toPeople(peopleDao)).thenReturn(peopleMocked);
        Mockito.when(peopleRepository.findById(UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce"))).thenReturn(Optional.of(peopleDao));

        // when:
        People people = peopleService.getById(UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce"));

        //then:
        assertEquals("Pedro", people.getFirstname());
        assertEquals("Lopez", people.getLastname());
        assertEquals("pedro@email.com", people.getEmail());
        assertEquals("Male", people.getGender());
        assertEquals(UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce"), people.getId());
    }

    @Test
    void givenNotPeople_whenDeleteNotExisting_thenNotDeleteSendException() {
        // given:
        PeopleRepository peopleRepository = Mockito.mock(PeopleRepository.class);
        ConverterMapper converterMapper = Mockito.mock(ConverterMapper.class);
        RegistryService registryService = Mockito.mock(RegistryService.class);
        PeopleServiceImpl peopleService = new PeopleServiceImpl(peopleRepository, registryService, converterMapper);

        List<Car> lstCarMocked = new ArrayList<>();
        People peopleMocked = PeopleDataTest.getPeopleMocked();
        PeopleDao peopleDao = PeopleDataTest.getPeopleDaoMocked();

        Mockito.when(converterMapper.toPeople(peopleDao)).thenReturn(peopleMocked);
        Mockito.when(peopleRepository.findById(UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce"))).thenReturn(null);
        Mockito.when(registryService.getCarsByPeople(UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce"))).thenReturn(lstCarMocked);

        // when:
        ModelNotFoundException exceptionMessage = Assertions.assertThrows(ModelNotFoundException.class, () -> peopleService.delete(UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce")));

        //then:
        assertEquals("Person not found", exceptionMessage.getMessage());
    }

    @Test
    void givenPeople_whenDeleteWithRegistry_thenNotDeleteSendException() {

        // given:
        PeopleRepository peopleRepository = Mockito.mock(PeopleRepository.class);
        ConverterMapper converterMapper = Mockito.mock(ConverterMapper.class);
        RegistryService registryService = Mockito.mock(RegistryService.class);
        PeopleServiceImpl peopleService = new PeopleServiceImpl(peopleRepository, registryService, converterMapper);

        Car carMocked = new Car("VINID01", "Mercedes", "SUV", 2020, "white");
        People peopleMocked = PeopleDataTest.getPeopleMocked();
        PeopleDao peopleDao = PeopleDataTest.getPeopleDaoMocked();
        List<Car> lstCarMocked = new ArrayList<>();
        lstCarMocked.add(carMocked);

        Mockito.when(converterMapper.toPeople(peopleDao)).thenReturn(peopleMocked);
        Mockito.when(peopleRepository.findById(UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce"))).thenReturn(Optional.of(peopleDao));
        Mockito.when(registryService.getCarsByPeople(UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce"))).thenReturn(lstCarMocked);


        // when:
        ModelNotFoundException exceptionMessage = Assertions.assertThrows(ModelNotFoundException.class, () -> peopleService.delete(UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce")));

        //then:
        assertEquals("The person is assigned, it cannot be deleted", exceptionMessage.getMessage());
    }


}