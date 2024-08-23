package home.ahmad.restapi_jersesy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import home.ahmad.restapi_jersesy.service.EmployeeService;
import home.ahmad.restapi_jersey.model.Employee;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

import java.util.List;

/**
 * jersey-controller
 * http://localhost:8080/employees
 * 
 * Manuelle Link-Erstellung: Anstatt Spring HATEOAS zu verwenden, habe ich in der getEmployeeById-Methode UriBuilder von Jersey verwendet, 
 * um den Link zur Ressource zu erstellen.
 * 
 * @author Ahmad Alrefai
 */
@Component
@Path("/employees")
public class EmployeeController {

    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getAllEmployees() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeById(@PathParam("id") Long id, @Context UriInfo uriInfo) {
        Employee employee = service.findById(id);
        if (employee == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Long.toString(id));
        return Response.ok(employee)
                       .link(uriBuilder.build(), "self")
                       .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEmployee(Employee employee, @Context UriInfo uriInfo) {
        Employee createdEmployee = service.save(employee);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder().path(Long.toString(createdEmployee.getId()));
        return Response.created(uriBuilder.build()).entity(createdEmployee).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(@PathParam("id") Long id, Employee employee) {
        employee.setId(id);
        Employee updatedEmployee = service.save(employee);
        return Response.ok(updatedEmployee).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEmployee(@PathParam("id") Long id) {
        service.deleteById(id);
        return Response.noContent().build();
    }
}
