package ModeloBD_DAO;

import java.util.ArrayList;

public interface PatronDAO<T> {

    public Object insertar(T t); // Insertar un registro (del tipo que sea)

    public boolean borrar(Object pk); // Eliminar un registro referenciado por su PK

    public boolean actualizar(T t); // Actualizar un registro

    public T buscar(Object pk); // Devuelve el registro cuya PK se le pasa

    public ArrayList<T> listarTodos();// Devuelve la lista de todos los registros de la tabla
}
