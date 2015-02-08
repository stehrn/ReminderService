package com.stehrn.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import com.wordnik.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Nik on 08/02/2015.
 */
@ApiModel(value = "A reminder to do something on a given date")
public class Reminder {

    private long id;
    @NotNull
    private Date date;
    @NotEmpty
    private String description;

    public Reminder() {
        // required for deserialization
    }

    public Reminder(long id, Date date, String description) {
        this.id = id;
        this.date = date;
        this.description = description;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public Date getDate() {
        return date;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reminder reminder = (Reminder) o;

        if (id != reminder.id) return false;
        if (!date.equals(reminder.date)) return false;
        if (!description.equals(reminder.description)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, date, description);
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
