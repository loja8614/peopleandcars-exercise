package training.peopleandcars.model.modelDao;
import javax.persistence.*;

@Entity
@Table (schema = "registry", name ="rel_registry")
public class RegistryDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Integer id;


    @ManyToOne
    @JoinColumn(name = "people_id")
    PeopleDao people;

    @ManyToOne
    @JoinColumn(name = "car_vin")
    CarDao car;

    public RegistryDao() {
    }

    public RegistryDao(Integer id) {
        this.id = id;
    }

    public RegistryDao(Integer id, PeopleDao people, CarDao car) {
        this.id = id;
        this.people = people;
        this.car = car;
    }
    public RegistryDao(PeopleDao people, CarDao car) {
        this.people = people;
        this.car = car;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PeopleDao getPeople() {
        return people;
    }

    public void setPeople(PeopleDao people) {
        this.people = people;
    }

    public CarDao getCar() {
        return car;
    }

    public void setCar(CarDao car) {
        this.car = car;
    }
}
