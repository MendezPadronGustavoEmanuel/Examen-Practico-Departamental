package estrategia;

import modelo.DefinicionAtributo.Tipo;

/**
 * Interfaz para validación de atributos.
 */
public interface EstrategiaValidacion {
    /**
     * Valida un valor dado según la definición de atributo.
     *
     * @param nombre   nombre del atributo
     * @param valor    valor a validar (String o Integer)
     * @param tipo     tipo esperado (STRING o INTEGER)
     * @param unico    indica si debe ser único (se gestiona externamente)
     * @return true si es válido, false en caso contrario
     */
    boolean validar(String nombre, Object valor, Tipo tipo, boolean unico);
}
