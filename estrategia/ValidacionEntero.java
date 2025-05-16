package estrategia;

import modelo.DefinicionAtributo.Tipo;

/**
 * Estrategia de validación para atributos de tipo Integer.
 */
public class ValidacionEntero implements EstrategiaValidacion {

    @Override
    public boolean validar(String nombre, Object valor, Tipo tipo, boolean unico) {
        if (tipo != Tipo.INTEGER) {
            return false; // No corresponde al tipo Integer
        }
        if (!(valor instanceof Integer)) {
            return false;
        }
        Integer numero = (Integer) valor;
        // Validación básica: no es negativo
        return numero >= 0;
    }
}
