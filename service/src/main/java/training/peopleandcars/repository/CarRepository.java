package training.peopleandcars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training.peopleandcars.modelapi.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,String> {
    Car save(Car car);
    List<Car> findAll();
    void deleteById(String vin);
    Optional<Car> findById(String vin);
}
