package com.stehrn.resources;

import com.stehrn.core.Reminder;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Nik on 08/02/2015.
 * <p/>
 * see http://dropwizard.io/manual/testing.html
 */
public class ReminderResourceTest {

    @ClassRule
    public static ResourceTestRule resource = ResourceTestRule.builder().addResource(new ReminderResource()).build();

    @Test
    public void getReminderForValidId() throws Exception {
        Reminder expectedReminder = new Reminder(4444, new Date(), "Feed the cat");
        Reminder actualReminder = resource.client().resource("/reminders/4444").get(Reminder.class);
        assertThat(expectedReminder, is(equalTo(actualReminder)));
    }
}
