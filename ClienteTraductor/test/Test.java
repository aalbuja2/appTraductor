
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ec.bancopichincha.modelo.HistorialTraductor;
import ec.bancopichincha.utils.Global;
import java.lang.reflect.Type;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author roni9
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(Global.DOMINIO);
        Response respuesta = target.request(MediaType.APPLICATION_JSON).get();
        String objJson = respuesta.readEntity(String.class);
        Type listType = new TypeToken<List<HistorialTraductor>>() {}.getType();
        List<HistorialTraductor> yourList = new Gson().fromJson(objJson, listType);

    }

}
