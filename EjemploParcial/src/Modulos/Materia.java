package Modulos;

import Exceptions.agregarException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class Materia implements ImetodosGenericos<Nota> {
    private final String nombre;
    private final String docente;
    private final ArrayList<String> diasDeCursada;

    private final HashSet<Nota> notas;
    private int tope;

    public Materia(String nombre, String docente) {
        this.nombre = nombre;
        this.docente = docente;
        this.diasDeCursada=new ArrayList<>();
        this.diasDeCursada.add("Lunes");
        this.diasDeCursada.add("Martes");
        this.diasDeCursada.add("Miercoles");
        this.diasDeCursada.add("Jueves");
        this.diasDeCursada.add("Viernes");
        this.notas = new HashSet<>();
        this.tope=0;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDocente() {
        return docente;
    }

    public ArrayList<String> getDiasDeCursada() {
        return diasDeCursada;
    }

    public HashSet<Nota> getNotas() {
        return notas;
    }

    public int getTope() {
        return tope;
    }

    /*
            metodos que utiliza el arreglo notas
             */
    @Override
    public void agregar(Nota dato) throws agregarException {
        if(tope<4) {
            if (notas.contains(dato)) {
                for(Nota a: notas)
                {
                    if(a.equals(dato))
                    {
                        throw new agregarException("error! nota de instancia ya existe (resultado de parcial= "+a.mostrarUnaNota()+")");
                    }
                    if(a.getFecha().equals(dato.getFecha()))
                    {
                        throw new agregarException("error! nota de instancia ya existe (resultado de parcial= "+a.mostrarUnaNota()+")");
                    }
                }
            } else{
                notas.add(dato);
                this.tope++;
            }
        }
        else {
            throw new agregarException("error ya rindio cuatro parciales");//recu y parcial
        }
    }

    @Override
    public String contar() {
        String res= "notas :";
        res+=this.notas.size();
        return res;
    }

    @Override
    public String listar() {
        String res= "notas: ";
        for (Nota a: notas)
        {
            res+=a.getNombre()+", puntaje: "+a.getNumero()+"y fecha: "+a.getFecha()+"\n";
        }
        return res;
    }

    @Override
    public void eliminar(Nota dato) {
        notas.remove(dato);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Materia materia = (Materia) o;
        return Objects.equals(nombre, materia.nombre) && Objects.equals(docente, materia.docente) && Objects.equals(diasDeCursada, materia.diasDeCursada) && Objects.equals(notas, materia.notas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, docente, diasDeCursada, notas);
    }

    public JSONArray notaToJSON()
    {
        JSONObject jsonObject=new JSONObject();
        JSONArray jsonArray=new JSONArray();
        int i=1;

        for (Nota a: notas)
        {
            String key=" -Nota"+i;
            try {
                jsonObject.put(key,a);
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            i++;
        }

        return jsonArray;
    }

    @Override
    public String toString() {
        return "Materia{" +
                "nombre='" + nombre + '\'' +
                ", docente='" + docente + '\'' +
                ", diasDeCursada=" + diasDeCursada +
                ", notas=" + notas +
                ", tope=" + tope +
                '}';
    }
}