package com.stehrn.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Nik on 08/02/2015.
 */
public class ReminderTest {

    public static final ObjectMapper OBJECT_MAPPER = Jackson.newObjectMapper();

    @Test
    public void serialize() throws Exception {
        Reminder reminder = new Reminder(4455, null, "Feed the cat");
        assertThat(fixture("fixtures/Reminder.json"), is(OBJECT_MAPPER.writeValueAsString(reminder)));
    }
}
