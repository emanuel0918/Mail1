package com.example.gmailbot.controller;
import com.example.gmailbot.service.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@Path("/api")
public class ResourceController {

    @Path("status")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatus() {
        StatusMessage statusMessage = new StatusMessage();
        statusMessage.setSuccess(true);
        statusMessage.setStatus(Response.Status.OK.getStatusCode());
        statusMessage.setMessage("Mikrotik ApiResource Status is OK...");
        return Response.status(Response.Status.OK.getStatusCode()).entity(statusMessage).build();
    }
    
    @Path("get-token")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response executeTest() throws JsonProcessingException, Exception {
        
        return Response.status(Response.Status.OK.getStatusCode()).entity(new Gson().toJson(null)).build();
        
    }

    private boolean isLinuxServer() {
        try {
            String os = System.getProperty("os.name");
            System.out.println("This is a " + os);
            if (os.contains("inux")) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    
}
