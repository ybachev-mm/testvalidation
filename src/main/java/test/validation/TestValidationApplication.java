package test.validation;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.flywaydb.core.Flyway;
import test.validation.config.MyJerseyViolationExceptionMapper;
import test.validation.db.EmployeeDAO;
import test.validation.resources.EmployeeResource;

public class TestValidationApplication extends Application<TestValidationConfiguration> {

    private final HibernateBundle<TestValidationConfiguration> hibernate =
                    new ScanningHibernateBundle<TestValidationConfiguration>("test.validation.db.model") {
        @Override
        public DataSourceFactory getDataSourceFactory(TestValidationConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(final String[] args) throws Exception {
        new TestValidationApplication().run(args);
    }

    @Override
    public String getName() {
        return "TestValidation";
    }

    @Override
    public void initialize(final Bootstrap<TestValidationConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final TestValidationConfiguration configuration, final Environment environment) {
        final EmployeeDAO personDAO = new EmployeeDAO(hibernate.getSessionFactory());
        final EmployeeResource personResource = new EmployeeResource(personDAO);

        DataSourceFactory dataSourceFactory = configuration.getDataSourceFactory();
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSourceFactory.getUrl(), dataSourceFactory.getUser(), dataSourceFactory.getPassword());
        flyway.migrate();

        environment.jersey().register(personResource);
        environment.jersey().register(new MyJerseyViolationExceptionMapper());
    }

}
