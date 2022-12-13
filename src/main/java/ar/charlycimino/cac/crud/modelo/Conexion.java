package ar.charlycimino.cac.crud.modelo;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Charly Cimino Aprendé más Java en mi canal:
 * https://www.youtube.com/c/CharlyCimino Encontrá más código en mi repo de
 * GitHub: https://github.com/CharlyCimino
 */
public class Conexion {

    private static final String URL_DB = "jdbc:mysql://root:root@localhost:3306/cac_crud_bd_22552?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
//    private static Connection con;
    private static BasicDataSource dataSource;

    private Conexion() {
    }

    public static DataSource getDataSource() {
        if (dataSource == null) {
            try {
//               Class.forName("com.mysql.jdbc.Driver");
                dataSource = new BasicDataSource();
                dataSource.setUrl(URL_DB);
                dataSource.setInitialSize(50);
                System.out.println("Conectado");

            } catch (Exception ex) {
                System.out.println("Error de conexion");
                throw new RuntimeException("Error de E/S al leer config de BBDD", ex);
            }
        }
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }
}
