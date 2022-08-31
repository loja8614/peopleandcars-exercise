package training.peopleandcars.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import training.peopleandcars.model.mapper.ConverterMapper;
import training.peopleandcars.model.modelDao.CarDao;
import training.peopleandcars.model.modelDao.PeopleDao;
import training.peopleandcars.model.modelDao.RegistryDao;
import training.peopleandcars.model.modelapi.Car;
import training.peopleandcars.model.modelapi.People;
import training.peopleandcars.model.modelapi.Registry;
import training.peopleandcars.repository.RegistryRepository;
import training.peopleandcars.util.CarDataTest;
import training.peopleandcars.util.PeopleDataTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RegistryServiceTest {

    @Test
    void givenRegistry_whenSave_thenReturnRegistrySaved() {
        //given:
        RegistryRepository registryRepository = Mockito.mock(RegistryRepository.class);
        ConverterMapper converterMapper = Mockito.mock(ConverterMapper.class);
        RegistryServiceImpl registryService = new RegistryServiceImpl(registryRepository, converterMapper);

        Car carMocked = CarDataTest.getMockCar();
        People peopleMocked = PeopleDataTest.getPeopleMocked();
        Registry registryMocked = new Registry();
        registryMocked.setId(1);
        registryMocked.setPeople(peopleMocked);
        registryMocked.setCar(carMocked);

        CarDao carDaoMocked = CarDataTest.getMockCarDao();
        PeopleDao peopleDaoMocked = PeopleDataTest.getPeopleDaoMocked();

        RegistryDao registryMockedDao = new RegistryDao();
        registryMockedDao.setId(1);
        registryMockedDao.setPeople(peopleDaoMocked);
        registryMockedDao.setCar(carDaoMocked);

        Mockito.when(converterMapper.toRegistry(registryMockedDao)).thenReturn(registryMocked);
        Mockito.when(converterMapper.toRegistryDao(registryMocked)).thenReturn(registryMockedDao);
        Mockito.when(registryRepository.save(registryMockedDao)).thenReturn(registryMockedDao);

        //when
        Registry registrySaved = registryService.save(registryMocked);
        //then
        assertEquals(1, registrySaved.getId());

    }

    @Test
    void givenPeopleId_whenGetCars_thenGetTwoCars() {
        //given:
        RegistryRepository registryRepository = Mockito.mock(RegistryRepository.class);
        ConverterMapper converterMapper = Mockito.mock(ConverterMapper.class);
        RegistryServiceImpl registryService = new RegistryServiceImpl(registryRepository, converterMapper);

        UUID idPeople = UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce");
        List<RegistryDao> lstRegistryMocked = new ArrayList<>();
        PeopleDao peopleDaoMocked = PeopleDataTest.getPeopleDaoMocked();
        List<CarDao> lstCarMocked = CarDataTest.getAllCarsDaoMocked();
        int i = 1;
        for (CarDao car : lstCarMocked
        ) {
            RegistryDao registryMock = new RegistryDao(i, peopleDaoMocked, car);
            lstRegistryMocked.add(registryMock);
            i++;
        }

        Mockito.when(converterMapper.toCar(lstCarMocked.get(0))).thenReturn(CarDataTest.getMockCar());
        Mockito.when(registryRepository.findByPeopleId(idPeople)).thenReturn(lstRegistryMocked);

        //when:
        List<Car> lstCars = registryService.getCarsByPeople(idPeople);

        //then:
        assertEquals(3, lstCars.size());

    }

    @Test
    void givenCarVin_whenGetPeopleByCar_thenPeopleRegistry() {
        //given:
        RegistryRepository registryRepository = Mockito.mock(RegistryRepository.class);
        ConverterMapper converterMapper = Mockito.mock(ConverterMapper.class);
        RegistryServiceImpl registryService = new RegistryServiceImpl(registryRepository, converterMapper);

        String vin = "VINID";
        List<RegistryDao> lstRegistryMocked = new ArrayList<>();
        List<PeopleDao> lstPeopleMocked = new ArrayList<>();
        lstPeopleMocked.add(PeopleDataTest.getPeopleDaoMocked());
        CarDao carMocked = CarDataTest.getMockCarDao();
        int i = 1;
        for (PeopleDao peopleDao : lstPeopleMocked
        ) {
            RegistryDao registryMock = new RegistryDao(i, peopleDao, carMocked);
            lstRegistryMocked.add(registryMock);
            i++;
        }
        Mockito.when(converterMapper.toPeople(lstRegistryMocked.get(0).getPeople())).thenReturn(PeopleDataTest.getPeopleMocked());
        Mockito.when(registryRepository.findByCarVin(vin)).thenReturn(lstRegistryMocked);

        //when:
        List<People> lstPeopleByCar = registryService.getPeopleByCar(vin);

        //then:
        assertEquals(1, lstPeopleByCar.size());

    }
}