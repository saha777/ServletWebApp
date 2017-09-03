package dbService.dao.impls;

import dbService.datasets.Pet;
import dbService.datasets.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.Alias;

import java.util.List;

public class PetsDAO {
    private Session session;

    public PetsDAO(Session session){this.session = session;}

    public Pet get(long id) throws HibernateException {
        Criteria criteria = session.createCriteria(Pet.class);
        return (Pet) criteria.add(Restrictions.eq("id", id)).uniqueResult();
    }

    public long insert(User user, String name, String animalClass, int age, String date) throws HibernateException {
        return (Long) session.save(new Pet(user, name, animalClass, age, date));
    }

    public List<Pet> getAllPets(){
        Criteria criteria = session.createCriteria(Pet.class);
        List<Pet> list = criteria.list();
        return list;
    }

    public List<Pet> getUserPets(User user){
        List<Pet> pets = session.createQuery("from Pet a where a.user = " + user.getId() + " ").list();
        return pets;
    }

    public long getId(Pet pet) throws HibernateException{
        return pet.getId();
    }
}
