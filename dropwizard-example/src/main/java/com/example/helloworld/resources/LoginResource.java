package com.example.helloworld.resources;

import com.example.helloworld.core.UserTest;
import com.example.helloworld.db.UserDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/signin")
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

    private final UserDAO userDAO;
    public LoginResource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GET
    @UnitOfWork
    public Response login(@QueryParam("userId") String userId, @QueryParam("userPassword") String userPassword) {

        System.out.println("-----------------------------");
        System.out.println("@GET /signin start");

        if(userId == null || userPassword == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        UserTest user = userDAO.findByUserId(userId);

        if(user == null){
            System.out.println("return STATUS_CODE 403 and noId");
            System.out.println("-----------------------------");
            return Response.status(Response.Status.FORBIDDEN).entity("noId").build();
        }

        if(user.getUserPassword().equals(userPassword)){
            Long id = user.getId();
            System.out.println("return STATUS_CODE 200");
            System.out.println("-----------------------------");
            return Response.ok(id).build();
        }

        System.out.println("return STATUS_CODE 403 and wrongPassword");
        System.out.println("-----------------------------");
        return Response.status(Response.Status.FORBIDDEN).entity("wrongPassword").build();
    }
}
