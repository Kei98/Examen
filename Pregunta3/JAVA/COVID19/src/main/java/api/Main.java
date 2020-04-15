package api;

import static api.JsonUtil.json;

import entities.COVID_19;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import static spark.Spark.*;
import java.util.Date;

/**
 *
 * @author kei98
 */

public class Main {
@SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    public static void main(String[] args) {

        port(8080);
        Service service = new Service();
   
        // POST - Inserta un registro de COVID19 en la BD
        post("/covid19", (request, response) -> {
            
            DateFormat format = new SimpleDateFormat("YYYY-MM-dd");
            Date fecha = new Date();
            String cCon = request.queryParams("casos_confirmados");
            String rec = request.queryParams("recuperados");
            String desc = request.queryParams("descartados");
            String cCri = request.queryParams("casos_criticos");
            String f = request.queryParams("fallecidos");
            String cAf = request.queryParams("cantones_afectados");
            String tM = request.queryParams("total_mujeres");
            String tH = request.queryParams("total_hombres");

            try {
                COVID_19 c = new COVID_19(0, format.format(fecha) , Integer.parseInt(cCon), 
                        Integer.parseInt(rec), Integer.parseInt(desc), Integer.parseInt(cCri), 
                        Integer.parseInt(f), Integer.parseInt(cAf), Integer.parseInt(tM), Integer.parseInt(tH));

                return service.postRegistro(c);
            } catch (NumberFormatException nfe) {
                return "Id no válido";
            }
        });

        //GET - Devuelve todos los registros de la BD
        get("/covid19", (request, response) -> {
            return service.getRegistros();
        }, json());

        //GET - Devuelve un registro por el número de id
        get("/covid19/:id", (request, response) -> {
            String id = request.params(":id");

            try {
                return service.getRegistro(Integer.parseInt(id));
            } catch (NumberFormatException nfe) {
                return "Id no válido";
            }
        }, json());

        // PUT - Actualiza una ciudad por el número de id
        put("/covid19/:id", (request, response) -> {
            String id = request.params(":id");
            String fecha = request.queryParams("fecha");
            String cCon = request.queryParams("casos_confirmados");
            String rec = request.queryParams("recuperados");
            String desc = request.queryParams("descartados");
            String cCri = request.queryParams("casos_criticos");
            String f = request.queryParams("fallecidos");
            String cAf = request.queryParams("cantones_afectados");
            String tM = request.queryParams("total_mujeres");
            String tH = request.queryParams("total_hombres");
            

            try {
                COVID_19 registro = new COVID_19(fecha, Integer.parseInt(cCon),
                        Integer.parseInt(rec), Integer.parseInt(desc), Integer.parseInt(cCri), 
                        Integer.parseInt(f), Integer.parseInt(cAf), Integer.parseInt(tM), Integer.parseInt(tH));
                registro.setId(Integer.parseInt(id));

                return service.putRegistro(registro);
            } catch (NumberFormatException nfe) {
                return "Id no válido";
            }
        });

        //DELETE - Elimina una ciudad por el número de id
        delete("/covid19/:id", (request, response) -> {
            String id = request.params(":id");

            try {
                return service.deleteRegistro(Integer.parseInt(id));
            } catch (NumberFormatException nfe) {
                return "Id no válido";
            }
        });

        //Authetication
        before((request, response) -> {
            String method = request.requestMethod();
            if (method.equals("POST") || method.equals("PUT") || method.equals("DELETE")) {
                String authentication = request.headers("Authentication");
                if (!"CastroCarazo".equals(authentication)) {
                    halt(401, "User Unauthorized");
                }
            }
        });

        //CORS
        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
        });

    }

}

