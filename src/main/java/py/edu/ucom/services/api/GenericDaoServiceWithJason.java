package py.edu.ucom.services.api;

import jakarta.enterprise.context.ApplicationScoped;
import py.edu.ucom.entities.apiresponse.Gastos;
import py.edu.ucom.repository.ApiResponseRepository;
import py.edu.ucom.config.GenericDAO;

import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class GenericDaoServiceWithJason implements GenericDAO<Gastos, Integer> {

    public ApiResponseRepository repository;

    public GenericDaoServiceWithJason(ApiResponseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Gastos> listar() {
        return this.repository.listar();
    }

    @Override
    public Gastos obtener(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Gastos modificar(Gastos param) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Gastos agregar(Gastos param) {
        return this.repository.agregar(param);
    }

    @Override
    public void eliminar(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Gastos mayorGasto() {
        List<Gastos> lista = this.repository.listar();
        BigDecimal max = BigDecimal.ZERO;
        Integer id = 0;
        for (Gastos elem : lista) {
            if (max.compareTo(elem.getMonto()) < 0) {
                max = elem.getMonto();
                id = elem.getId();
            }
        }
        return this.repository.obtenerById(id).orElse(null);
    }


}
