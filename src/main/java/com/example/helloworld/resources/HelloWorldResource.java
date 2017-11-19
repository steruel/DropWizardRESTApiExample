package com.example.helloworld.resources;

import com.example.helloworld.api.Saying;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;

@Path("/cognitive")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String templateH;
    private final String templateG;
    private final String defaultName;
    private final AtomicLong counterH;
    private final AtomicLong counterG;
    private final AtomicLong counterP;

    static class Entity {
        @JsonProperty
        String name;
    }


    public HelloWorldResource(String templateH, String templateG, String defaultName) {
        this.templateH = templateH;
        this.templateG = templateG;
        this.defaultName = defaultName;
        this.counterH = new AtomicLong();
        this.counterG = new AtomicLong();
        this.counterP = new AtomicLong();
    }


    @GET
    @Path("status")
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(templateH, name.orElse(defaultName));
        System.out.print("1");
        return new Saying(counterH.incrementAndGet(), value);
    }

    @GET
    @Path("data")
    @Timed
    public Saying sayGoodbye(@QueryParam("name") Optional<String> name) {
        final String value = String.format(templateG, name.orElse(defaultName));
        System.out.print("2");
        return new Saying(counterG.incrementAndGet(), value);
    }

    @POST
    @Path("analyse")
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed
    public Saying analyse(List<Entity> entities) {
        // Do something with entities...
        for(int i=0;i< entities.size();i++)
        {
            Entity current = entities.get(i);
            return new Saying(counterP.incrementAndGet(), current.name.toString());
        }
        return null;
    }
/*    public Saying analyse(@QueryParam("name") Optional<String> name) {
        final String value = String.format(templateG, name.orElse(defaultName));
        System.out.print("2");
        return new Saying(counterG.incrementAndGet(), value);
    }*/
}
