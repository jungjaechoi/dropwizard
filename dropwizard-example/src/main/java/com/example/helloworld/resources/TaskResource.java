package com.example.helloworld.resources;

import com.example.helloworld.core.Task;
import com.example.helloworld.core.User;
import com.example.helloworld.core.UserTest;
import com.example.helloworld.db.TaskDAO;
import com.example.helloworld.db.UserDAO;
import com.example.helloworld.etcClass.TaskUpdate;
import com.google.gson.Gson;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {

    private final TaskDAO taskDAO;
    private final UserDAO userDAO;

    public TaskResource(TaskDAO taskDAO, UserDAO userDAO) {
        this.taskDAO = taskDAO;
        this.userDAO = userDAO;
    }

    @POST
    @UnitOfWork
    public Response createTask(@Valid Task taskParam) {
        System.out.println("-----------------------------");
        System.out.println("@POST /task start");
        if(taskParam.getUserId() == null || taskParam.getUserEmail() == null || taskParam.getTitle() == null || taskParam.getContent()==null){
            Task task = new Task();
            System.out.println("return STATUS_CODE 400");
            System.out.println("-----------------------------");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else {
            if(taskParam.getState()==null){
                taskParam.setState("0");
            }
            if(taskParam.getAccess()==null){
                taskParam.setAccess("0");
            }
            if(taskParam.getPriority()==null){
                taskParam.setPriority("0");
            }

            LocalDateTime now = LocalDateTime.now();
            String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            taskParam.setCreate_date(formatedNow);
            taskParam.setEdit_date(formatedNow);

            try{
                Task task = taskDAO.create(taskParam);
                System.out.println("return STATUS_CODE 200");
                System.out.println("-----------------------------");
                return Response.ok(task).build();
            } catch (Exception e){
                System.out.println("error: "+ e);
                System.out.println("return STATUS_CODE 500");
                System.out.println("-----------------------------");
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @GET
    @UnitOfWork
    public Response getTask(){

        try{
            System.out.println("-----------------------------");
            System.out.println("@GET /task start");
            List<Task> task = taskDAO.findAll();

            String tasks = new Gson().toJson(task);

            System.out.println("return STATUS_CODE 200");
            System.out.println("-----------------------------");
            return Response.ok(tasks).build();

        } catch(Exception e){

            System.out.println("error: "+ e);
            System.out.println("return STATUS_CODE 500");
            System.out.println("-----------------------------");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PATCH
    @UnitOfWork
    public Response updateTask(@Valid TaskUpdate taskUpdate){

        System.out.println("-----------------------------");
        System.out.println("@PATCH /task start");

        System.out.println("task id 값: "+ taskUpdate.getId());
        System.out.println("task userId 값: "+ taskUpdate.getUserId());
        System.out.println("task access 값: "+ taskUpdate.getAccess());

        if(taskUpdate.getId() == null || taskUpdate.getUserId() == null || taskUpdate.getAccess() == null
            || taskUpdate.getContent() == null || taskUpdate.getPriority() == null
            || taskUpdate.getState() == null || taskUpdate.getTitle() == null){

            System.out.println("return STATUS_CODE 400");
            System.out.println("-----------------------------");

            return Response.status(Response.Status.BAD_REQUEST).build();

        }

        Optional<Task> oldTask = taskDAO.findById(taskUpdate.getId());

        if(oldTask.isPresent()){
            oldTask.get().setContent(taskUpdate.getContent());
            oldTask.get().setTitle(taskUpdate.getTitle());
            oldTask.get().setState(taskUpdate.getState());
            oldTask.get().setPriority(taskUpdate.getPriority());
            oldTask.get().setAccess(taskUpdate.getAccess());
            LocalDateTime now = LocalDateTime.now();
            String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            oldTask.get().setEdit_date(formatedNow);

            try{

                Task task = taskDAO.create(oldTask.get());
                System.out.println("return STATUS_CODE 200");
                System.out.println("-----------------------------");

                return Response.ok(task).build();

            }catch(Exception e){

                System.out.println("return STATUS_CODE 500");
                System.out.println("-----------------------------");
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

            }
        }
        else{
            System.out.println("return STATUS_CODE 404");
            System.out.println("-----------------------------");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @UnitOfWork
    public Response deleteTask(@QueryParam("id") Long id){

        System.out.println("-----------------------------");
        System.out.println("@DELETE /task start");

        if(id == null){
            System.out.println("return STATUS_CODE 400");
            System.out.println("-----------------------------");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try{

            String result = taskDAO.deleteById(id);
            System.out.println("return STATUS_CODE 204");
            System.out.println("-----------------------------");
            return Response.status(Response.Status.NO_CONTENT).build();

        }catch(Exception e){
            System.out.println("return STATUS_CODE 400");
            System.out.println("-----------------------------");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
