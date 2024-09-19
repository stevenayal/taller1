package py.edu.ucom.controllers.proyecto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import py.edu.ucom.entities.proyecto.PresupuestoMensual;
import py.edu.ucom.repository.PresupuestoMensualRepository;

import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

@Path("/presupuestos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PresupuestoResource {

    @Inject
    PresupuestoMensualRepository presupuestoMensualRepository;

    @POST
    @Operation(summary = "Crear un nuevo presupuesto", description = "Crea un nuevo presupuesto y lo guarda en la base de datos.")
    public Response crearPresupuesto(PresupuestoMensual presupuestoMensual) {
        PresupuestoMensual nuevoPresupuesto = presupuestoMensualRepository.crearPresupuesto(presupuestoMensual);
        return Response.status(Response.Status.CREATED).entity(nuevoPresupuesto).build();
    }

    @GET
    @Operation(summary = "Listar todos los presupuestos", description = "Obtiene una lista de todos los presupuestos almacenados.")
    public List<PresupuestoMensual> listarPresupuestos() {
        return presupuestoMensualRepository.listar();
    }

    @GET
    @Path("/{presupuestoId}")
    @Operation(summary = "Obtener un presupuesto específico", description = "Obtiene el presupuesto con el ID especificado.")
    public Response obtenerPresupuesto(@PathParam("presupuestoId") Integer presupuestoId) {
        Optional<PresupuestoMensual> presupuesto = presupuestoMensualRepository.obtenerById(presupuestoId);
        if (presupuesto.isPresent()) {
            return Response.ok(presupuesto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Presupuesto no encontrado").build();
        }
    }

    @DELETE
    @Path("/{presupuestoId}")
    @Operation(summary = "Eliminar un presupuesto", description = "Elimina el presupuesto con el ID especificado.")
    public Response eliminarPresupuesto(@PathParam("presupuestoId") Integer presupuestoId) {
        Optional<PresupuestoMensual> presupuestoOpt = presupuestoMensualRepository.obtenerById(presupuestoId);
        if (presupuestoOpt.isPresent()) {
            presupuestoMensualRepository.eliminar(presupuestoOpt.get());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Presupuesto no encontrado").build();
        }
    }

    @GET
    @Path("/count")
    @Operation(summary = "Contar presupuestos", description = "Obtiene el número total de presupuestos.")
    public Response contarPresupuestos() {
        long total = presupuestoMensualRepository.contar();
        return Response.ok(total).build();
    }

    @GET
    @Path("/final/presupuestos-mensual/{rangoInicial}/{rangoFinal}")
    @Operation(summary = "Filtrar presupuestos por rango de montos", description = "Lista los presupuestos cuyo monto presupuestado está entre los valores especificados.")
    public Response filtrarPresupuestosPorRango(@PathParam("rangoInicial") int rangoInicial,
                                                @PathParam("rangoFinal") int rangoFinal) {
        List<PresupuestoMensual> presupuestos = presupuestoMensualRepository.filtrarPorRangoMontos(rangoInicial, rangoFinal);

        if (presupuestos.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No se encontraron presupuestos en el rango especificado")
                    .build();
        }

        return Response.ok(presupuestos).build();
    }
    @GET
    @Path("/final/presupuestos-mensual/mayor-presupuesto")
    @Operation(summary = "Obtener el presupuesto con el monto más alto", description = "Devuelve el presupuesto con el monto presupuestado más alto.")
    public Response obtenerMayorPresupuesto() {
        List<PresupuestoMensual> presupuestos = presupuestoMensualRepository.obtenerPresupuestosConMayorMonto();

        if (presupuestos.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No se encontraron presupuestos.")
                    .build();
        }

        return Response.ok(presupuestos).build();
    }
    @PersistenceContext
    private EntityManager entityManager;

    public List<PresupuestoMensual> obtenerPresupuestosConMayorMonto() {
        TypedQuery<PresupuestoMensual> query = entityManager.createQuery(
                "SELECT p FROM PresupuestoMensual p WHERE p.saldoInicial = (SELECT MAX(p2.saldoInicial) FROM PresupuestoMensual p2)",
                PresupuestoMensual.class);
        return query.getResultList();
    }

}