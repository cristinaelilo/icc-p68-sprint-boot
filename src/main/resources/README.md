# Práctica 1: Instalación y Configuración Inicial de Spring Boot

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.0-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)

Este repositorio contiene el proyecto `fundamentos01`, desarrollado como parte de la Práctica 1 para comprender la arquitectura inicial, configuración y despliegue de una API REST utilizando Spring Boot y un servidor Tomcat embebido.

## Información Académica
* **Universidad:** Universidad Politécnica Salesiana (UPS)
* **Carrera:** Ciencias de la Computación
* **Asignatura:** Programación y Plataformas Web
* **Docente:** Ing. Pablo Torres
* **Estudiante:** Cristina Loja  clojap1@est.ups.edu.ec

---

## Tecnologías y Herramientas
* **Lenguaje:** Java 17 (Compatible hasta v25)
* **Framework:** Spring Boot 4.0.0
* **Dependencias:** Spring Web, Spring Boot DevTools
* **Build Tool:** Gradle (Groovy DSL)
* **Servidor:** Apache Tomcat (Embebido)

---

## Ejecución del Proyecto

Para levantar el servidor en un entorno local de Windows, ejecuta el siguiente comando en la terminal en la raíz del proyecto:

```powershell
.\gradlew.bat bootRun
```

> **Nota:** Si existe un problema de memoria RAM o puertos ocupados, se puede ejecutar el proyecto forzando una nueva instancia de memoria con: `.\gradlew.bat bootRun --no-daemon -Dorg.gradle.jvmargs="-Xmx256m"`

---

## Endpoints Implementados

### Estado del Servicio
Verifica que la API está en línea y funcionando correctamente.

* **URL:** `/api/status`
* **Método HTTP:** `GET`
* **Respuesta Exitosa (JSON):**

```json
{
  "service": "Spring Boot API",
  "status": "running",
  "timestamp": "2026-06-14T17:22:01.242"
}
```
---

## Configuración del Entorno de Red
El proyecto utiliza un archivo `application.yml` para gestionar la configuración del servidor, centralizando las rutas de acceso y el puerto de escucha.

* **Puerto:** `8081`
* **Context Path (Prefijo raíz):** `/api`

### Tabla de Endpoints Disponibles
| Descripción | Ruta (URL Completa) | Método |
| :--- | :--- | :--- |
| Estado del Servicio | `/api/status` | `GET` |
| Lista de Estudiantes | `/api/students` | `GET` |
| Conteo de Estudiantes | `/api/students/count` | `GET` |

---

## Evidencias de la Práctica

A continuación, se presentan las capturas correspondientes a la validación del entorno y funcionamiento del código:

### 1. Verificación de Java
Captura del comando java -version en la terminal, confirmando la instalación de JDK 17 y la configuración correcta de las variables de entorno para el desarrollo del proyecto.

![Versión de Java](/src/assets/evidencia-01-java.png)

### 2. Servidor Spring Boot en Ejecución
Captura de la terminal mostrando el log del proceso bootRun, donde se confirma el arranque del servidor Tomcat embebido y el inicio satisfactorio del contexto de la aplicación.

![Servidor Ejecutándose](/src/assets/evidencia-02-servidor.png)

### 3. Prueba del Endpoint en el Navegador
Captura del navegador web accediendo a localhost:8080/api/status, visualizando la respuesta JSON que confirma que el servicio se encuentra en estado "running".

![Endpoint Funcionando](/src/assets/evidencia-03-endpoint.png)

### 4. Estructura del Controlador
Captura del entorno de desarrollo (IDE) mostrando el código fuente del controlador, donde se observa la implementación de las anotaciones @RestController y @GetMapping.

![Estructura del Controlador](/src/assets/evidencia-04-controlador.png)

### 5. Gestión de Estudiantes
Visualización de la colección de objetos `Student` serializada a JSON mediante el endpoint configurado.

![Lista de Estudiantes](/src/assets/evidencia-05-students.png)

### 6. Conteo de Estudiantes
Visualización del resultado del endpoint `/api/students/count`, mostrando el total de registros procesados.

![Conteo de Estudiantes](/src/assets/evidencia-06-count.png)

### 5. Conclusión

* **Funcionamiento del endpoint (/api/status):** Comprendí que el endpoint funciona como una interfaz de comunicación expuesta mediante el protocolo HTTP. Al realizar una petición de tipo GET a la ruta /api/status, el framework Spring Boot intercepta la solicitud, la dirige al método correspondiente en el controlador gracias a la anotación @GetMapping("/api/status"), procesa la lógica interna y genera una respuesta automática en formato JSON. Esta respuesta incluye datos dinámicos como el timestamp y el status del servicio, permitiendo que cualquier cliente pueda consumir esta información de manera estructurada.

* **Función general de Spring Boot en la creación del servidor:** La función principal de Spring Boot es automatizar y simplificar la creación de servidores web, eliminando la necesidad de configurar manualmente servidores externos como Apache Tomcat. Al ser un framework basado en el concepto de "servidor embebido", Spring Boot inicia automáticamente el entorno de ejecución necesario al momento de ejecutar la aplicación. Esto centraliza la configuración, gestión de dependencias y despliegue dentro del mismo proyecto, garantizando que el servidor esté siempre listo y optimizado para la aplicación específica que estamos desarrollando.

-----------------------------------------------------------------------------------------

# Práctica 3: Desarrollo de API REST con Spring Boot (Users & Products)

Este proyecto corresponde a la Práctica 3, donde se implementa una API REST en Spring Boot utilizando almacenamiento en memoria (Listas), aplicando controladores, DTOs y mappers para gestionar usuarios y productos.

