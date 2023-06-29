package Modulos;

import Exceptions.agregarException;
import org.json.JSONObject;

import java.util.Objects;

public class Nota{
    private final int numero;
    private final String nombre;
    private final String fecha;


    public Nota(int numero, String nombre, String fecha) {
        this.numero = numero;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public int getNumero() {
        return numero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nota nota = (Nota) o;
        return numero == nota.numero && Objects.equals(nombre, nota.nombre) && Objects.equals(fecha, nota.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, nombre, fecha);
    }
    public String mostrarUnaNota()
    {
        return "Nota: "+getNumero()+", materia: "+getNombre()+",fecha: "+getFecha();
    }

}
