Conceptos Fundamentales
¿Qué es un servicio REST?

REST es un estilo de arquitectura de software enfocado en el intercambio de recursos y basado en HTTP. Le agrega una capa muy delgada de complejidad y abstracción a HTTP. Mientras que HTTP es transferencia de archivos, REST se basa en la transferencia de recursos.

¿Cuáles son los principios del diseño RESTful?

Una API RESTful es una interfaz que utiliza los principios de REST para comunicarse hacia y desde un servidor.
El principio más importante en las APIs RESTful es el uso de los métodos HTTP:
GET
POST
PUT
DELETE
Estos métodos son empleados por los clientes para crear, manipular y eliminar datos en los servidores, respectivamente.

¿Qué es HTTP y cuáles son los métodos HTTP más comunes?

El Protocolo de Transferencia de Hipertexto (HTTP) está diseñado para permitir la comunicación entre clientes y servidores.

HTTP funciona como un protocolo de petición-respuesta entre un cliente y un servidor.

Metodos HTTP: 
GET
POST
PUT
HEAD
DELETE
PATCH
OPTIONS
CONNECT
TRACE
Los metodos mas comunes de HTTP son: GET y POST.

¿Qué es un recurso en el contexto de un servicio REST?

Los recursos son el bloque de compilación básico de creación de un servicio RESTful. Ejemplos de un recurso de una aplicación de almacén de libros en línea incluyen un libro, un pedido en una tienda y una colección de usuarios.

Los recursos los direccionan los URL y los métodos HTTP pueden llevar a cabo operaciones en recursos. Los recursos pueden tener diversas representaciones que utilicen distintos formatos como, por ejemplo, XML y JSON. Puede utilizar cabeceras y parámetros HTTP para pasar información adicional que sea relevante para la solicitud y la respuesta.


¿Qué es un endpoint?

Un punto final de API es la ubicación de la API en la que un sistema interactúa con una API web. También es el punto de comunicación entre dos sistemas.

La URL del punto final es la ubicación exacta del recurso solicitado en un servidor API, permitiendo así que dos programas interactúen. En el punto final, la API accederá a los recursos que necesite de un servidor para realizar una tarea específica, como la obtención de ciertos datos o información.

 Estructura de un Servicio REST
 
Reglas de una arquitectura REST
a- Interfaz uniforme
b- Peticiones sin estado
c- Cacheable
d- Separación de cliente y servidor
e- Sistema de Capas
f- Código bajo demanda (opcional)
 
¿Qué es un URI y cómo se define?

URI es la abreviatura de Uniform Resource Identifier, en español identificador uniforme de recursos. Este término genérico se emplea para todos los tipos de nombres y direcciones que se refieren a objetos internet tales como páginas, imágenes, videos, etc.

Un URI es por tanto una cadena de caracteres que se utilizan para identificar un recurso o un nombre en internet. Su propósito es permitir la interacción entre diferentes recursos en Internet y otro tipo de red.

¿Qué es una API RESTful?

La API RESTful es una interfaz que dos sistemas de computación utilizan para intercambiar información de manera segura a través de Internet. La mayoría de las aplicaciones para empresas deben comunicarse con otras aplicaciones internas o de terceros para llevar a cabo varias tareas

¿Qué son los códigos de estado HTTP y cómo se usan en REST?

Los codigos de respuestas se engloban en cinco clases: 

Respuestas informativas (100–199),
Respuestas satisfactorias (200–299),
Redirecciones (300–399),
Errores de los clientes (400–499),
y errores de los servidores (500–599).
Mas detalles en: https://www.rfc-editor.org/rfc/rfc7231.html

Agregar una tabla con los códigos HTTP de respuesta más comunes, y su significado.

| Código | Significado                                                                      |
|--------|----------------------------------------------------------------------------------|
| 200    | OK: La solicitud ha tenido éxito.                                               |
| 201    | Creado: La solicitud ha sido cumplida y un nuevo recurso ha sido creado.       |
| 204    | Sin contenido: La solicitud ha sido exitosa, pero no hay contenido para devolver. |
| 301    | Movido permanentemente: El recurso solicitado ha sido movido a una nueva URI.   |
| 302    | Encontrado: El recurso solicitado reside temporalmente en una URI diferente.     |
| 400    | Solicitud incorrecta: La solicitud no puede ser procesada debido a errores del cliente. |
| 401    | No autorizado: Se requiere autenticación para acceder al recurso.                |
| 403    | Prohibido: El servidor ha comprendido la solicitud, pero se niega a autorizarla. |
| 404    | No encontrado: El recurso solicitado no se encuentra en el servidor.             |
| 500    | Error interno del servidor: Se ha producido un error en el servidor y la solicitud no puede ser cumplida. |

¿Qué es JSON y por qué se usa comúnmente en APIs REST?

JSON (JavaScript Object Notation) es un formato ligero de intercambio de datos que utiliza un texto legible por humanos para representar objetos estructurados. Su estructura se basa en pares de clave-valor, lo que facilita la organización y transmisión de datos.

Por que se utiliza mucho: 
Simplicidad: JSON es fácil de leer y escribir tanto para humanos como para máquinas, lo que facilita la comprensión y depuración.
Ligero: Su tamaño reducido lo hace ideal para transferencias a través de la red, lo que puede mejorar el rendimiento de las aplicaciones.
Compatibilidad: JSON es compatible con una amplia variedad de lenguajes de programación, lo que permite su uso en diferentes plataformas y aplicaciones.