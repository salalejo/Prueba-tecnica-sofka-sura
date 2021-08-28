Prueba Tecnica Constructores SAS

Esta prueba se realizó creando varios servicios web para cada una de las condiciones que se pedían y además, fue desplegada la solución en Heroku y tiene una persistencia de datos en MongoDB.

URL App(Front-End consumiendo los servicios del back): https://constructores-sas.netlify.app/ 
URL backend: https://constructores-sas.herokuapp.com/

SERVICIO 1:
Crear solicitudes de construcción dado un tipo y unas coordenadas
Endpoint: https://constructores-sas.herokuapp.com/api/createcr

SERVICIO 2:
Crear un reporte con todas las solicitudes de construcción agrupadas por su estado(pendiente, en progreso, terminadas)
Endpoint: https://constructores-sas.herokuapp.com/api/informe

SERVICIO 3:
Mostrar la fecha de terminación del proyecto
Endpoint: https://constructores-sas.herokuapp.com/api/fechafinal

SERVICIO 4:
Añadir materiales al stock de cada uno
Endpoint: https://constructores-sas.herokuapp.com/api/addmaterial
