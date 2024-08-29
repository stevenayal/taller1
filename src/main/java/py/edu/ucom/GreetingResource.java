package py.edu.ucom;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/greeting")  // Cambié el path base para evitar conflicto
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
