/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.bancopichincha.controlador;

import ec.bancopichincha.servicio.HistorialTraductorServicio;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Según las especificaciones el ,étodo get lista los mensajes antes traducidos 
 * El método post traduce un mensaje y guarda en la base de datos.
 * @author roni9
 */
@Path("traductor")
public class WSTraductor {

    HistorialTraductorServicio servicio = new HistorialTraductorServicio();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerListaHistorial() {
        return servicio.obtenerHitorialTraduccion();
    }

    @POST
    @Path("/{frase}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response traducirOracion(@PathParam("frase") String frase) {
        return servicio.traducirPalabra(frase);
    }
}
