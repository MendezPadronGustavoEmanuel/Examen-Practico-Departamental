package modelo;

import java.util.LinkedHashMap;
import java.util.Map;

/** 
 * Representación genérica de una entidad con atributos dinámicos 
 */
public class Entidad {
    private Map<String, Object> atributos;

    public Entidad() {
        this.atributos = new LinkedHashMap<>();
    }

    /**
     * Asigna un valor a un atributo
     * @param nombre nombre del atributo
     * @param valor  valor (String o Integer)
     */
    public void setAtributo(String nombre, Object valor) {
        atributos.put(nombre, valor);
    }

    /**
     * Obtiene el valor de un atributo
     * @param nombre nombre del atributo
     * @return valor asociado o null
     */
    public Object getAtributo(String nombre) {
        return atributos.get(nombre);
    }

    /**
     * Devuelve el mapa completo de atributos
     * @return mapa nombre→valor
     */
    public Map<String, Object> getAtributos() {
        return atributos;
    }

    @Override
    public String toString() {
        return "Entidad{" + "atributos=" + atributos + '}';
    }
}
