package ec.bancopichincha.generica;

/**
 * Clase de utilidad que define el ordenamiento de consultas.
 *
 */
public final class Order {

    /**
     * Constante utilizada para definir ordenamiento ascendente.
     */
    public static final String ASC = "A,";
    /**
     * Constante utilizada para definir ordenamiento descendente.
     */
    public static final String DESC = "D,";

    /**
     * Constructor por defecto.
     */
    private Order() {

    }

    /**
     * Retorna un objeto que especifica que la propiedad recibida como par�metro
     * ser� tomada para ordenamiento ascendente.
     *
     * @param propiedad Propiedad de la entidad que ser� utilizada para el
     * ordenamiento.
     * @return Cadena que especifica el ordenamiento.
     */
    public static String ascendente(String propiedad) {
        return ASC + propiedad;
    }

    /**
     * Retorna un objeto que especifica que la propiedad recibida como par�metro
     * ser� tomada para ordenamiento descendente.
     *
     * @param propiedad Propiedad de la entidad que ser� utilizada para el
     * ordenamiento.
     * @return Cadena que especifica el ordenamiento.
     */
    public static String descendente(String propiedad) {
        return DESC + propiedad;
    }
}
