

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import py.edu.ucom.controllers.proyecto.PresupuestoResource;
import py.edu.ucom.entities.proyecto.Cliente;
import py.edu.ucom.entities.proyecto.PresupuestoMensual;
import py.edu.ucom.repository.PresupuestoMensualRepository; // Asegúrate de que este sea el servicio correcto
import py.edu.ucom.repository.proyecto.ClienteRepository;

import java.util.List;

@Path("/final")
public class FinalResource {

    @Inject
    private PresupuestoResource presupuestoService;

    @Inject
    private PresupuestoMensualRepository presupuestoMensualRepository;

    @GET
    @Path("/count")
    @Operation(summary = "Contar presupuestos", description = "Obtiene el número total de presupuestos.")
    public Response contarPresupuestos() {
        long total = presupuestoMensualRepository.contar();
        return Response.ok(total).build();
    }

    @GET
    @Path("/presupuestos-mensual/{rangoInicial}/{rangoFinal}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Filtrar presupuestos por rango de montos", description = "Lista los presupuestos cuyo monto presupuestado está entre los valores especificados.")
    public Response filtrarPresupuestosPorRango(@PathParam("rangoInicial") int rangoInicial,
                                                @PathParam("rangoFinal") int rangoFinal) {
        List<PresupuestoMensual> presupuestos = presupuestoMensualRepository.filtrarPorRangoMontos(rangoInicial, rangoFinal);

        if (presupuestos.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"mensaje\": \"No se encontraron presupuestos en el rango especificado\"}")
                    .build();
        }

        return Response.ok(presupuestos).build();
    }

    @GET
    @Path("/presupuestos-mensual/mayor-presupuesto")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obtener el presupuesto con el monto más alto", description = "Devuelve el presupuesto con el monto presupuestado más alto.")
    public Response obtenerMayorPresupuesto() {
        List<PresupuestoMensual> presupuestos = presupuestoMensualRepository.obtenerPresupuestosConMayorMonto();

        if (presupuestos.isEmpty()) {
            // Crear un objeto JSON de respuesta
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"mensaje\": \"No se encontraron presupuestos.\"}")
                    .build();
        }

        return Response.ok(presupuestos).build();
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