package vista;

public class txt {

}

/*Desarrollo de un sistema de gestion generico con java swing,mvc y patrones 
 Objetivo General: Evaluar la capacidad del estudiante para diseñar e implementar una aplicacion de escritorio geenerica
 con una interaz grafica utilizando Java Swing, Aplicando el patron de diseño Modelo - Vista - Controlador  MVC
 utilizando al menos tres patrones, de diseño adicionales y persistiendo datos en formato JSON.
 Dominio de la Aplicacion . Gestion de Entidades. La aplicacion permitira a los usuario crear leer actualizar y eliminar registros
 de Entidades Genericas. Cada Entidad tendra un conjunto de atributos definidos dinamicamente por el usuario.
 Requisitos Adicionales:
 1- Definicion de atributos : Al iniciode la palicacion (o a traves de una interfaz inicial) el usuariodebera poder definir los nombres y tipos
 (String o Integer) de al menos tres atributos que tendra cada Entidad.
 2- Crear: El usuario podra ingresar los valores para cada uno de los atributos definidos para una nueva Entidad y Guardarla en el sistema.
 Se debera asegurar que el atributoque se defina como "Identificador unico" (a eleccion del usuario entre los atributos String) no se repita.
 3- Leer (listar): El usuario podra visualizar una lista de todas las Entidades registradas en una tabla, mostrando todos los atributos definido.
 4- Leer (Detalle): Al seleccionar una Entidad de la tabla se mostrara los valores de sus atributos en campos de textoo(generados dinamicamente
 segun los atributos definidos).
 5- Actualizar: El usuario podra modificar los valores de los atributos de una Entidad existente y guardar los cambios. Se debera validar la unicidad
 del atributo identificador  si se modifica.
 6- Eliminar: El usuario podra seleccionar una Entidad de la tabla y eliminarla del sistema
 7- Persistencia: Los datos de las Entidades (incluyendo la definicion de los atributos) se deberan guardar en un archivo JSON al cerrar la 
 aplicacion y cargar desde el mismo archivo al iniciarla. archivo JSON al cerrar la aplicacion y cargar desde el mismo archivo al iniciarla.
 8- Lista generica de clases para insertar , eliminar, buscar, modificar, etc, Y esta debera  heredar a una lista clase 
 
 REQUISITOS TECNICOS Y PATRONES DE DISEÑO : 
  - MVC:
  modelo contendra la logica del negocio y la gestion de datos(la representacion generia de una entidad y la logica para manipular la lista de entidades
  y la definicion de atributos).
  vista se encargara de la interfaz grafica de usuario (Jframe, Jtable dinamico,JtextFields dinamico, JButtons) Debera adaptarse dinamicamente a los atributos definidos
  controlador actuara como intermediario entre las vistas y elmodelo de interfasz de usuario y actualizando el modelo  y la vusta segun sea necesario 
  
  PATRONES DE DISEÑO (Minimo 3):
   Singleton: Para garantizar una unica instancia de un servicio de gestion de la definicion de atributos o del archivo JSON.
   Factory Method: Para la creacion de objetos genericos de Entidad basados en la definicion de atributos
   Strategy: Para implementar diferentes estrategias de validacion de datos segun el tipo de atributo (String, Integer, Unicidad).
   Observer : Para notificar ala Vistas sobre el cambios en el Modelo (especialmente cuando cambia la definicion de atributos)
   Command : Para encapsular las acciones de la interfasz de usuario (Crear, Actualizar, Eliminar) como objetos genericos
   Dao : Para abtraer la logica de acceso a los datos JSON (tanto la definicion de atributos como las entidades)
   
   
   Un documento breve explicado maximo 2 cuartillas explicando :  la estructura de l proyecto y como se aplico el patron MVC en un contexto generico  . los tres patrones de diseño elegeidos y como se implementaron y justifficaron en la aplicacion generica, como se manejo la definicion dinamica de atributos en la arquitectura MVC, Cualquie consideracion de diseño o limitacion de la implementacion
   
 
*/