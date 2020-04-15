package api;

import data.DatabaseManager;
import entities.COVID_19;
import java.util.List;

/**
 *
 * @author kei98
 */
public class Service {
    DatabaseManager controlador = new DatabaseManager();
    
    public List<COVID_19> getRegistros() {
        controlador.abrirBD();
        List<COVID_19> registros = controlador.getRegistros();
        controlador.cerrarBD();
        return registros;
    }

    public COVID_19 getRegistro(int id) {
        controlador.abrirBD();
        COVID_19 registro = controlador.getRegistro(id);
        controlador.cerrarBD();
        return registro;
    }
    
    public String postRegistro(COVID_19 registro) {
        controlador.abrirBD();
        COVID_19 consulta = controlador.getRegistro(registro.getId());
        controlador.cerrarBD();

        if (consulta == null) {
            controlador.abrirBD();
            controlador.insertarRegistro(registro);
            controlador.cerrarBD();
            return "Registro con el id " + registro.getId()+ " ha sido creado!";
        } else {            
            return "El registro ya existe";
        }
    }

    public String putRegistro(COVID_19 registro) {
        controlador.abrirBD();
        COVID_19 consulta = controlador.getRegistro(registro.getId());
        controlador.cerrarBD();

        if (consulta != null) {
            controlador.abrirBD();
            controlador.actualizarRegistro(registro);
            controlador.cerrarBD();
            return "Registro con el id " + registro.getId()+ " ha sido actualizado!";
        } else {
            
            return "Registro no encontrado";
        }
    }
    
    public String deleteRegistro(int id) {

        controlador.abrirBD();
        COVID_19 registro = controlador.getRegistro(id);
        controlador.cerrarBD();

        if (registro != null) {
            controlador.abrirBD();
            controlador.eliminarRegistro(id);
            controlador.cerrarBD();
            return "Registro con el id " + id + " ha sido eliminado!";
        } else {
            return "Registro no encontrado";
        }
    }
    
}
