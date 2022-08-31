package training.peopleandcars.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import training.peopleandcars.model.modelDao.RegistryDao;

import java.util.List;
import java.util.UUID;

@Repository
public interface RegistryRepository extends CrudRepository<RegistryDao,Integer> {
    RegistryDao save(RegistryDao registry);

    @Query("SELECT r FROM RegistryDao r WHERE r.people.id = :peopleId")
    List<RegistryDao> findByPeopleId(@Param("peopleId") UUID peopleId);

    @Query("SELECT r FROM RegistryDao r WHERE r.car.vin = :carVin")
    List<RegistryDao> findByCarVin(@Param("carVin") String vin);

    @Query("SELECT r FROM RegistryDao r WHERE r.car.vin = :carVin and r.people.id = :peopleId")
    List<RegistryDao> findByPeopleIdCarVin(@Param("carVin") String vin,
                                        @Param("peopleId") UUID peopleId);
}
