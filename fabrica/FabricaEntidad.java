package fabrica;

import modelo.Entidad;
import modelo.DefinicionAtributo;
import java.util.List;
import java.util.Map;

/**
 * Factory Method para crear objetos Entidad con atributos inicializados.
 */
public class FabricaEntidad {

    /**
     * Crea una nueva Entidad vacía y configura sus atributos (con valores por defecto).
     *
     * @param definiciones lista de definiciones de atributos
     * @return nueva Entidad con todos los atributos creados (valor null)
     */
    public static Entidad crearEntidad(List<DefinicionAtributo> definiciones) {
        Entidad e = new Entidad();
        for (DefinicionAtributo def : definiciones) {
            // Inicializa con null o un valor por defecto
            switch (def.getTipo()) {
                case STRING:
                    e.setAtributo(def.getNombre(), null);
                    break;
                case INTEGER:
                    e.setAtributo(def.getNombre(), 0);
                    break;
            }
        }
        return e;
    }

    /**
     * Clona una Entidad existente (copia profunda de atributos).
     *
     * @param original Entidad a clonar
     * @return copia de la Entidad
     */
    public static Entidad clonarEntidad(Entidad original) {
        Entidad copia = new Entidad();
        for (Map.Entry<String, Object> entry : original.getAtributos().entrySet()) {
            copia.setAtributo(entry.getKey(), entry.getValue());
        }
        return copia;
    }
}
