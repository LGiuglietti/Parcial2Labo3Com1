package Modulos;

import Exceptions.agregarException;
import org.json.JSONArray;
import org.json.JSONObject;

public class SistemaVirtual {
    public SistemaVirtual() {
    }

    public void menu() {
        Estudiante juan = new Estudiante("juan", "juanson", "12/12/12", 1, "juanjuanson@juanmail.com");
        Materia mat = new Materia("matematica", "susi");
        Materia pro = new Materia("programacion III", "gonza");
        try {
            juan.agregar(mat);
        } catch (agregarException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            juan.agregar(pro);
        } catch (agregarException e) {
            System.out.println(e.getMessage());
        }
        try {//repetido
            juan.agregar(pro);
        } catch (agregarException e) {
            System.out.println(e.getMessage());
        }

        Nota miNota=new Nota(6,"matematica","45/3/85");
        Nota miNota2=new Nota(8,"programacion III","15/6/85");
        Nota miNota3=new Nota(2,"matematica","3/5/85");

        juan.agregarNota(miNota,"matematica");
        juan.agregarNota(miNota2,"programacion III");
        juan.agregarNota(miNota3,"matematica");


        System.out.println(juan.listar());

        JSONObject jso=juan.toJSON();
        JsonUtiles jsu=new JsonUtiles();
        jsu.grabar(jso,"archivo");







    }
}
