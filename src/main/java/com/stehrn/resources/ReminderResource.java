package com.stehrn.resources;

import com.codahale.metrics.annotation.Timed;
import com.stehrn.core.Reminder;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * Created by Nik on 08/02/2015.
 */
@Api(value = "/reminders", description = "Set of resources of managing reminders")
@Path("/reminders")
public class ReminderResource {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Valid
    @Timed
    @ApiOperation(value = "Find reminder by Id", response = Reminder.class)
    public Reminder getReminder(
            @ApiParam(value = "Id of reminder", required = true)
            @PathParam("id") Long id) {
        return new Reminder(id, new Date(), "a description");
    }
}
