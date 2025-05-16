package estrategia;

import modelo.DefinicionAtributo.Tipo;

/**
 * Estrategia de validación para atributos de tipo String.
 */
public class ValidacionString implements EstrategiaValidacion {

    @Override
    public boolean validar(String nombre, Object valor, Tipo tipo, boolean unico) {
        if (tipo != Tipo.STRING) {
            return false; // No corresponde al tipo String
        }
        if (!(valor instanceof String)) {
            return false;
        }
        String texto = (String) valor;
        // Validación básica: no vacío
        if (texto.trim().isEmpty()) {
            return false;
        }
        // La unicidad se comprob­ará en el controlador o DAO
        return true;
    }
}
