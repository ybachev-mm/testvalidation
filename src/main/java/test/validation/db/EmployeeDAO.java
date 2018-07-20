package test.validation.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import test.validation.db.model.Employee;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author yavor bachev
 * @since 0.1
 * 2018 Jul
 */
public class EmployeeDAO extends AbstractDAO<Employee> {

    public EmployeeDAO(SessionFactory factory) {
        super(factory);
    }

    public List<Employee> getAll() {
        return list(namedQuery("Employee.findAll"));
    }

    public List<Employee> findByName(String name) {
        return list(namedQuery("Employee.findByName")
                    .setParameter("name", "%" + name + "%")
        );
    }

    public Optional<Employee> findById(long id) {
        return Optional.ofNullable(get(id));
    }

    public void delete(Employee employee) {
        currentSession().delete(employee);
    }

    public void saveEmployee(Employee employee){
        Serializable save = currentSession().save(employee);
        System.out.println(save);
    }
}
