package py.edu.ucom.controllers.proyecto;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import py.edu.ucom.entities.proyecto.Cliente;
import py.edu.ucom.model.response.ApiResponse;
import py.edu.ucom.repository.proyecto.ClienteRepository;
import py.edu.ucom.services.proyecto.ClienteService;

import java.util.List;

@Path("cliente")
public class ClienteResource {
    private final ClienteService service;

    public ClienteResource(ClienteService service) {
        this.service = service;
    }

    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse<Cliente> obtenerPorId(
            @PathParam("id") Integer id) {
        ApiResponse<Cliente> respuesta = new ApiResponse();

        try {
            respuesta.setCode(Response.Status.OK.getStatusCode());
            respuesta.setMessage("Se obtuvo exitosamente.");
            respuesta.setData(service.obtener(id));
        } catch (Exception e) {

            respuesta.setCode(Response.Status.CONFLICT.getStatusCode());
            respuesta.setMessage("No se obtuvo  la entidad con id :" + id);
            respuesta.setData(null);
        }

        return respuesta;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse<List<Cliente>> listar() {
        ApiResponse<List<Cliente>> respuesta = new ApiResponse();

        try {
            respuesta.setCode(Response.Status.OK.getStatusCode());
            respuesta.setMessage("Se obtuvo exitosamente el listado.");
            respuesta.setData(service.listar());
        } catch (Exception e) {

            respuesta.setCode(Response.Status.CONFLICT.getStatusCode());
            respuesta.setMessage("No se obtuvo el listado.");
            respuesta.setData(null);
        }

        return respuesta;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse<Cliente> agregar(Cliente param) {
        ApiResponse<Cliente> respuesta = new ApiResponse();

        try {
            this.service.agregar(param);
            respuesta.setCode(Response.Status.OK.getStatusCode());
            respuesta.setMessage("Se agrego exitosamente.");
            respuesta.setData(param);
        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setCode(Response.Status.CONFLICT.getStatusCode());
            respuesta.setMessage("No se pudo agregar la entidad.");
            respuesta.setData(null);
        }

        return respuesta;
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse<Cliente> modificar(Cliente param, @PathParam("id") Integer Id) {
        ApiResponse<Cliente> respuesta = new ApiResponse();

        try {
            this.service.modificar(param);
            respuesta.setCode(Response.Status.OK.getStatusCode());
            respuesta.setMessage("Se agrego exitosamente.");
            respuesta.setData(param);
        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setCode(Response.Status.CONFLICT.getStatusCode());
            respuesta.setMessage("No se pudo agregar la entidad.");
            respuesta.setData(null);
        }

        return respuesta;
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse<Object> eliminar(@PathParam("id") Integer id) {
        ApiResponse<Object> respuesta = new ApiResponse();

        try {
            this.service.eliminar(id);
            respuesta.setCode(Response.Status.OK.getStatusCode());
            respuesta.setMessage("Se elimino exitosamente.");
            respuesta.setData(null);
        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setCode(Response.Status.CONFLICT.getStatusCode());
            respuesta.setMessage("No se pudo eliminar la entidad.");
            respuesta.setData(null);
        }

        return respuesta;
    }
    @Inject
    ClienteRepository clienteRepository;

    @GET
    @Path("/buscar")
    public Response buscarClientes(@QueryParam("nombres") String nombres,
                                   @QueryParam("apellidos") String apellidos) {
        List<Cliente> clientes = clienteRepository.buscarClientes(nombres, apellidos);

        if (clientes.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No se encontraron clientes.")
                    .build();
        }

        return Response.ok(clientes).build();
    }

}
