package com.stehrn.resources;

import com.mongodb.Mongo;
import io.dropwizard.lifecycle.Managed;

/**
 * Created by Nik on 08/02/2015.
 */
public class MongoManaged implements Managed {

    private final Mongo mongo;

    public MongoManaged(Mongo mongo) {
        this.mongo = mongo;
    }

    @Override
    public void start() throws Exception {

    }

    @Override
    public void stop() throws Exception {
        this.mongo.close();
    }
}
