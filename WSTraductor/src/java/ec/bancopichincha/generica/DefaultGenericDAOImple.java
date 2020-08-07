package ec.bancopichincha.generica;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.hibernate.Query;
import org.hibernate.LockOptions;
import org.hibernate.Session;

/**
 * Implementa las operaciones b�sicas de acceso a datos DAO utilizando el API de
 * JPA.
 * <p>
 * Para escribir una subclase DAO, la cual implemente sus propios m�todos debe
 * asociarse el tipo de Entidad. Recuerde que deber� existir una clase DAO por
 * cada clase de Entidad.
 *
 * Este proyecto utiliza la implementaci�n de Hibernate para JPA.
 *
 * @param <T> Clase correspondiente al modelo (Clases Hibernate) que especifica
 * el tipo de objeto con el cual se van a realizar las operaciones de acceso a
 * datos.
 * @param <ID> Tipo de la Clave Primaria de la clase modelo, esta clase debe
 * extender de java.io.Serializable
 *
 */
public class DefaultGenericDAOImple<T, ID extends Serializable> implements
        GenericDAO<T, ID> {

    /**
     * Creaci�n del log de auditor�a.
     */
    private static final Logger LOGGER = Logger.getLogger(DefaultGenericDAOImple.class.getName());

    /**
     * La constante MENSAJE_ERROR_AUDITORIA con el mensaje de error para el
     * LOGGER cuando no se puede asignar la informaci�n de auditor�a.
     */
    public static final String MENSAJE_ERROR_AUDITORIA
            = "Error al asignar el objeto de auditoria: ";

    /**
     * Objeto que representa a la clase de modelo a la cual pertenece el DAO.
     */
    private Class<T> entityBeanType;

    private QueryBuilder<T> qryBuilder;

    /**
     * Constructor por defecto.
     */
    public DefaultGenericDAOImple() {
        //this.entityBeanType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Constructor por defecto.
     *
     * @param clase
     */
    @SuppressWarnings("unchecked")
    public DefaultGenericDAOImple(Class<T> clase) {
        this.qryBuilder = new QueryBuilder<>();
        this.entityBeanType = clase;
    }

    @PostConstruct
    public void init() {
        this.qryBuilder = new QueryBuilder<>();
    }

    /**
     * Retorna el tipo de Entidad a la que pertenece el DAO.
     *
     * @return Tipo de Entidad a la que pertenece el DAO.
     */
    public Class<T> getEntityBeanType() {
        return entityBeanType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T findById(ID id, boolean lock, Session sesion) {
        T entity;
        if (lock) {
            entity = (T) sesion.get(getEntityBeanType(), id);
            sesion.refresh(entity, LockOptions.UPGRADE);
        } else {
            entity = (T) sesion.get(getEntityBeanType(), id);
        }
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAll(Session sesion) {
        try {
            Query query = this.qryBuilder.buildQuery(0, sesion, this.entityBeanType.newInstance());
            return query.list();
        } catch (IllegalAccessException | InstantiationException ex) {
            LOGGER.log(Level.SEVERE, "Error al ejecutar sentencia all.", ex);
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T insert(T entity, Session sesion) {
        sesion.save(entity);
        return (T) entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T update(T entity, Session sesion) {
        return (T) sesion.merge(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void makeTransient(T entity, Session sesion) {
        sesion.delete(sesion.merge(entity));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(T entity, Session sesion) {
        sesion.delete(entity);
    }

    /**
     * Ejecuta la operaci�n flush definida en JPA.
     */
    @Override
    public void flush(Session sesion) {
        sesion.flush();
    }

    /**
     * Ejecuta la operación clear definida en JPA.
     */
    public void clear(Session sesion) {
        sesion.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> find(T entityExample, Session sesion, Boolean... maxRegistrosConsulta) {
        Query query = this.qryBuilder.buildQuery(0, sesion, entityExample);
        if (maxRegistrosConsulta.length > 0 && Boolean.TRUE.equals(maxRegistrosConsulta[0])) {
            //query.setMaxResults(2);
        }
        return query.list();
    }

    public List<T> findO(T entityExample, Session sesion, String... orden) {
        Query query = this.qryBuilder.buildQuery(0, sesion, entityExample, orden);
        return query.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer count(T entityExample, Session sesion) {
        Query query = this.qryBuilder.buildQuery(1, sesion, entityExample);
        return ((Long) query.uniqueResult()).intValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Object> ejecutarNativo(String sentencia, Session sesion) throws Exception {
        try {
            Query query = sesion.createSQLQuery(sentencia);
            List<Object> lista = query.list();
            return lista;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al ejecutar sentencia", e);
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int ejecutarUpdateNativo(String sentencia, Session sesion) throws Exception {
        try {
            Query query = sesion.createSQLQuery(sentencia);
            return query.executeUpdate();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al ejecutar sentencia", e);
            return 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T bloquearEscritura(T entidad, Session sesion) {
        return (T) sesion.merge(entidad);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(T entidad, Session sesion) {
        sesion.refresh(entidad);
    }

    /**
     * Agrega comillas a una cadena: 'ejemplo'.
     *
     * @param cadena Cadena sin comillas: ejemplo.
     * @return cadena con comillas
     */
    protected String comillar(String cadena) {
        return StringUtils.isBlank(cadena) ? "''" : "'" + cadena + "'";
    }
}
