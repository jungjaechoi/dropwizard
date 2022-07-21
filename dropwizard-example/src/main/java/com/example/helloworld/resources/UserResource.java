package com.example.helloworld.resources;

import com.example.helloworld.core.UserTest;
import com.example.helloworld.db.UserDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserDAO userDAO;
    @Inject
    public UserResource(@Named("UserDAO") UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @POST
    @UnitOfWork
    public Response createUser(@Valid UserTest userParam) {

        System.out.println("-----------------------------");
        System.out.println("@POST /user start");

        if(userParam.getUserId() == null || userParam.getUserPassword() == null){
            System.out.println("return STATUS_CODE 400");
            System.out.println("-----------------------------");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        UserTest user = userDAO.findByUserId(userParam.getUserId());

        if(user == null){
            try{
                userDAO.create(userParam);
                String result = "success";
                System.out.println("return STATUS_CODE 200");
                System.out.println("-----------------------------");
                return Response.ok(result).build();
            }
            catch(Exception e){
                System.out.println("return STATUS_CODE 500");
                System.out.println("-----------------------------");
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
        String result = "fail";
        return Response.ok(result).build();
    }

    @GET
    @UnitOfWork
    public Response userExist(@QueryParam("userId") String userId) {

        System.out.println("-----------------------------");
        System.out.println("@GET /user start");

        if(userId == null){
            System.out.println("return STATUS_CODE 400");
            System.out.println("-----------------------------");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        UserTest user = userDAO.findByUserId(userId);

        if(user == null){
            System.out.println("return STATUS_CODE 200 and succcess");
            System.out.println("-----------------------------");
            String result = "success";
            return Response.ok(result).build();
        }

        System.out.println("return STATUS_CODE 200 and fail");
        System.out.println("-----------------------------");
        String result = "fail";
        return Response.ok(result).build();
    }
}
