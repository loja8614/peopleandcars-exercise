package training.peopleandcars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training.peopleandcars.model.modelDao.PeopleDao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PeopleRepository extends JpaRepository<PeopleDao, UUID> {
    PeopleDao save(PeopleDao people);
    List<PeopleDao> findAll();
    void deleteById(UUID id);
    Optional<PeopleDao> findById(UUID uuid);
}
