package database.dao;

import database.HibernateUtil;
import database.entity.Message;
import database.entity.User;
import org.hibernate.Session;

import java.util.List;

/**
 * DAO (data access object) - один из наиболее распространенных паттернов
 * проектирования, "Доступ к данным". Его смысл прост - создать в
 * приложении слой, который отвечает только за доступ к данным, и больше
 * ни за что. Достать данные из БД, обновить данные, удалить данные - и все.
 * Однако, мы не будем создавать DAO напрямую и вызывать его методы в нашем
 * приложении. Вся логика будет помещена в класс UserService.
 *
 */
public class UserDAO {

    public void insert(User user){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();

        session.persist(user);

        session.getTransaction().commit();
    }

    public void update(User user){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();

        session.saveOrUpdate(user);

        session.getTransaction().commit();
    }

    public void delete(User user){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();

        session.delete(user);

        session.getTransaction().commit();
    }

    public User get(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();

        User user = session.get(User.class, id);

        session.getTransaction().commit();

        return user;
    }

    public List<User> get(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();

        List<User> list = (List<User>) session.createQuery("FROM User").list();

        session.getTransaction().commit();

        return list;
    }

    public User findById(int id) {
        return HibernateUtil.getSessionFactory().openSession().get(User.class, id);
    }

    public Message findAutoById(int id) {
        return HibernateUtil.getSessionFactory().openSession().get(Message.class, id);
    }
}
