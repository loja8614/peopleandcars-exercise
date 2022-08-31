package training.peopleandcars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training.peopleandcars.model.modelDao.CarDao;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<CarDao,String> {
    CarDao save(CarDao car);
    List<CarDao> findAll();
    void deleteById(String vin);
    Optional<CarDao> findById(String vin);
}
