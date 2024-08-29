package py.edu.ucom.entities.apiresponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Gastos {
    private Integer id;
    private LocalDate fecha;
    private BigDecimal monto;
    private String descripcion;

    public Gastos(Integer id, LocalDate fecha, BigDecimal monto, String descripcion) {
        setId(id);
        setFecha(fecha);
        setMonto(monto);
        setDescripcion(descripcion);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un valor positivo.");
        }
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        if (fecha.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser futura.");
        }
        this.fecha = fecha;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El monto debe ser un valor positivo.");
        }
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (Objects.isNull(descripcion) || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía.");
        }
        this.descripcion = descripcion;
    }

    public BigDecimal getCostoTotal() {
        return this.monto;
    }

    public static List<Gastos> getGastosEntreFechas(List<Gastos> gastos, LocalDate inicio, LocalDate fin) {
        List<Gastos> gastosEntreFechas = new ArrayList<>();
        for (Gastos gasto : gastos) {
            if (gasto.getFecha().isEqual(inicio) || gasto.getFecha().isAfter(inicio) && gasto.getFecha().isBefore(fin)) {
                gastosEntreFechas.add(gasto);
            }
        }
        return gastosEntreFechas;
    }
}