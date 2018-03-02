package com.irongate.web.rs;

import com.irongate.entities.BusinessEntity;
import com.irongate.services.business.crud.GenericCrudService;
import com.irongate.utils.EntitiesClasses;
import com.irongate.web.deserialize.JsonDeserialization;
import com.irongate.web.response.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/crud")
public class GenericService {

    @Autowired
    GenericCrudService genericCrudService;

    @POST
    @Path("/save/{entity}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("entity") String entity, @RequestBody String body) {
        Class entityType = EntitiesClasses.findClass(entity);
        if (entityType == null) {
            return ResponseBuilder.buildErrorResponse("Invalid entity type " + entity);
        }
        try {
            BusinessEntity deserializeEntity = JsonDeserialization.deserialize(body, entityType);
            genericCrudService.save(deserializeEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.buildErrorResponse(e.getMessage());
        }


        return ResponseBuilder.buildSucessResponse("Successfully created");
    }

    @POST
    @Path("/update/{entity}/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("entity") String entity,
                           @RequestBody String body, @PathParam("id") String id) {
        Class entityType = EntitiesClasses.findClass(entity);
        if (entityType == null) {
            return ResponseBuilder.buildErrorResponse("Invalid entity type " + entity);
        }
        try {
            BusinessEntity deserializeEntity = JsonDeserialization.deserialize(body, entityType);
            deserializeEntity.setId(id);
            genericCrudService.update(deserializeEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.buildErrorResponse(e.getMessage());
        }

        return ResponseBuilder.buildSucessResponse("Successfully updated");
    }

    @GET
    @Path("/read/{entity}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(@PathParam("entity") String entity, @PathParam("id") String id) {
        Class entityType = EntitiesClasses.findClass(entity);
        if (entityType == null) {
            return ResponseBuilder.buildErrorResponse("Invalid entity type");
        }
        try {
            return ResponseBuilder.buildSucessResponse(genericCrudService.read(entityType, id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.buildErrorResponse(e.getMessage());
        }
    }

    @GET
    @Path("/delete/{entity}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("entity") String entity, @PathParam("id") String id) {
        Class entityType = EntitiesClasses.findClass(entity);
        if (entityType == null) {
            return ResponseBuilder.buildErrorResponse("Invalid entity type " + entity);
        }
        try {
            genericCrudService.delete(entityType, id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.buildErrorResponse(e.getMessage());
        }
        return ResponseBuilder.buildSucessResponse("Successfully deleted");
    }

    @GET
    @Path("/readAll/{entity}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAll(@PathParam("entity") String entity) {
        Class entityType = EntitiesClasses.findClass(entity);
        if (entityType == null) {
            return ResponseBuilder.buildErrorResponse("Invalid entity type " + entity);
        }
        try {
            return ResponseBuilder.buildSucessResponse(genericCrudService.findAll(entityType));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.buildErrorResponse(e.getMessage());
        }
    }
}
