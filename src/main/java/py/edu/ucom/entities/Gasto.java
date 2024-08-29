package py.edu.ucom.entities;

import java.time.LocalDate;

public class Gasto {
    private int id;
    private LocalDate fecha;
    private double monto;
    private String descripcion;

    // Constructor
    public Gasto() {}

    public Gasto(int id, LocalDate fecha, double monto, String descripcion) {
        this.id = id;
        this.fecha = fecha;
        this.monto = monto;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

