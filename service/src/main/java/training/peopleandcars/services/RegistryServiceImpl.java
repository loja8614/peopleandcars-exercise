package training.peopleandcars.services;

import training.peopleandcars.model.mapper.ConverterMapper;
import training.peopleandcars.model.modelDao.RegistryDao;
import training.peopleandcars.model.modelapi.Car;
import training.peopleandcars.model.modelapi.People;
import training.peopleandcars.model.modelapi.Registry;
import training.peopleandcars.repository.RegistryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RegistryServiceImpl implements RegistryService {

    private RegistryRepository registryRepository;
    private ConverterMapper converterMapper;

    public RegistryServiceImpl(RegistryRepository registryRepository,ConverterMapper converterMapper) {
        this.registryRepository = registryRepository;
        this.converterMapper=converterMapper;
    }

    public Registry save(Registry newRegistry) {
        Registry RegistrySaved = new Registry();

        if (registryRepository.findByPeopleIdCarVin(newRegistry.getCar().getVin(), newRegistry.getPeople().getId()).size() == 0
                && registryRepository.findByCarVin(newRegistry.getCar().getVin()).size() == 0) {
            RegistrySaved = converterMapper.toRegistry(registryRepository.save(converterMapper.toRegistryDao(newRegistry)));
        }
        return RegistrySaved;
    }


    public List<Car> getCarsByPeople(UUID peopleId) {
        List<RegistryDao> lstRegistryDao = registryRepository.findByPeopleId(peopleId);
        List<Car> lstCarsByPeople = new ArrayList<>();
        for (RegistryDao r : lstRegistryDao) {
            lstCarsByPeople.add(converterMapper.toCar(r.getCar()));
        }
        return lstCarsByPeople;
    }
    public List<People> getPeopleByCar(String vin) {
        List<RegistryDao> lstRegistryDao = registryRepository.findByCarVin(vin);
        List<People> lstPeopleByCar = new ArrayList<>();
        for (RegistryDao r : lstRegistryDao)
            lstPeopleByCar.add(converterMapper.toPeople(r.getPeople()));
        return lstPeopleByCar;
    }


}
