
package ar.charlycimino.cac.crud.modelo;

import java.util.List;

/**
 *
 * @author Charly Cimino
 * Aprendé más Java en mi canal: https://www.youtube.com/c/CharlyCimino
 * Encontrá más código en mi repo de GitHub: https://github.com/CharlyCimino
 */
public interface Modelo {
    /**
     * Devuelve una lista de alumnos existentes
     * El Parametro order by indica si se ordenara por Nombre o Apellido
     * @param ListOrderby
     * @return 
     */
    public List<Alumno> getAlumnos(String ListOrderby);
    
    /**
     * Retorna un alumno por ID
     * @param id el id del alumno a retornar
     * @return El alumno encontrado por ID o null si no existe
     */
    public Alumno getAlumno(int id);
    
    /**
     * Agrega un alumno al modelo
     * @param alumno El alumno a agregar
     * @return La cantidad de registros modificados
     */
    public int addAlumno(Alumno alumno);
    
    /**
     * Modifica un alumno del modelo
     * @param alumno El alumno que contiene los datos para modificar
     * @return La cantidad de registros modificados
     */
    public int updateAlumno(Alumno alumno);
    
    /**
     * Borra un alumno por ID
     * @param id el id del alumno a borrar
     * @return La cantidad de registros modificados
     */
    public int removeAlumno(int id);
}
