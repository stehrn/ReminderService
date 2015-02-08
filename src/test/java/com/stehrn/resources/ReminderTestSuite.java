package com.stehrn.resources;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Nik on 08/02/2015.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ReminderIntegrationTest.class,
        ReminderResourceTest.class})
public class ReminderTestSuite {
}
