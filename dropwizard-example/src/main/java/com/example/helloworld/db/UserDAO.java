package com.example.helloworld.db;

import com.example.helloworld.core.UserTest;
import com.google.inject.Provides;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class UserDAO extends AbstractDAO<UserTest> {

    @Inject
    @Named("HibernateBundle")
    public UserDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<UserTest> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public UserTest create(UserTest user) {
        return persist(user);
    }

    public UserTest findByUserId(String userId) {

//        String sql = String.format("select u from UserTest u where u.userId = %s",userId);
        try{
            CriteriaBuilder builder = currentSession().getCriteriaBuilder();
            CriteriaQuery<UserTest> query = builder.createQuery(UserTest.class);
            Root<UserTest> root = query.from(UserTest.class);
            query.select(root).where(
                builder.equal(root.get("userId"),userId)
            );
            UserTest result = currentSession().createQuery(query).getSingleResult();
//            Query query = query("select u from UserTest u where u.userId = :userId");
//            query.setParameter("userId",userId);
//            UserTest result = uniqueResult(query);

            return result;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

//    public String findEmailById(Long id){
//
//        String sql = String.format("select u from UserTest u where u.id = %s",id);
//
//        try{
//            Query query = query(sql);
//            UserTest result = uniqueResult(query);
//
//            return result.getUserId();
//        }
//        catch(Exception e){
//            System.out.println(e);
//            return null;
//        }
//    }

    public List<UserTest> findAll() {
        return list(namedTypedQuery("com.example.helloworld.core.UserTest.findAll"));
    }
}
