package py.edu.ucom.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import py.edu.ucom.entities.proyecto.PresupuestoMensual;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class PresupuestoMensualRepository {

    private List<PresupuestoMensual> presupuestos = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger();

    @PersistenceContext
    //private EntityManager entityManager;

    @Transactional
    public PresupuestoMensual crearPresupuesto(PresupuestoMensual presupuestoMensual) {
        // Persistir el objeto en la base de datos
        entityManager.persist(presupuestoMensual);
        return presupuestoMensual;
    }

    // Obtener un presupuesto por ID
    public Optional<PresupuestoMensual> obtenerById(Integer id) {
        return presupuestos.stream()
                .filter(p -> p.getIdPresupuesto().equals(id))
                .findFirst();
    }

    @PersistenceContext
    private EntityManager entityManager;

    // Listar todos los presupuestos
    public List<PresupuestoMensual> listar() {
        TypedQuery<PresupuestoMensual> query = entityManager.createQuery("SELECT p FROM PresupuestoMensual p", PresupuestoMensual.class);
        return query.getResultList();
    }

    public long contar() {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(p) FROM PresupuestoMensual p", Long.class);
        return query.getSingleResult();
    }



    // Eliminar un presupuesto
    public void eliminar(PresupuestoMensual presupuestoMensual) {
        presupuestos.removeIf(p -> p.getIdPresupuesto().equals(presupuestoMensual.getIdPresupuesto()));
    }
    public List<PresupuestoMensual> obtenerPresupuestosConMayorMonto() {
        TypedQuery<PresupuestoMensual> query = entityManager.createQuery(
                "SELECT p FROM PresupuestoMensual p WHERE p.saldoInicial = (SELECT MAX(p2.saldoInicial) FROM PresupuestoMensual p2)",
                PresupuestoMensual.class);
        return query.getResultList();
    }


    public List<PresupuestoMensual> filtrarPorRangoMontos(int rangoInicial, int rangoFinal) {
        TypedQuery<PresupuestoMensual> query = entityManager.createQuery(
                "SELECT p FROM PresupuestoMensual p WHERE p.saldoInicial BETWEEN :rangoInicial AND :rangoFinal",
                PresupuestoMensual.class);
        query.setParameter("rangoInicial", rangoInicial);
        query.setParameter("rangoFinal", rangoFinal);
        return query.getResultList();
    }
}

