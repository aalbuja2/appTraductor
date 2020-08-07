/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.bancopichincha.dao;

import ec.bancopichincha.generica.DefaultGenericDAOImple;
import ec.bancopichincha.modelo.HistorialTraductor;

/**
 *
 * @author roni9
 */
public class HistorialTraductorDao extends DefaultGenericDAOImple<HistorialTraductor, Integer> {

    public HistorialTraductorDao() {
        super(HistorialTraductor.class);
    }
}
