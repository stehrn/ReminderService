package com.stehrn;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by Nik on 08/02/2015.
 */
public class ReminderConfiguration extends Configuration {

    @JsonProperty
    public String defaultWelcome;

    @JsonProperty
    @NotEmpty
    public String mongohost = "localhost";

    @JsonProperty
    @Min(1)
    @Max(65535)
    public int mongoport = 27017;

    @JsonProperty
    @NotEmpty
    public String mongodb = "mydb";
}
