# appTraduccion

La aplicación para la traduccion de un cadena de texto al idioma ingles /español se lo ha realizado de la siguiente manera

1) Base de datos en MySQL de modo que almanece las cadenas de textos traducidas. Para esto crear un esquema de base de datos {bdtraductor}
 ```sql
DROP TABLE IF EXISTS `historial_traductor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historial_traductor` (
  `id_historial_traductor` int NOT NULL AUTO_INCREMENT,
  `texto` varchar(1024) NOT NULL,
  `traduccion` varchar(1024) NOT NULL,
  `lenguaje` varchar(256) NOT NULL,
  PRIMARY KEY (`id_historial_traductor`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='En esta tabla se almacenará el texto origen el texto final y se identificará el lenguaje';
 ```
2) Se realizo una aplicación un web service mediante la apiRest usando las librerias de Netbeans **JAX-RS 2.0 / Jersey 2.5.1** en donde se encuenta la funcionalidad para almencear y listar las cadenas de texto.
 
3)Se realizo una API que esta integrada en el web service para la configuracion de Google Cloud Translate y los metodos para su traducción.
 
4)Se realizo una aplicación web para el usuario en donde le permitira traducir y listar las cadenas.
