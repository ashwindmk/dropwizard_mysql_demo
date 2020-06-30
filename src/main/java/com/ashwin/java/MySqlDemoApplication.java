package com.ashwin.java;

import com.ashwin.java.repository.EmployeeRepository;
import com.ashwin.java.resource.EmployeeResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class MySqlDemoApplication extends Application<MySqlDemoConfiguration> {
    private static final Logger logger = LoggerFactory.getLogger(MySqlDemoApplication.class);
    private static final String SQL = "sql";
    private static final String MYSQL_DEMO = "mysql-demo";

    public static void main(String[] args) throws Exception {
        new MySqlDemoApplication().run(args);
    }

    @Override
    public String getName() {
        return MYSQL_DEMO;
    }

    @Override
    public void initialize(Bootstrap<MySqlDemoConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(true, false)));
    }

    @Override
    public void run(MySqlDemoConfiguration config, Environment env) throws Exception {
        final DataSource dataSource = config.getDataSourceFactory().build(env.metrics(), SQL);
        DBI dbi = new DBI(dataSource);

        logger.info("Registering RESTful API resources");
        env.jersey().register(new EmployeeResource(dbi.onDemand(EmployeeRepository.class)));
    }
}
