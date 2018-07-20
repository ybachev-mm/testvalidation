package test.validation.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import test.validation.db.EmployeeDAO;
import test.validation.db.model.Employee;
import test.validation.dropwizard.exceptions.ErrorCodeResponseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/employee")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class EmployeeResource {

    EmployeeDAO employeeDAO;

    public EmployeeResource(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @GET
    @UnitOfWork
    public List<Employee> get(@QueryParam("name") Optional<String> name) {
        if (name.isPresent()) {
            return employeeDAO.findByName(name.get());
        }
        return employeeDAO.getAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<Employee> getById(@PathParam("id") LongParam id) {
        // use only for quick testing status code = 10 is't real
        if (id.get() == 10) {
            throw ErrorCodeResponseException.buildCustomException(10);
        }
        // use only for quick testing status code and message are fictional
        if (id.get() == 20) {
            throw ErrorCodeResponseException.buildCustomException(69, "Opps");
        }
        // using our own enum for status and message
        if (id.get() == 30) {
            throw ErrorCodeResponseException.buildCustomException(ErrorCodeResponseException.Status.EXPECTATION_FAILED);
        }
        // using our own enum for status and message with possibility to substitute values
        if (id.get() == 40) {
            throw ErrorCodeResponseException.buildCustomException(ErrorCodeResponseException.Status.WRONG_PARAMETERS, "getById", "test");
        }

        return employeeDAO.findById(id.get());
    }

    @POST
    @UnitOfWork
    public void saveEmployee(Employee employee){
        employeeDAO.saveEmployee(employee);
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public void deleteEmployee(@PathParam("id") LongParam id){
        Optional<Employee> byId = employeeDAO.findById(id.get());
        employeeDAO.delete(byId.get());
    }

}