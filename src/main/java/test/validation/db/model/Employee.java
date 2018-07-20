package test.validation.db.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author yavor bachev
 * @since 0.1
 * 2018 Jul
 */

@Entity
@Table(name = "EMPLOYEE")
@NamedQueries({
                @NamedQuery(name = "Employee.findAll",
                                query = "select e from Employee e"),
                @NamedQuery(name = "Employee.findByName",
                                query = "select e from Employee e "
                                                + "where e.name like :name ")
})
public class Employee {

    @Id
    @Column(name = "ID", nullable = false)
    @NotNull
    @JsonProperty
    private Long id;

    @Column(name = "NAME", length = 100, nullable = false)
    @NotNull
    @JsonProperty
    private String name;

    @Column(name = "EMAIL")
    @Size(max=1)
    @NotNull
    @JsonProperty
    private String email;

    public Long getId() {
        return id;
    }

    public Employee setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee person = (Employee) o;

        if (id != null ? !id.equals(person.id) : person.id != null) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0) + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", email='" + email + '\'' +
                        '}';
    }
}
