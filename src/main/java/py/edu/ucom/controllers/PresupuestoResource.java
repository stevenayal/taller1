package py.edu.ucom.controllers;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;  // Asegúrate de importar esta clase
import org.eclipse.microprofile.openapi.annotations.Operation;
import py.edu.ucom.entities.Presupuesto;
import py.edu.ucom.entities.Gasto;
import py.edu.ucom.repository.PresupuestoRepository;

import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

@Path("/presupuestos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PresupuestoResource {

    @Inject
    PresupuestoRepository presupuestoRepository;
    @POST
    @Operation(summary = "Crear un nuevo presupuesto", description = "Crea un nuevo presupuesto y lo guarda en la base de datos.")

    public Response crearPresupuesto(Presupuesto presupuesto) {
        // Aquí podrías agregar validaciones si es necesario
        presupuestoRepository.listar().add(presupuesto);
        presupuestoRepository.guardarDatos();
        return Response.status(Response.Status.CREATED).entity(presupuesto).build();
    }

    @GET
    @Operation(summary = "Listar todos los presupuestos", description = "Obtiene una lista de todos los presupuestos almacenados.")

    public List<Presupuesto> listarPresupuestos() {
        return presupuestoRepository.listar();
    }

    @GET
    @Path("/{presupuestoId}")
    @Operation(summary = "Obtener un presupuesto específico", description = "Obtiene el presupuesto con el ID especificado.")

    public Response obtenerPresupuesto(@PathParam("presupuestoId") Integer presupuestoId) {
        Optional<Presupuesto> presupuesto = presupuestoRepository.obtenerById(presupuestoId);
        if (presupuesto.isPresent()) {
            return Response.ok(presupuesto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Presupuesto no encontrado").build();
        }
    }

    @GET
    @Path("/total-gastos/{presupuestoId}")
    @Operation(summary = "Sumar gastos de un presupuesto", description = "Retorna la sumatoria de los gastos realizados en el presupuesto con el ID especificado.")

    public Response obtenerTotalGastos(@PathParam("presupuestoId") Integer presupuestoId) {
        Optional<Presupuesto> presupuestoOpt = presupuestoRepository.obtenerById(presupuestoId);
        if (presupuestoOpt.isPresent()) {
            double totalGastos = presupuestoOpt.get().getGastos().stream()
                    .mapToDouble(Gasto::getMonto)
                    .sum();
            return Response.ok(totalGastos).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Presupuesto no encontrado").build();
        }
    }

    @POST
    @Path("/agregar-gasto/{presupuestoId}")
    @Operation(summary = "Agregar un gasto a un presupuesto", description = "Inserta un nuevo gasto en el presupuesto con el ID especificado, validando que no supere el monto presupuestado.")

    public Response agregarGasto(@PathParam("presupuestoId") Integer presupuestoId, Gasto gasto) {
        Optional<Presupuesto> presupuestoOpt = presupuestoRepository.obtenerById(presupuestoId);
        if (presupuestoOpt.isPresent()) {
            Presupuesto presupuesto = presupuestoOpt.get();
            double totalGastos = presupuesto.getGastos().stream()
                    .mapToDouble(Gasto::getMonto)
                    .sum();
            if (totalGastos + gasto.getMonto() <= presupuesto.getMontoPresupuestado()) {
                presupuesto.getGastos().add(gasto);
                presupuestoRepository.guardarDatos();
                return Response.status(Response.Status.CREATED).entity(gasto).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("El gasto supera el monto presupuestado").build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Presupuesto no encontrado").build();
        }
    }
}
