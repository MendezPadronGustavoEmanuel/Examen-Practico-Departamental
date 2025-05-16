package dao;

import modelo.DefinicionAtributo;
import modelo.Entidad;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DAO JSON: persiste definiciones y entidades en archivos JSON.
 * Implementa Singleton para un único acceso al archivo.
 */
public class JsonDAO implements DAO<Entidad> {

    private static JsonDAO instancia;
    private static final String ARCH_DEF = "atributos.json";
    private static final String ARCH_ENT = "entidades.json";

    private List<DefinicionAtributo> definiciones;
    private List<Entidad> entidades;
    private ObjectMapper mapper;

    private JsonDAO() {
        mapper = new ObjectMapper();
        cargarDatos();
    }

    public static synchronized JsonDAO getInstancia() {
        if (instancia == null) {
            instancia = new JsonDAO();
        }
        return instancia;
    }

    private void cargarDatos() {
        try {
            File fDef = Paths.get(ARCH_DEF).toFile();
            if (fDef.exists()) {
                definiciones = mapper.readValue(fDef, new TypeReference<List<DefinicionAtributo>>() {});
            } else {
                definiciones = new ArrayList<>();
            }
            File fEnt = Paths.get(ARCH_ENT).toFile();
            if (fEnt.exists()) {
                entidades = mapper.readValue(fEnt, new TypeReference<List<Entidad>>() {});
            } else {
                entidades = new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
            definiciones = new ArrayList<>();
            entidades = new ArrayList<>();
        }
    }

    private void guardarDatos() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                  .writeValue(Paths.get(ARCH_DEF).toFile(), definiciones);
            mapper.writerWithDefaultPrettyPrinter()
                  .writeValue(Paths.get(ARCH_ENT).toFile(), entidades);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Métodos para definiciones de atributos
    public List<DefinicionAtributo> listarDefiniciones() {
        return new ArrayList<>(definiciones);
    }

    /** Reemplaza por completo la lista de definiciones y guarda en disco */
    public void reemplazarDefiniciones(List<DefinicionAtributo> nuevasDefs) {
        this.definiciones = new ArrayList<>(nuevasDefs);
        guardarDatos();
    }

    // Implementación DAO<Entidad>
    @Override
    public void crear(Entidad obj) throws Exception {
        entidades.add(obj);
        guardarDatos();
    }

    @Override
    public List<Entidad> listar() throws Exception {
        return new ArrayList<>(entidades);
    }

    @Override
    public Optional<Entidad> buscar(java.util.function.Predicate<Entidad> predicado) throws Exception {
        return entidades.stream().filter(predicado).findFirst();
    }

    @Override
    public void actualizar(int indice, Entidad obj) throws Exception {
        entidades.set(indice, obj);
        guardarDatos();
    }

    @Override
    public void eliminar(Entidad obj) throws Exception {
        entidades.remove(obj);
        guardarDatos();
    }
}
