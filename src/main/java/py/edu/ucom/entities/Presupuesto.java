package py.edu.ucom.entities;

import java.time.LocalDate;
import java.util.List;

public class Presupuesto {
    private Integer id; // Cambiado a Integer
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double montoPresupuestado;
    private List<Gasto> gastos;

    // Constructor
    public Presupuesto() {}

    public Presupuesto(Integer id, LocalDate fechaInicio, LocalDate fechaFin, double montoPresupuestado, List<Gasto> gastos) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.montoPresupuestado = montoPresupuestado;
        this.gastos = gastos;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getMontoPresupuestado() {
        return montoPresupuestado;
    }

    public void setMontoPresupuestado(double montoPresupuestado) {
        this.montoPresupuestado = montoPresupuestado;
    }

    public List<Gasto> getGastos() {
        return gastos;
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }
}
