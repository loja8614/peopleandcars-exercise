package training.peopleandcars.util;



import training.peopleandcars.model.modelDao.PeopleDao;
import training.peopleandcars.model.modelapi.People;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PeopleDataTest {
    public static People getPeopleMocked() {
        People peopleMocked = new People();
        peopleMocked.setId(UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce"));
        peopleMocked.setFirstname("Pedro");
        peopleMocked.setLastname("Lopez");
        peopleMocked.setEmail("pedro@email.com");
        peopleMocked.setGender("Male");
        return peopleMocked;
    }
    public static PeopleDao getPeopleDaoMocked() {
        PeopleDao peopleMocked = new PeopleDao();
        peopleMocked.setId(UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce"));
        peopleMocked.setFirstname("Pedro");
        peopleMocked.setLastname("Lopez");
        peopleMocked.setEmail("pedro@email.com");
        peopleMocked.setGender("Male");
        return peopleMocked;
    }

    public static People getPeopleUpdatedMocked() {
        People peopleMocked = new People();
        peopleMocked.setId(UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce"));
        peopleMocked.setFirstname("Pedro");
        peopleMocked.setLastname("Ramirez");
        peopleMocked.setEmail("pedroRam@email.com");
        peopleMocked.setGender("Male");
        return peopleMocked;
    }

    public static List<People> getLstPeopleMocked() {
        List<People> lstPeopleMocked = new ArrayList<>();
        People peopleMocked = new People();
        peopleMocked.setId( UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce"));
        peopleMocked.setFirstname("Pedro");
        peopleMocked.setLastname("Lopez");
        peopleMocked.setGender("pedro@email.com");
        peopleMocked.setEmail("Male");

        lstPeopleMocked.add(peopleMocked);
        peopleMocked = new People();
        peopleMocked.setId( UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bdd"));
        peopleMocked.setFirstname("Juan");
        peopleMocked.setLastname("Perez");
        peopleMocked.setGender("juan@email.com");
        peopleMocked.setEmail("Male");
        lstPeopleMocked.add(peopleMocked);
        peopleMocked = new People();
        peopleMocked.setId(UUID.fromString("33611be5-33f6-4e5c-996d-9ad88bcb2bdd"));
        peopleMocked.setFirstname("Maria");
        peopleMocked.setLastname("Vazquez");
        peopleMocked.setGender("mary@email.com");
        peopleMocked.setEmail("Female");
        lstPeopleMocked.add(peopleMocked);
        return lstPeopleMocked;
    }
    public static List<PeopleDao> getLstPeopleDaoMocked() {
        List<PeopleDao> lstPeopleDaoMocked = new ArrayList<>();
        PeopleDao PeopleDaoMocked = new PeopleDao();
        PeopleDaoMocked.setId( UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bce"));
        PeopleDaoMocked.setFirstname("Pedro");
        PeopleDaoMocked.setLastname("Lopez");
        PeopleDaoMocked.setGender("pedro@email.com");
        PeopleDaoMocked.setEmail("Male");

        lstPeopleDaoMocked.add(PeopleDaoMocked);
        PeopleDaoMocked = new PeopleDao();
        PeopleDaoMocked.setId( UUID.fromString("32611be5-33f6-4e5c-996d-9ad88bcb2bdd"));
        PeopleDaoMocked.setFirstname("Juan");
        PeopleDaoMocked.setLastname("Perez");
        PeopleDaoMocked.setGender("juan@email.com");
        PeopleDaoMocked.setEmail("Male");
        lstPeopleDaoMocked.add(PeopleDaoMocked);
        PeopleDaoMocked = new PeopleDao();
        PeopleDaoMocked.setId(UUID.fromString("33611be5-33f6-4e5c-996d-9ad88bcb2bdd"));
        PeopleDaoMocked.setFirstname("Maria");
        PeopleDaoMocked.setLastname("Vazquez");
        PeopleDaoMocked.setGender("mary@email.com");
        PeopleDaoMocked.setEmail("Female");
        lstPeopleDaoMocked.add(PeopleDaoMocked);
        return lstPeopleDaoMocked;
    }


}
