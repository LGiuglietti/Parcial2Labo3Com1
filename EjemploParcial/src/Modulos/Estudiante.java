package Modulos;

import Exceptions.MateriaNotFound;
import Exceptions.agregarException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Iterator;

public class  Estudiante implements ImetodosGenericos<Materia>{
    private final String nombre;
    private final String apellido;
    private final String fechaNacimiento;
    private final int legajo;
    private final String mail;
    private final HashSet<Materia> materias;

    public Estudiante(String nombre, String apellido, String fechaNacimiento, int legajo, String mail) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.legajo = legajo;
        this.mail = mail;
        this.materias = new HashSet<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getLegajo() {
        return legajo;
    }

    public String getMail() {
        return mail;
    }

    /*
    metodos que utiliza el arreglo materias
     */

    @Override
    public void agregar(Materia dato) throws agregarException {
        if (this.materias.contains(dato)){
            throw new agregarException("materia ya en lista");
        }
        else
        {
            materias.add(dato);
        }
    }

    @Override
    public String contar() {
        return "el alumno "+getNombre()+" "+getApellido()+"cursa "+materias.size()+" materias";
    }

    @Override
    public String listar() {
        String res= "listado de materias: ";
        for (Materia aux: materias)
        {
            res+=aux.getNombre()+",tope: "+aux.getTope()+",docente "+aux.getDocente()+"\n";
            for (Nota n: aux.getNotas())
            {
                res+=n.mostrarUnaNota();
            }
        }
        return res;
    }

    @Override
    public void eliminar(Materia dato) {
        materias.remove(dato);
    }
    public void agregarNota(Nota aux,String materia)
    {
        for (Materia a : materias) {
            if (a.getNombre().equals(materia)) {
                try {
                    a.agregar(aux);
                } catch (agregarException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    public JSONArray materiaToJSON()
    {
        JSONObject jsonObject=new JSONObject();
        JSONArray jsonArray=new JSONArray();
        for (Materia a: materias)
        {
            try {
                jsonObject.put(a.getNombre(),a.notaToJSON());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }

    public JSONObject toJSON()
    {
        JSONArray materiaArray=new JSONArray();
        for (Materia m: materias)
        {
            JSONArray notasArray=new JSONArray();
            int i=1;
            JSONObject materiaObj=new JSONObject();
            for (Nota n: m.getNotas()) //arreglo de notas
            {
                String llave=new String("nota"+i);
                JSONObject notaobj=new JSONObject();
                try {
                    notaobj.put(llave,n.mostrarUnaNota());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                notasArray.put(notaobj);
                i++;
            }
            String llave=new String(m.getNombre());
            try {
                materiaObj.put(llave,m.toString());
                materiaObj.put("notas: ",notasArray);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            materiaArray.put(materiaObj);
        }
        JSONObject juanObj=new JSONObject();
        try {
            juanObj.put("nombre",getNombre());
            juanObj.put("apellido",getApellido());
            juanObj.put("legajo",getLegajo());
            juanObj.put("materias",materiaArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return juanObj;
    }

}
