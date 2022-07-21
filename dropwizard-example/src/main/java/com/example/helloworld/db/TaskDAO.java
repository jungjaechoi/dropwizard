package com.example.helloworld.db;

import com.example.helloworld.core.Person;
import com.example.helloworld.core.Task;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class TaskDAO extends AbstractDAO<Task> {

    public TaskDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Task create(Task task) {
        return persist(task);
    }

//    public List<Task> findByUserId(HttpServletRequest request) {
//
//        String userId = request.getParameter("name");
////        String sql = String.format("select t from Task t where t.userId = %s",userId);
//
//        try{
//            Query query = query("select t from Task t where t.userId = :userId");
//            query.setParameter("userId",userId);
//            List<Task> result = list(query);
//            System.out.println("result:" + result);
//            return result;
//        }
//        catch(Exception e){
//            System.out.println(e);
//            return null;
//        }
//    }

    public String deleteById(long id){
        Optional<Task> task = findById(id);
        if(task.isPresent()){
            currentSession().delete(task.get());
            return "success";
        }
        else{
            return "fail";
        }
    }

    public List<Task> findAll() {
        return list(namedTypedQuery("com.example.helloworld.core.Task.findAll"));
    }
}
