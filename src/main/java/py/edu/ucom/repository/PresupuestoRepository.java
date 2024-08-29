package py.edu.ucom.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.enterprise.context.ApplicationScoped;
import py.edu.ucom.entities.Presupuesto;
import py.edu.ucom.entities.Gasto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

@ApplicationScoped
public class PresupuestoRepository {
    private static final String FILE_PATH = "presupuestos.json"; // Ajusta la ruta según tus necesidades
    private List<Presupuesto> presupuestos = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(PresupuestoRepository.class.getName());
    private AtomicInteger idCounter = new AtomicInteger(); // Para generar IDs únicos

    public PresupuestoRepository() {
        cargarDatos();
        inicializarIdCounter();
    }

    private void inicializarIdCounter() {
        presupuestos.forEach(p -> {
            if (p.getId() > idCounter.get()) {
                idCounter.set(p.getId());
            }
        });
    }

    public void cargarDatos() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                presupuestos = mapper.readValue(file, new TypeReference<List<Presupuesto>>() {});
            }
        } catch (IOException e) {
            LOGGER.severe("Error cargando datos: " + e.getMessage());
        }
    }

    public void guardarDatos() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(FILE_PATH), presupuestos);
        } catch (IOException e) {
            LOGGER.severe("Error guardando datos: " + e.getMessage());
        }
    }

    public Presupuesto crearPresupuesto(Presupuesto presupuesto) {
        presupuesto.setId(idCounter.incrementAndGet());
        presupuestos.add(presupuesto);
        guardarDatos();
        return presupuesto;
    }

    public Optional<Presupuesto> obtenerById(Integer id) {
        return presupuestos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public List<Presupuesto> listar() {
        return new ArrayList<>(presupuestos);
    }

    public void agregarGasto(Integer presupuestoId, Gasto gasto) {
        Optional<Presupuesto> presupuestoOpt = obtenerById(presupuestoId);
        if (presupuestoOpt.isPresent()) {
            Presupuesto presupuesto = presupuestoOpt.get();
            presupuesto.getGastos().add(gasto);
            guardarDatos();
        } else {
            LOGGER.warning("Presupuesto con ID " + presupuestoId + " no encontrado.");
        }
    }
}
