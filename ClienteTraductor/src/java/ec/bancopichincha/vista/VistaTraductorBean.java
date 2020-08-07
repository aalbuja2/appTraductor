/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.bancopichincha.vista;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ec.bancopichincha.modelo.HistorialTraductor;
import ec.bancopichincha.utils.Global;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.primefaces.context.RequestContext;

/**
 *
 * @author roni9
 */
@ManagedBean(name = "vistaTraductorBean")
@ViewScoped
public class VistaTraductorBean {

    List<HistorialTraductor> listaHistorial;

    /**
     * Creates a new instance of VistaTraductorBean
     */
    @PostConstruct
    public void init() {

    }

    public VistaTraductorBean() {

    }

    // Método para consumir el servicio web que tome los mensajes antes traducidos
    public void visualizarHistorial() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(Global.DOMINIO);
        Response respuesta = target.request(MediaType.APPLICATION_JSON).get();
        String objJson = respuesta.readEntity(String.class);
        Type listType = new TypeToken<List<HistorialTraductor>>() {
        }.getType();
        listaHistorial = new Gson().fromJson(objJson, listType);
        RequestContext.getCurrentInstance().update("contenedor");
    }

    // Método par consumir el servicio web par que traduzca que inserte en la base de datos.
    public void traducirFrase() {
        Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(Global.DOMINIO);
        Response respuesta = target.request(MediaType.APPLICATION_JSON).post(Entity.json(map.get("texto")));
        String objJson = respuesta.readEntity(String.class);
        HistorialTraductor histRes = new Gson().fromJson(objJson, HistorialTraductor.class);
        RequestContext.getCurrentInstance().execute("document.getElementById('traduccion').text('" + histRes.getTraduccion() + "');");

    }

    public List<HistorialTraductor> getListaHistorial() {
        return listaHistorial;
    }

    public void setListaHistorial(List<HistorialTraductor> listaHistorial) {
        this.listaHistorial = listaHistorial;
    }

}
