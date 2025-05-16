package dao;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz genérica CRUD para acceso a datos.
 *
 * @param <T> tipo de entidad
 */
public interface DAO<T> {
    void crear(T obj) throws Exception;
    List<T> listar() throws Exception;
    Optional<T> buscar(java.util.function.Predicate<T> predicado) throws Exception;
    void actualizar(int indice, T obj) throws Exception;
    void eliminar(T obj) throws Exception;
}
