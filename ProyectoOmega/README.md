# Proyecto Omega

## Especificaciones
* JAVA (v1.8.0_201)
* NetBeans (>=8.0)
* Bootstrap (>=4.0)
* GlassFish Server (v4)
* Java DB (desde GlassFish Server)

## Requerimientos
* Navegadores probados: Safari (v12.1), Chrome (>= v74.0)
* Puertos disponibles: 1527, 8080

## Ejecución
1. Abrir NetBeans
2. Abrir 3 proyectos (`File/Open Project`):
    * OmegaSoap
    * OmegaRest
    * OmegaClient
3. (OPCIONAL) Cambiar el nombre de la base de datos en: 
    * `./OmegaClient⁩/web⁩/app.jsp`
    * `./OmegaRest⁩/src⁩/java⁩/webservices⁩/OperationsResource.java`
4. A los 3 proyectos, click derecho y `Clean and Build`
    * Orden sugerido:
        * OmegaSoap
        * OmegaRest
        * OmegaClient
5. A los 3 proyectos, click derecho y `Deploy`
    * Orden sugerido:
        * OmegaSoap
        * OmegaRest
        * OmegaClient
6. Acceder a la url [http://localhost:8080/OmegaClient/](http://localhost:8080/OmegaClient/)

# FAQ
1. ¿Es necesario tener JARs adicionales?
    * El proyecto trae todas las librerias necesarias para ejecutarse, en caso contrario, verificar la existencia de `json-simple-1.1.1.jar`
2. ¿Cómo enciendo los servidores?
    * Si GlassFish y Java DB estan _apagados_, al momento de ejecutarse cualquiera de los 3 proyectos, iniciará GlassFish. JavaDB iniciará cuando OmegaSoap sea desplegado.
