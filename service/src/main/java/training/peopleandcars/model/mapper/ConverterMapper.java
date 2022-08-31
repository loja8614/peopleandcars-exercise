package training.peopleandcars.model.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training.peopleandcars.model.modelDao.CarDao;
import training.peopleandcars.model.modelDao.PeopleDao;
import training.peopleandcars.model.modelDao.RegistryDao;
import training.peopleandcars.model.modelapi.Car;
import training.peopleandcars.model.modelapi.People;
import training.peopleandcars.model.modelapi.Registry;

@Service
public class ConverterMapper {
    private ModelMapper modelMapper = new ModelMapper();
    public CarDao toCarDao(Car car){
        return modelMapper.map(car,CarDao.class);
    }
    public Car toCar(CarDao carDao){
        return modelMapper.map(carDao,Car.class);
    }
    public PeopleDao toPeopleDao(People people){
        return modelMapper.map(people, PeopleDao.class);
    }
    public People toPeople(PeopleDao peopleDao){
        return modelMapper.map(peopleDao, People.class);
    }
    public RegistryDao toRegistryDao(Registry registry){
        return modelMapper.map(registry,RegistryDao.class);
    }
    public Registry toRegistry(RegistryDao registryDao){
        return modelMapper.map(registryDao,Registry.class);
    }



}
