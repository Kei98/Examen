package data;

import entities.COVID_19;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kei98
 */
public class DatabaseManager {

    private final String host = "localhost:3306";//Contiene el host de la BD.
    private final String database = "COVID_19";//Contiene el nombre de la BD.
    private final String url = "jdbc:mysql://" + host + "/" + database + "?autoReconnect=true&useSSL=false";//Contiene la dirección de la BD.
    private final String USER = "root";//Contiene el usuario de la BD.
    private final String PASS = "GitKei98*";//Contiene el password de la BD.
    private final String DRIVER = "com.mysql.jdbc.Driver";//Contiene la dirección del Driver de MySQL.
    Connection connection = null;//Inicializa la variable conexión a la BD.
    Statement statement = null;//Inicializa la variable statement de la BD.
    ResultSet resultSet = null;//Inicializa la variable tipo ResultSet.

    /**
     * Abre la Base de Datos.
     */
    public void abrirBD() {

        try {

            Class.forName(DRIVER);
            connection = (Connection) DriverManager.getConnection(url, USER, PASS);//Crea la conexión a la BD.
            statement = (Statement) connection.createStatement();//Crea el Statement.

        }//Fin de try
        catch (Exception ex) {

            System.err.println(ex);

        }//Fin de catch

    }//Fin de clase abrirBD

    public void insertarRegistro(COVID_19 registro) {
        try {

            //Forma de insertar utilizando el Prepared Statement
            String sql = "INSERT INTO COVID19 VALUES (?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement;

            preparedStatement = (PreparedStatement) connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, registro.getId());
            preparedStatement.setString(2, registro.getFecha());
            preparedStatement.setInt(3, registro.getCasos_confirmados());
            preparedStatement.setInt(4, registro.getRecuperados());
            preparedStatement.setInt(5, registro.getDescartados());
            preparedStatement.setInt(6, registro.getCasos_criticos());
            preparedStatement.setInt(7, registro.getFallecidos());
            preparedStatement.setInt(8, registro.getCantones_afectados());
            preparedStatement.setInt(9, registro.getTotal_mujeres());
            preparedStatement.setInt(10, registro.getTotal_hombres());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } //Fin de try
        catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos " + ex);

        }//Fin de catch
    }
/**
 * actualiza los datos de forma inteligente, si no se le especifican, se toma como que no se desea actualizar
 * @param registro 
 */
    public void actualizarRegistro(COVID_19 registro) {
        try {

            //Forma de insertar utilizando el Prepared Statement
            int i = 0;
            String sql = "UPDATE COVID19 SET ";
            for (int j = 0; j < registro.noNulos().size()-1; j++) {
                sql += registro.noNulos().get(j) + " = ?, ";
            }
            sql += registro.noNulos().get(registro.noNulos().size()-1) + " = ? ";
            sql += "WHERE id = ?";
            
            PreparedStatement preparedStatement;
            
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            if(registro.getStrOcupado() == 1){
                preparedStatement.setString(++i, registro.getFecha());
            }
            for(int j = 1; j < registro.getData().size(); j++){
                preparedStatement.setInt(++i, registro.getData().get(j));
            }
            preparedStatement.setInt(++i, registro.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } //Fin de try
        catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos " + ex);

        }//Fin de catch
    }

    public void eliminarRegistro(int id) {

        try {

            String sql = "DELETE FROM COVID19 WHERE id=" + id;
            statement.execute(sql);

        } //Fin de try
        catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos");

        }//Fin de catch

    }

    public List<COVID_19> getRegistros() {
        List registros = new ArrayList();

        try {

            String sql = "SELECT * FROM COVID19";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                COVID_19 registro = new COVID_19();
                registro.setId(resultSet.getInt("id"));
                registro.setFecha(resultSet.getString("fecha"));
                registro.setCasos_confirmados(resultSet.getInt("casos_confirmados"));
                registro.setRecuperados(resultSet.getInt("recuperados"));
                registro.setDescartados(resultSet.getInt("descartados"));
                registro.setCasos_criticos(resultSet.getInt("casos_criticos"));
                registro.setFallecidos(resultSet.getInt("fallecidos"));
                registro.setCantones_afectados(resultSet.getInt("cantones_afectados"));
                registro.setTotal_mujeres(resultSet.getInt("total_mujeres"));
                registro.setTotal_hombres(resultSet.getInt("total_hombres"));
                
                registros.add(registro);

            }

        } //Fin de try //Fin de try
        catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos");

        }//Fin de catch

        return registros;
    }

    public COVID_19 getRegistro(int id) {
        COVID_19 registro = null;

        try {
            String sql = "SELECT * FROM COVID19 WHERE id=" + id;
            resultSet = statement.executeQuery(sql);

            if (resultSet.first()) {

                registro = new COVID_19();
                
                registro.setId(resultSet.getInt(1));
                registro.setFecha(resultSet.getString(2));
                registro.setCasos_confirmados(resultSet.getInt(3));
                registro.setRecuperados(resultSet.getInt(4));
                registro.setDescartados(resultSet.getInt(5));
                registro.setCasos_criticos(resultSet.getInt(6));
                registro.setFallecidos(resultSet.getInt(7));
                registro.setCantones_afectados(resultSet.getInt(8));
                registro.setTotal_mujeres(resultSet.getInt(9));
                registro.setTotal_hombres(resultSet.getInt(10));
                System.out.println(registro);
            }

        } //Fin de try
        catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos");

        }//Fin de catch

        return registro;
    }

    /**
     * Cierra la Base de Datos.
     */
    public void cerrarBD() {

        try {

            statement.close();//Cierra el Statement.
            connection.close();//Termina la conxión con la BD.

        } //Fin de try
        catch (SQLException ex) {

            System.err.println("Hubo un error al cerrar la Base de Datos");

        }//Fin de catch

    }//Fin del método cerrarBD

}//Fin de clase