## Endpoints USERS

GET /api/users
GET /api/users/{id}
POST /api/users
PUT /api/users/{id}
PATCH /api/users/{id}
DELETE /api/users/{id}

## Endpoints PRODUCTS

GET /api/products
GET /api/products/{id}
POST /api/products
PUT /api/products/{id}
PATCH /api/products/{id}
DELETE /api/products/{id}

## Pruebas con Bruno

### Crear Usuario

{
  "name": "Cristina",
  "email": "cristina@est.ups.edu.ec",
  "password": "12345"
}

### Productos

Producto 1:
{
  "name": "Laptop Dell",
  "price": 1200.50,
  "stock": 10
}

Producto 2:
{
  "name": "Mouse Logitech",
  "price": 25.50,
  "stock": 50
}

Producto 3:
{
  "name": "Teclado Redragon",
  "price": 45.00,
  "stock": 20
}

## Evidencias

- GET /api/products (lista de productos)

![Conteo de Estudiantes](/src/assets/get-practica03.png)

- GET /api/products/{id}

![Conteo de Estudiantes](/src/assets/get-id-practica03.png)

- DELETE /api/products/{id} existente

![Conteo de Estudiantes](/src/assets/delete-practica03.png)

- DELETE /api/products/{id} inexistente

![Conteo de Estudiantes](/src/assets/delete-inexistente-practica03.png)

## Conclusión

Se implementó una API REST funcional con Spring Boot aplicando arquitectura basada en controladores, DTOs y mappers. Se utilizó almacenamiento en memoria mediante listas para simular una base de datos. Se validaron todos los endpoints mediante la herramienta Bruno, comprobando el funcionamiento correcto del CRUD para usuarios y productos.

------------------------------------------------------------------------------------------

# Práctica 4: Controladores + Servicios + Lógica de Negocio en Spring Boot

## Objetivo de la práctica

En esta práctica se refactorizó la arquitectura del proyecto, separando la lógica de negocio del controlador mediante el uso de la capa de servicios (`@Service`). Esto permite aplicar una arquitectura más limpia y escalable basada en capas.

---

## Arquitectura implementada

El flujo de la aplicación ahora es el siguiente:

Cliente  
→ Controller  
→ Service  
→ ServiceImpl  
→ List en memoria  
→ Mapper  
→ DTO de respuesta  

---

## Tecnologías utilizadas

* Java 17  
* Spring Boot  
* Spring Web  
* Programación en capas (Controller - Service - Mapper - DTO)  
* Stream API  
* Inyección de dependencias  

---

## Estructura del módulo Users

Se implementó un CRUD completo para usuarios con la siguiente estructura:

* UserModel  
* UserController  
* UserService (interfaz)  
* UserServiceImpl (lógica de negocio)  
* UserMapper  
* DTOs:
  - CreateUserDto  
  - UpdateUserDto  
  - PartialUpdateUserDto  
  - UserResponseDto  
  - ErrorResponseDto  

---

## Estructura del módulo Products

Se replicó la misma arquitectura del módulo Users para Products:

* ProductModel  
* ProductController  
* ProductService (interfaz)  
* ProductServiceImpl  
* ProductMapper  
* DTOs:
  - CreateProductDto  
  - UpdateProductDto  
  - PartialUpdateProductDto  
  - ProductResponseDto  

---

## Funcionalidades implementadas

### Users API

| Método | Endpoint | Descripción |
|------|---------|-------------|
| GET | /api/users | Listar usuarios |
| GET | /api/users/{id} | Buscar usuario por ID |
| POST | /api/users | Crear usuario |
| PUT | /api/users/{id} | Actualización completa |
| PATCH | /api/users/{id} | Actualización parcial |
| DELETE | /api/users/{id} | Eliminar usuario |

---

### Products API

| Método | Endpoint | Descripción |
|------|---------|-------------|
| GET | /api/products | Listar productos |
| GET | /api/products/{id} | Buscar producto por ID |
| POST | /api/products | Crear producto |
| PUT | /api/products/{id} | Actualización completa |
| PATCH | /api/products/{id} | Actualización parcial |
| DELETE | /api/products/{id} | Eliminar producto |

---

## Evidencias de la práctica

### 1. ProductServiceImpl.java

Captura donde se evidencia:
* uso de @Service  
* lista en memoria  
* generación de ID  
* lógica CRUD completa  
* uso de mapper  

![ProductServiceImpl](/src/assets/productServicelmpl-practica4.png)

![ProductServiceImpl](/src/assets/productServicelmpl1-practica4.png)

![ProductServiceImpl](/src/assets/productServicelmpl2-practica4.png)

---

### 2. ProductsController.java

Captura donde se evidencia:
* inyección de ProductService  
* endpoints REST  
* ausencia de lógica de negocio en el controlador  

![ProductsController](/src/assets/productsController-practica4.png)

![ProductsController](/src/assets/productsController1-practica4.png)


## Explicación breve

### ¿Cómo se inyecta el servicio en el controlador?

El servicio se inyecta en el controlador mediante **inyección de dependencias por constructor**.

Spring Boot detecta automáticamente la clase que implementa la interfaz `UserService` o `ProductService` gracias a la anotación `@Service`.

Luego, al crear el controlador, Spring proporciona la instancia del servicio como parámetro del constructor:

private final UserService service;

public UsersController(UserService service) {
    this.service = service;
}


### Conclusión

En esta práctica se aplicó el patrón de arquitectura por capas en Spring Boot, separando la lógica de negocio del controlador mediante la implementación de servicios. Esto permitió mejorar la organización del código, facilitar el mantenimiento y seguir buenas prácticas de desarrollo backend.

