package com.ashwin.java.resource;

import com.ashwin.java.model.Employee;
import com.ashwin.java.repository.EmployeeRepository;
import com.codahale.metrics.annotation.Timed;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {
    private final EmployeeRepository employeeRepository;

    public EmployeeResource(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GET
    @Timed
    public Response getEmployees() {
        return Response.ok(employeeRepository.getEmployees()).build();
    }

    @GET
    @Timed
    @Path("{id}")
    public Response getEmployee(@PathParam("id") final int id) {
        return Response.ok(employeeRepository.getEmployee(id)).build();
    }

    @POST
    @Timed
    public Response createEmployee(@NotNull @Valid final Employee employee) {
        Employee employeeCreate = new Employee(employee.getName(), employee.getDepartment(), employee.getSalary());
        return Response.ok(employeeRepository.createEmployee(employeeCreate)).build();
    }

    @PUT
    @Timed
    @Path("{id}")
    public Response editEmployee(@NotNull @Valid final Employee employee, @PathParam("id") final int id) {
        employee.setId(id);
        return Response.ok(employeeRepository.editEmployee(employee)).build();
    }

    @DELETE
    @Timed
    @Path("{id}")
    public Response deleteEmployee(@PathParam("id") final int id) {
        Map<String, String> response = new HashMap<>();
        response.put("status", employeeRepository.deleteEmployee(id));
        return Response.ok(response).build();
    }
}
