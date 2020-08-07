/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.bancopichincha.servicio;

import apitraductor.ApiTraductor;
import com.google.gson.Gson;
import ec.bancopichincha.dao.HistorialTraductorDao;
import ec.bancopichincha.modelo.HistorialTraductor;
import ec.bancopichincha.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author roni9
 */
public class HistorialTraductorServicio {

    public static HistorialTraductorDao dao = new HistorialTraductorDao();

    public Response traducirPalabra(String texto) {
        Session sesion = HibernateUtil.getSesionModulo().openSession();
        Transaction transaccion = sesion.beginTransaction();
        Response respuesta = null;
        HistorialTraductor objTraductor = null;
        try {
            objTraductor = dao.insert(consumirApiTraductor(texto), sesion);
            transaccion.commit();
        } catch (HibernateException ex) {
            respuesta = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex).build();
            Logger.getLogger(HistorialTraductorServicio.class.getName()).log(Level.ERROR, null, ex);
        } finally {
            sesion.flush();
            sesion.close();
        }
        if (respuesta == null) {
            respuesta = Response.ok().entity(objTraductor).build();
        }
        return respuesta;
    }

    public Response obtenerHitorialTraduccion() {
        Session sesion = HibernateUtil.getSesionModulo().openSession();
        List<HistorialTraductor> lstTraducciones = new ArrayList<>();
        Response respuesta = null;
        try {
            lstTraducciones = dao.findAll(sesion);
        } catch (HibernateException ex) {
            respuesta = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex).build();
            Logger.getLogger(HistorialTraductorServicio.class.getName()).log(Level.ERROR, null, ex);
        } finally {
            sesion.flush();
            sesion.close();
        }
        if (respuesta == null) {
            respuesta = Response.ok().entity(new Gson().toJson(lstTraducciones)).build();
        }
        return respuesta;
    }

    public HistorialTraductor consumirApiTraductor(String texto) {
        ApiTraductor objApi = new ApiTraductor();
        return new HistorialTraductor(texto, objApi.traducirFrase(texto), objApi.detectarLenguage(texto));
    }
}
