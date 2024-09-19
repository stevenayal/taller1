package py.edu.ucom.entities.proyecto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import py.edu.ucom.entities.Presupuesto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "presupuesto_mensual")
@NamedQueries({
        @NamedQuery(name = "PresupuestoMensual.findAll", query = "SELECT p FROM PresupuestoMensual p"),
        @NamedQuery(name = "PresupuestoMensual.findByIdPresupuesto", query = "SELECT p FROM PresupuestoMensual p WHERE p.idPresupuesto = :idPresupuesto"),
        @NamedQuery(name = "PresupuestoMensual.findByFechaIncio", query = "SELECT p FROM PresupuestoMensual p WHERE p.fechaIncio = :fechaIncio"),
        @NamedQuery(name = "PresupuestoMensual.findByFechaFin", query = "SELECT p FROM PresupuestoMensual p WHERE p.fechaFin = :fechaFin"),
        @NamedQuery(name = "PresupuestoMensual.findBySaldoInicial", query = "SELECT p FROM PresupuestoMensual p WHERE p.saldoInicial = :saldoInicial"),
        @NamedQuery(name = "PresupuestoMensual.findBySaldoFinal", query = "SELECT p FROM PresupuestoMensual p WHERE p.saldoFinal = :saldoFinal"),
        @NamedQuery(name = "PresupuestoMensual.findByEstado", query = "SELECT p FROM PresupuestoMensual p WHERE p.estado = :estado")
})
public class PresupuestoMensual extends Presupuesto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_presupuesto")
    private Integer idPresupuesto;

    @Basic(optional = false)
    @Column(name = "fecha_incio")
    private LocalDate fechaIncio;

    @Basic(optional = false)
    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Basic(optional = false)
    @Column(name = "saldo_inicial")
    private int saldoInicial;

    @Basic(optional = false)
    @Column(name = "saldo_final")
    private int saldoFinal;

    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "presupuestoMensual")
    private List<PresupuestoCategoria> presupuestoCategoriaList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPresupuesto")
    private List<Movimientos> movimientosList;

    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne(optional = false)
    private Cliente idCliente;

    public PresupuestoMensual() {
    }

    public PresupuestoMensual(Integer idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public PresupuestoMensual(Integer idPresupuesto, LocalDate fechaIncio, LocalDate fechaFin, int saldoInicial, int saldoFinal, String estado) {
        this.idPresupuesto = idPresupuesto;
        this.fechaIncio = fechaIncio;
        this.fechaFin = fechaFin;
        this.saldoInicial = saldoInicial;
        this.saldoFinal = saldoFinal;
        this.estado = estado;
    }

    // Getters y setters
    public Integer getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(Integer idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public LocalDate getFechaIncio() {
        return fechaIncio;
    }

    public void setFechaIncio(LocalDate fechaIncio) {
        this.fechaIncio = fechaIncio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(int saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public int getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(int saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<PresupuestoCategoria> getPresupuestoCategoriaList() {
        return presupuestoCategoriaList;
    }

    public void setPresupuestoCategoriaList(List<PresupuestoCategoria> presupuestoCategoriaList) {
        this.presupuestoCategoriaList = presupuestoCategoriaList;
    }

    public List<Movimientos> getMovimientosList() {
        return movimientosList;
    }

    public void setMovimientosList(List<Movimientos> movimientosList) {
        this.movimientosList = movimientosList;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        return (idPresupuesto != null ? idPresupuesto.hashCode() : 0);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PresupuestoMensual)) {
            return false;
        }
        PresupuestoMensual other = (PresupuestoMensual) object;
        return this.idPresupuesto != null && this.idPresupuesto.equals(other.idPresupuesto);
    }

    @Override
    public String toString() {
        return "py.edu.ucom.entities.proyecto.PresupuestoMensual[ idPresupuesto=" + idPresupuesto + " ]";
    }

    public double getMontoPresupuestado() {
        return saldoInicial; // O el campo que desees usar
    }
}