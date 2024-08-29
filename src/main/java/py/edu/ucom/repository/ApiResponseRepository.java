package py.edu.ucom.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import py.edu.ucom.entities.apiresponse.Gastos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ApiResponseRepository {

    private static final String FILE_PATH = "src/main/resources/data/gastos.json";
    private List<Gastos> gastosList;
    private final ObjectMapper objectMapper;

    public ApiResponseRepository() {
        objectMapper = new ObjectMapper();
        gastosList = cargarDatos();
    }

    private List<Gastos> cargarDatos() {
        try {
            System.out.println("CARGA DE DATOS" + FILE_PATH);
            File file = new File(FILE_PATH);
            if (file.exists()) {
                return objectMapper.readValue(file, new TypeReference<List<Gastos>>() {});
            } else {
                System.out.println("UPSSS NO HAY DATOS");
                return new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private synchronized void guardarDatos() {
        try {
            objectMapper.writeValue(new File(FILE_PATH), gastosList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<Gastos> obtenerById(Integer id) {
        return gastosList.stream()
                .filter(gasto -> gasto.getId().equals(id))
                .findFirst();
    }

    public List<Gastos> listar() {
        return new ArrayList<>(gastosList);
    }

    public synchronized Gastos agregar(Gastos param) {
        Integer newId = gastosList.isEmpty() ? 1
                : gastosList.stream()
                .mapToInt(Gastos::getId)
                .max()
                .orElse(0) + 1;

        param.setId(newId);
        gastosList.add(param);
        guardarDatos();
        return param;
    }

    public synchronized Gastos modificar(Gastos param) {
        Optional<Gastos> existingGasto = obtenerById(param.getId());

        if (existingGasto.isPresent()) {
            gastosList = gastosList.stream()
                    .map(gasto -> gasto.getId().equals(param.getId()) ? param : gasto)
                    .collect(Collectors.toList());
            guardarDatos();
            return param;
        } else {
            return null;
        }
    }

    public synchronized void eliminar(Integer id) {
        gastosList = gastosList.stream()
                .filter(gasto -> !gasto.getId().equals(id))
                .collect(Collectors.toList());
        guardarDatos();
    }
}
