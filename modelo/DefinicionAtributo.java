package modelo;

public class DefinicionAtributo {
    public enum Tipo { STRING, INTEGER }

    private String nombre;
    private Tipo tipo;
    private boolean unico;

    /** Constructor vacío requerido por Jackson */
    public DefinicionAtributo() { }

    /** Constructor que tú usas en el código */
    public DefinicionAtributo(String nombre, Tipo tipo, boolean unico) {
        this.nombre = nombre;
        this.tipo   = tipo;
        this.unico  = unico;
    }

    // Getters
    public String getNombre()      { return nombre; }
    public Tipo   getTipo()        { return tipo; }
    public boolean isUnico()       { return unico; }

    // Setters necesarios para Jackson
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setUnico(boolean unico) {
        this.unico = unico;
    }
}
