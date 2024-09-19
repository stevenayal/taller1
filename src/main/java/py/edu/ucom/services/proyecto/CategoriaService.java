package py.edu.ucom.services.proyecto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import py.edu.ucom.config.GenericDAO;
import py.edu.ucom.entities.proyecto.Categoria;
import py.edu.ucom.repository.proyecto.CategoriaRepository;

import java.util.List;

@ApplicationScoped
public class CategoriaService implements GenericDAO<Categoria, Integer> {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Categoria> listar() {
        return this.repository.findAll().list();
    }

    @Override
    public Categoria obtener(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        this.repository.deleteById(id);
    }

    @Override
    @Transactional
    public Categoria modificar(Categoria param) {

        return this.repository.getEntityManager().merge(param);
    }

    @Override
    @Transactional
    public Categoria agregar(Categoria param) {
        this.repository.persist(param);
        return null;
    }

}
