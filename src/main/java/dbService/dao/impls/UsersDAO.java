package dbService.dao.impls;


import dbService.datasets.Pet;
import dbService.datasets.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.Set;

public class UsersDAO {
    private Session session;

    public UsersDAO(Session session){this.session = session;}

    public User get(String email) throws HibernateException {
        Criteria criteria = session.createCriteria(User.class);
        return (User) criteria.add(Restrictions.eq("email", email)).uniqueResult();
    }

    public long insert(String name, String email, String pass, String country, String location) throws HibernateException {
        return (Long) session.save(new User(name, email, pass, country, location));
    }

    public long getId(String email) throws HibernateException{
        Criteria criteria = session.createCriteria(User.class);
        return ((User) criteria.add(Restrictions.eq("email", email)).uniqueResult()).getId();
    }

    public Set<Pet> getPets(User user){
        return user.getPets();
    }
}
