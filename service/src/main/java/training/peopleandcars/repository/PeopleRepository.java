package training.peopleandcars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training.peopleandcars.modelapi.People;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PeopleRepository extends JpaRepository<People, UUID> {
    People save(People people);
    List<People> findAll();
    void deleteById(UUID id);
    Optional<People> findById(UUID uuid);
}
