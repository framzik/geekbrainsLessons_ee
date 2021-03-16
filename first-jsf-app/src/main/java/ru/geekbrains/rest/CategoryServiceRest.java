package ru.geekbrains.rest;

import ru.geekbrains.DtoEntities.CategoryRepr;
import ru.geekbrains.ProductRepr;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/category")
public interface CategoryServiceRest {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<CategoryRepr> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    CategoryRepr findById(@PathParam("id") Long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(CategoryRepr category);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(CategoryRepr category);

    @DELETE
    @Path("/{id}")
    void deleteById(@PathParam("id")Long id);

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    Long countAll();


}
