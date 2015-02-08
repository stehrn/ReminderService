package com.stehrn;

import com.mongodb.Mongo;
import com.stehrn.resources.MongoManaged;
import com.stehrn.resources.ReminderResource;
import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.config.SwaggerConfig;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
import com.wordnik.swagger.reader.ClassReaders;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.net.UnknownHostException;
import java.util.EnumSet;

/**
 * Created by Nik on 08/02/2015.
 */
public class ReminderApplication extends Application<ReminderConfiguration> {

    final static Logger logger = LoggerFactory.getLogger(ReminderApplication.class);

    @Override
    public void initialize(Bootstrap<ReminderConfiguration> bootstrap) {

    }

    @Override
    public String getName() {
        return "reminder-service";
    }

    @Override
    public void run(ReminderConfiguration configuration, Environment environment) throws Exception {

        logger.info("Welcome: " + configuration.defaultWelcome);

        setupMongo(configuration, environment);

        // register resources
        environment.jersey().register(new ReminderResource());

        setupSwagger(environment);
    }

    private void setupMongo(ReminderConfiguration configuration, Environment environment) throws UnknownHostException {
        Mongo mongo = new Mongo(configuration.mongohost, configuration.mongoport);
        MongoManaged mongoManaged = new MongoManaged(mongo);
        environment.lifecycle().manage(mongoManaged);
    }

    private void setupSwagger(Environment environment) {
        //https://github.com/swagger-api/swagger-core/wiki/JavaDropwizard-Quickstart

        // Swagger Resource
        environment.jersey().register(new ApiListingResourceJSON());

        // Swagger providers
        environment.jersey().register(new ApiDeclarationProvider());
        environment.jersey().register(new ResourceListingProvider());

        // Swagger Scanner, which finds all the resources for @Api Annotations
        ScannerFactory.setScanner(new DefaultJaxrsScanner());

        // Add the reader, which scans the resources and extracts the resource information
        ClassReaders.setReader(new DefaultJaxrsApiReader());

        // Set the swagger config options
        SwaggerConfig config = ConfigFactory.config();
        config.setApiVersion("1.0.1");
        config.setBasePath("http://localhost:8080");

        // optional CORS support
        FilterRegistration.Dynamic crossOriginRequsts = environment.servlets().addFilter("crossOriginRequsts", CrossOriginFilter.class);//"/*"
        crossOriginRequsts.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

    public static void main(String[] args) throws Exception {
        new ReminderApplication().run(new String[]{"server", "reminder-config.yaml"});
    }
}
