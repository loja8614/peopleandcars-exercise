package training.peopleandcars.services;

import training.peopleandcars.exception.ModelNotFoundException;
import training.peopleandcars.model.mapper.ConverterMapper;
import training.peopleandcars.model.modelDao.PeopleDao;
import training.peopleandcars.model.modelapi.People;
import training.peopleandcars.repository.PeopleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PeopleServiceImpl implements PeopleService {
    private PeopleRepository personRepository;
    private RegistryService registryService;
    private ConverterMapper converterMapper;

    public PeopleServiceImpl(PeopleRepository personRepository, RegistryService registryService, ConverterMapper converterMapper) {
        this.personRepository = personRepository;
        this.registryService = registryService;
        this.converterMapper = converterMapper;
    }

    public People save(People people) {
        PeopleDao peopleDao=  personRepository.save(converterMapper.toPeopleDao(people));
        return converterMapper.toPeople(peopleDao);
    }

    public List<People> getAll() {
        List<People> lstPeople = new ArrayList<>();
        List<PeopleDao> lstPeopleDao;
        lstPeopleDao = personRepository.findAll();
        for (PeopleDao peopleDao : lstPeopleDao
        ) {
            lstPeople.add(converterMapper.toPeople(peopleDao));
        }

        return lstPeople;
    }

    public void delete(UUID id) {
        People people = getById(id);
        if (registryService.getCarsByPeople(id).size() != 0) {
            throw new ModelNotFoundException("The person is assigned, it cannot be deleted");
        }
        personRepository.deleteById(id);
    }

    public People getById(UUID id) {
        Optional<PeopleDao> peopleDao = personRepository.findById(id);
        if (peopleDao == null)
            throw new ModelNotFoundException("Person not found");
        return converterMapper.toPeople(peopleDao.get());
    }

}
