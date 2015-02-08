package com.stehrn.resources;

import com.stehrn.ReminderApplication;
import com.stehrn.ReminderConfiguration;
import com.stehrn.core.Reminder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * Created by Nik on 08/02/2015.
 */
public class ReminderIntegrationTest {


    @ClassRule
    public static final DropwizardAppRule<ReminderConfiguration> RULE = new DropwizardAppRule<ReminderConfiguration>(ReminderApplication.class,
            "C:\\java\\GitHub\\ReminderService\\src\\test\\resources\\reminder-test-config.yaml");

    @Test
    public void getReminderForValidId() throws Exception {
        Client client = new Client();
        WebResource resource = client.resource(String.format("http://localhost:%d/reminder/4444", RULE.getLocalPort()));
        Reminder reminder = resource.get(Reminder.class);
    }
}
