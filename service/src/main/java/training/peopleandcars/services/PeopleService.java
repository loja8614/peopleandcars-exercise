package training.peopleandcars.services;

import training.peopleandcars.model.modelapi.People;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PeopleService {
    People save(People people);
    List<People> getAll();
    void delete (UUID id);
    People getById(UUID id);
}
