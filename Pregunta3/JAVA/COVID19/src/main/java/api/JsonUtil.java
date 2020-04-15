package api;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 *
 * @author Esteban Ramírez Martinez
 */
public class JsonUtil {
 
  public static String toJson(Object object) {
    return new Gson().toJson(object);
  }
 
  public static ResponseTransformer json() {
    return JsonUtil::toJson;
  }
}