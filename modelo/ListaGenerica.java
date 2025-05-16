package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Lista genérica con operaciones CRUD básicas
 */
public class ListaGenerica<T> {
    protected List<T> elementos;

    public ListaGenerica() {
        this.elementos = new ArrayList<>();
    }

    public void agregar(T item) {
        elementos.add(item);
    }

    public boolean eliminar(T item) {
        return elementos.remove(item);
    }

    public List<T> listar() {
        return new ArrayList<>(elementos);
    }

    /**
     * Busca un elemento que cumpla el predicado
     */
    public Optional<T> buscar(java.util.function.Predicate<T> predicado) {
        return elementos.stream().filter(predicado).findFirst();
    }

    public void actualizar(int indice, T nuevo) {
        elementos.set(indice, nuevo);
    }
}