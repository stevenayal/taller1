package py.edu.com;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;


@Path("/recursos-basicos")
public class GreetingResource {

    @GET
    @Path("/suma")
    @Produces(MediaType.TEXT_PLAIN)
    public int sum(@QueryParam("a") int a, @QueryParam("b") int b) {
        return a + b;
    }

    @GET
    @Path("/resta")
    @Produces(MediaType.TEXT_PLAIN)
    public int subtract(@QueryParam("a") int a, @QueryParam("b") int b) {
        return a - b;
    }
}
