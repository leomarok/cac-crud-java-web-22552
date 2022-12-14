
package ar.charlycimino.cac.crud.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Charly Cimino
 * Aprendé más Java en mi canal: https://www.youtube.com/c/CharlyCimino
 * Encontrá más código en mi repo de GitHub: https://github.com/CharlyCimino
 */
public class ModeloMySQL implements Modelo {
    
    private static final String GET_ALL_QUERY = "SELECT * FROM alumnos ORDER BY nombre";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM alumnos WHERE id = ?";
    
    @Override
    public List<Alumno> getAlumnos() {
        List<Alumno> lista = new ArrayList<>();
        
        try (Connection con = Conexion.getConnection();  
            PreparedStatement ps = con.prepareStatement(GET_ALL_QUERY);
            ResultSet rs = ps.executeQuery();) {         
            System.out.println("Conectado!!");
            while(rs.next()) {
                lista.add( rsToAlumno(rs) );
            }         
        } catch(SQLException e) {
            throw new RuntimeException("Error de SQL", e);
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener lista de alumnos", ex);
        }       
        return lista;
    }

    @Override
    public Alumno getAlumno(int id) {
        Alumno alumno = null;
        try (Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(GET_BY_ID_QUERY);
            ) {            
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery();) {
                rs.next();
                alumno = rsToAlumno(rs);
            }    
        } catch(SQLException e) {
            throw new RuntimeException("Error de SQL", e);
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener alumno por ID", ex);
        }
        
        return alumno;
    }

    @Override
    public int addAlumno(Alumno alumno) {
        return 0;
    }

    @Override
    public int updateAlumno(Alumno alumno) {
        return 0;
    }

    @Override
    public int removeAlumno(int id) {
        return 0;
    }
    
    private Alumno rsToAlumno(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");
        String mail = rs.getString("mail");
        String fechaNac = rs.getString("fechaNac");
        String fotoBase64 = rs.getString("fotoBase64");
        return new Alumno(id, nombre, apellido, mail, fechaNac, fotoBase64);
    }
    
}
