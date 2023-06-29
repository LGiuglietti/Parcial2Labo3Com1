package Modulos;

import Exceptions.agregarException;

public interface  ImetodosGenericos <T>{ //estos metodos son solamente utilizados para el arreglo de cada clase qeu la implemente

    void agregar(T dato) throws agregarException;
    String contar();
    String listar();
    void eliminar(T dato);


}
