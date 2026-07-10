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


-------------------------------------------------------------------------

# Práctica 5: Persistencia con Spring Boot y PostgreSQL

## Introducción
En esta práctica se migró el CRUD de la aplicación desde una gestión de datos en memoria hacia una base de datos relacional persistente utilizando **PostgreSQL** y **Spring Data JPA**. Se implementó el uso de contenedores mediante **Docker** para garantizar un entorno de desarrollo reproducible, facilitando la conexión y gestión de la base de datos `devdb`.

## Flujo de Datos
El flujo de datos sigue la arquitectura de capas, permitiendo la separación de responsabilidades:
* **Cliente**: Realiza peticiones HTTP al `Controller`.
* **Service**: Gestiona la lógica de negocio y utiliza el `Repository`.
* **Repository**: Ejecuta operaciones de persistencia mediante JPA e Hibernate.
* **BaseEntity**: Se utiliza como superclase para heredar campos de auditoría (`id`, `createdAt`, `updatedAt`, `deleted`) en todas las entidades, garantizando consistencia y eliminando código duplicado.

## Configuración del Entorno
* **Base de Datos**: Se configuró un contenedor PostgreSQL mediante Docker usando el volumen `pgdata` para asegurar que los datos persistan aunque el contenedor se elimine.
* **Conexión**: Se configuró el archivo `application.yml` para establecer la conexión mediante `jdbc:postgresql://localhost:5432/devdb` con el usuario `ups`.

## Evidencias del CRUD con PostgreSQL

### 4.1. Estado del Contenedor en Docker
Se verificó que el contenedor `postgres-dev` esté activo y operando en el puerto 5432.
![Docker Status](/src/assets/terminal-practica05.png)

### 4.2. Peticiones de la API (Bruno)
Se realizaron pruebas de creación (POST), actualización (PUT) y borrado lógico (DELETE) mediante la herramienta Bruno, garantizando que el servicio responde correctamente.

![Bruno Requests](/src/assets/bruno-practica05.png)

### 4.3. Verificación en Base de Datos
Se realizó una consulta directa a la base de datos para validar la persistencia y el borrado lógico (`deleted = true` para el smartphone).

![SELECT](/src/assets/select-practica05.png)

Se realizo una practica en clases de crear dos productos, cambiar el nombre de un producto y elimniar.

![SELECT](/src/assets/clase-practica05.png)

```sql
-- Consulta SQL utilizada para verificar la persistencia y el borrado lógico
SELECT * FROM products;

```

------------------------------------------------------------------------------------

# Practica 06 - Frameworks Backend: Spring Boot – Validación de DTOs y Reglas de Entrada

# 1. Introducción
En esta práctica se implementó una API REST utilizando Spring Boot, aplicando validación de datos de entrada mediante Jakarta Validation (@Valid), con el objetivo de asegurar la integridad de los datos antes de ser procesados y almacenados en la base de datos. Se trabajó con un módulo de productos que incluye operaciones CRUD, validaciones, reglas de negocio y eliminación lógica (soft delete).


# 2. Validación implementada
Se utilizó Jakarta Validation con: @NotBlank (texto obligatorio), @NotNull (valores obligatorios), @Size (longitud de texto), @Min(0) (evitar negativos), @Valid (activación de validación en controller).

# 3. DTOs implementados
CreateProductDto, UpdateProductDto, PartialUpdateProductDto, ProductResponseDto.

# 4. Reglas de negocio implementadas
No productos con precio negativo, no productos con stock negativo, no actualizar productos eliminados, no eliminar dos veces un producto, no mostrar productos eliminados en consultas.

# 5. Soft Delete
Se implementó eliminación lógica con entity.setDeleted(true); para evitar eliminación física en base de datos.

# 6. Flujo de la aplicación
Cliente → Controller (@Valid) → Service → Model → Entity → Repository → PostgreSQL

# 7. Pruebas realizadas

## Caso inválido
{
  "name": "",
  "price": -5,
  "stock": -2
}
Resultado: 400 Bad Request

## Caso válido
{
  "name": "Mouse Logitech",
  "price": 25.5,
  "stock": 10
}
Resultado: Producto creado correctamente (200 OK)

## DELETE
DELETE /products/{id}
Resultado: 200 OK (respuesta vacía)

## Verificación soft delete
GET /products → el producto eliminado NO aparece.

# 8. EVIDENCIAS

## CRUD de productos (Bruno)
![Bruno Requests](/src/assets/get-practica06.png)
![Bruno Requests](/src/assets/get-id-practica06.png)
![Bruno Requests](/src/assets/post-practica06.png)
![Bruno Requests](/src/assets/patch-practica06.png)
![Bruno Requests](/src/assets/put-practica06.png)

Esta captura muestra el funcionamiento completo del CRUD de productos (crear, listar, actualizar y eliminar).

---

## Validación de DTO (error 400 Bad Request)
![Validación 400](/src/assets/error-validacion-practica06.png)

Esta captura muestra la validación de datos de entrada con @Valid, generando error 400 cuando los datos son incorrectos.

---

## Listado de productos
![GET Products](/src/assets/get-practica06.png)

Esta captura muestra la consulta de todos los productos disponibles (GET /products), donde no aparecen los eliminados.

---

## Eliminación de producto (Soft Delete)
![DELETE Product](/src/assets/delete-practica06.png)

Esta captura muestra la eliminación lógica de un producto mediante DELETE, sin borrarlo físicamente de la base de datos.

---

## Verificación de Soft Delete
![Soft Delete](/src/assets/producto-delete-practica06.png)

Esta captura muestra que el producto eliminado ya no aparece en el listado GET /products, confirmando el soft delete.


# 9. Conclusión
Se implementó una API REST con Spring Boot aplicando validación de DTOs, reglas de negocio y eliminación lógica, garantizando integridad de datos antes de persistir en la base de datos.

# Resultado final
API funcional, validación de datos, CRUD completo, soft delete y arquitectura en capas.

-------------------------------------------------------------------------------------------

# Práctica 7 - Manejo Global de Errores y Excepciones

## Asignatura

Programación y Plataformas Web

## Tema

Framework Backend: Spring Boot - Control Global de Errores y Excepciones

## Objetivo

Implementar un sistema global para el manejo de errores en el módulo **Products**, utilizando excepciones personalizadas, un manejador global de excepciones y un formato estándar para las respuestas de error.

---

# Funcionalidades implementadas

- Manejo global de excepciones mediante `@RestControllerAdvice`.
- Excepción base `ApplicationException`.
- Excepción `NotFoundException`.
- Excepción `ConflictException`.
- Excepción `BadRequestException`.
- Respuesta estándar mediante `ErrorResponse`.
- Validación automática de DTOs con `@Valid`.
- Eliminación lógica de productos.
- Validación de productos duplicados.
- Validación de productos inexistentes.

---

# Estructura implementada

```
core
└── exceptions
    ├── base
    │   └── ApplicationException.java
    │
    ├── domain
    │   ├── NotFoundException.java
    │   ├── ConflictException.java
    │   └── BadRequestException.java
    │
    ├── handler
    │   └── GlobalExceptionHandler.java
    │
    └── response
        └── ErrorResponse.java
```

---

# Evidencias

## 1. Error por producto inexistente

### Solicitud

```
GET /products/999
```

### Resultado esperado

```
404 Not Found
```

### Captura

> Agregar aquí la captura del error 404.

![Producto inexistente](/src/assets/product-not-found-practica07.png)

---

## 2. Error por producto duplicado

### Solicitud

```
POST /products
```

```json
{
    "name": "monitor",
    "price": 300,
    "stock": 20
}
```

### Resultado esperado

```
409 Conflict
```

### Captura

> Agregar aquí la captura del error 409.

![Producto duplicado](/src/assets/error-duplicado-practica07.png)

---

## 3. Error de validación del DTO

### Solicitud

```json
{
    "name": "",
    "price": -5,
    "stock": -1
}
```

### Resultado esperado

```
400 Bad Request
```

### Captura

> Agregar aquí la captura del error 400.

![Validación DTO](/src/assets/error-DTO-practica07.png)

---

## 4. Eliminación lógica

### Solicitud

```
DELETE /products/5
```

Posteriormente:

```
GET /products/5
```

### Resultado esperado

```
404 Not Found
```

### Captura

> Agregar aquí la captura del eliminado lógico.

![Delete lógico](/src/assets/eliminado-logico-practica07.png)

---

# Conclusiones

- Se implementó un sistema centralizado para el manejo de errores utilizando excepciones personalizadas.
- Se logró un formato uniforme para todas las respuestas de error mediante la clase `ErrorResponse`.
- Se validó correctamente la existencia de productos antes de realizar operaciones de consulta, actualización y eliminación.
- Se implementó la validación para impedir el registro de productos con nombres duplicados.
- Se verificó el correcto funcionamiento de las validaciones automáticas de los DTOs mediante `@Valid`.
- Se comprobó el funcionamiento del eliminado lógico y su integración con las excepciones del sistema.

---

# Resultado

La API responde correctamente con los siguientes códigos HTTP:

| Método | Escenario | Código HTTP |
|---------|-----------|-------------|
| GET | Producto existente | 200 OK |
| GET | Producto inexistente | 404 Not Found |
| POST | Producto válido | 200 OK |
| POST | Producto duplicado | 409 Conflict |
| POST | Datos inválidos | 400 Bad Request |
| PUT | Actualización correcta | 200 OK |
| PATCH | Actualización parcial | 200 OK |
| DELETE | Eliminación lógica | 200 OK |
| GET | Producto eliminado | 404 Not Found |

-----------------------------------------------------------------------------------------------

# Práctica 8 ()

-----------------------------------------------------------------------------------------------

# Práctica 10 (Spring Boot): Paginación de Productos con Page, Slice y Pageable

## 1. Resumen

En esta práctica se implementó paginación sobre el recurso `products` usando Spring Data JPA, mediante las interfaces `Page`, `Slice` y `Pageable`. Se mantuvo el endpoint original sin paginar (`GET /api/products`) para comparar el comportamiento frente a los nuevos endpoints paginados.

También se extendió la paginación al endpoint de productos por categoría (`/api/categories/{id}/products`), agregando las variantes `/page` y `/slice`, conservando los filtros dinámicos ya implementados en la Práctica 9.

---

## 2. Endpoints implementados

### Productos sin paginación

| Método | Ruta             | Descripción                        |
| ------ | ---------------- | ----------------------------------- |
| GET    | `/api/products`  | Lista todos los productos activos  |

### Productos con Page

| Método | Ruta                                                              | Descripción                                |
| ------ | ------------------------------------------------------------------ | ------------------------------------------- |
| GET    | `/api/products/page`                                              | Página inicial con valores por defecto     |
| GET    | `/api/products/page?page=0&size=5`                                | Primera página con 5 registros             |
| GET    | `/api/products/page?page=1&size=10`                               | Segunda página con 10 registros            |
| GET    | `/api/products/page?page=0&size=5&sortBy=price&direction=desc`    | Productos ordenados por precio descendente |
| GET    | `/api/products/page?page=0&size=5&sortBy=name&direction=asc`      | Productos ordenados por nombre ascendente  |

### Productos con Slice

| Método | Ruta                                                                  | Descripción                            |
| ------ | ----------------------------------------------------------------------| ---------------------------------------- |
| GET    | `/api/products/slice`                                                | Slice inicial con valores por defecto  |
| GET    | `/api/products/slice?page=0&size=5`                                  | Primer slice con 5 registros           |
| GET    | `/api/products/slice?page=1&size=5`                                  | Segundo slice con 5 registros          |
| GET    | `/api/products/slice?page=0&size=5&sortBy=createdAt&direction=desc`  | Slice ordenado por fecha descendente   |

### Productos por categoría (paginado)

| Método | Ruta                                                                                | Descripción                              |
| ------ | -------------------------------------------------------------------------------------| ------------------------------------------ |
| GET    | `/api/categories/{id}/products`                                                     | Lista sin paginar (se mantiene, Práctica 9) |
| GET    | `/api/categories/{id}/products/page?page=0&size=5`                                  | Productos de la categoría paginados con Page |
| GET    | `/api/categories/{id}/products/page?name=laptop&minPrice=500&page=0&size=5`         | Page con filtros combinados               |
| GET    | `/api/categories/{id}/products/slice?page=0&size=5`                                 | Productos de la categoría paginados con Slice |

---

## 3. Carga masiva de datos

Se generó un script `seed_data.sql` que crea:

- 10 usuarios
- 10 categorías
- 20 000 productos
- ~50 000 relaciones producto-categoría (2 o 3 categorías por producto)

Ejecución dentro del contenedor Docker:

```bash
docker exec -i postgres-dev psql -U ups -d devdb < seed_data.sql
```

o desde PowerShell:

```powershell
Get-Content seed_data.sql | docker exec -i postgres-dev psql -U ups -d devdb
```

Verificación tras la carga:

```
INSERT 0 10       → 10 usuarios
INSERT 0 10       → 10 categorías
INSERT 0 20000    → 20 000 productos
INSERT 0 50002    → 50 002 relaciones producto-categoría
COMMIT
```

---

## 4. Resultados y evidencias

### 4.1. Problema detectado en el endpoint sin paginar

`GET /api/products`

![Api Products](/src/assets/api-products.png)

Con 20 000 productos, el endpoint sin paginar mostró un tiempo de respuesta elevado y un tamaño de payload considerable, ya que cada producto incluye información anidada de `owner` y `categories`. Esto evidencia que devolver todos los registros sin límite no escala cuando el volumen de datos crece.

### 4.2. Respuesta con Page

`GET /api/products/page?page=0&size=5`

![Products Page](/src/assets/products-page.png)


`Page` evidencia los metadatos completos: `content`, `totalElements`, `totalPages`, `number`, `size`, `first`, `last`.

### 4.3. Respuesta con Slice

`GET /api/products/slice?page=0&size=5&sortBy=createdAt&direction=desc`

![Slice](/src/assets/Slice.png)

A diferencia de `Page`, la respuesta de `Slice` **no** incluye `totalElements` ni `totalPages`, porque no ejecuta la consulta `COUNT`. Esto la hace más rápida y liviana, a costa de no saber el total de registros ni de páginas.

### 4.4. Error por paginación inválida

`GET /api/products/page?page=-1&size=0`

![size](/src/assets/size.png)

Esto confirma que las validaciones `@Min` de `PaginationDto` funcionan correctamente junto con el `GlobalExceptionHandler`.

### 4.5. Endpoint de categoría paginado con Page

`GET /api/categories/1/products/page?page=1&size=10`

![categories-products-page](/src/assets/categories-products-page.png)

Se evidencia:
- Productos filtrados por la categoría indicada
- Paginación aplicada (`totalElements: 4459`, `totalPages: 446`)
- Filtros de la Práctica 9 (`name`, `minPrice`, `maxPrice`, `userId`) siguen disponibles junto con la paginación

### 4.6. Endpoint de categoría paginado con Slice

`GET /api/categories/1/products/slice?page=0&size=5`

![categories-products-slice-page](/src/assets/categories-products-slice-page.png)

Se evidencia:
- Productos filtrados por la categoría indicada
- Metadatos de `Slice` (`first`, `last`, `hasNext`, sin `totalElements`)

---

## 5. Explicación breve

### ¿Cuál es la diferencia entre `Page` y `Slice`?

`Page` ejecuta dos consultas: una para traer los datos (`LIMIT`/`OFFSET`) y otra de tipo `COUNT` para calcular el total de registros y de páginas. Esto permite mostrar información completa de navegación, como "Página 3 de 20" o "150 resultados en total", pero tiene un costo adicional de rendimiento porque cada solicitud ejecuta una consulta extra sobre toda la tabla filtrada.

`Slice`, en cambio, solo ejecuta la consulta de datos y le pide a la base de datos un registro adicional al tamaño de página solicitado (`size + 1`) para determinar si existe una página siguiente (`hasNext`). No sabe cuántos registros existen en total ni cuántas páginas hay. Es más eficiente cuando la interfaz solo necesita avanzar o retroceder, como en un scroll infinito o un botón de "siguiente", sin mostrar el total exacto de resultados.

En resumen: `Page` es más informativo pero más costoso; `Slice` es más liviano pero con información parcial.

### ¿Por qué la paginación debe aplicarse en el repositorio y no después de traer todos los datos en memoria?

Si la paginación se aplicara después de traer todos los registros a memoria (por ejemplo, usando `.stream().skip().limit()` sobre una lista ya cargada desde `findAll()`), la base de datos igual tendría que leer y transportar **todos** los registros de la tabla hacia la aplicación antes de descartar la mayoría de ellos. Esto anula por completo el propósito de paginar: se seguiría consumiendo memoria, ancho de banda y tiempo de procesamiento proporcional al total de datos, no al tamaño de página solicitado.

Al aplicar la paginación directamente en el repositorio mediante `Pageable`, Spring Data JPA traduce `page`, `size` y `sort` en cláusulas SQL `LIMIT`, `OFFSET` y `ORDER BY` que se ejecutan **dentro de la base de datos**. Esto significa que PostgreSQL solo lee y devuelve la cantidad exacta de filas necesarias para esa página, sin importar si la tabla tiene 100 o 20 000 000 de registros. Es la única forma de que la paginación realmente mejore el rendimiento y escale con el crecimiento de los datos.

---
--------------------------------------------------------------------------------------



----------------------------------------------------------------------------------------

# Práctica 13 - Validación de Ownership con Spring Security y JWT

---

# Información

---

# Objetivo

Implementar un mecanismo de validación de ownership (propiedad del recurso) para garantizar que únicamente el propietario de un producto o un usuario con rol **ROLE_ADMIN** pueda modificar o eliminar dicho recurso.

La autenticación se realiza mediante JWT y el propietario del producto se obtiene automáticamente desde el usuario autenticado, evitando que el cliente envíe manualmente un identificador de usuario.

---

# Pruebas realizadas en Bruno

Todas las pruebas se realizaron utilizando autenticación JWT mediante el encabezado:

```http
Authorization: Bearer <token>
```

---

# Prueba 1 - Registro de usuarios

### Usuario A

```
POST /auth/register
```

```json
{
  "name": "Usuario A",
  "email": "usera@ups.edu.ec",
  "password": "Password123"
}
```

Resultado esperado

- 201 Created
- Se genera un JWT.
- Se asigna ROLE_USER.

![Registro Usuario A](/src/assets/usuarioA.png)

---

### Usuario B

```
POST /auth/register
```

```json
{
  "name": "Usuario B",
  "email": "userb@ups.edu.ec",
  "password": "Password123"
}
```

Resultado esperado

- 201 Created
- Se genera un JWT.
- Se asigna ROLE_USER.

![Usuario B](/src/assets/usuarioB.png)

---

# Prueba 2 - Creación del producto

El Usuario A crea un producto.

```
POST /products
```

```json
{
  "name": "Laptop Usuario A",
  "price": 900,
  "stock": 10,
  "categoryIds": [1,2]
}
```

No se envía ningún **userId**, ya que el propietario se obtiene desde el JWT.

Resultado esperado

- 200 OK

La respuesta debe mostrar:

```json
"owner": {
    "email": "usera@ups.edu.ec"
}
```

![Cracion de Producto](/src/assets/creacion-product.png)

---

# Prueba 3 - Usuario B intenta modificar el producto

```
PUT /products/{id}
```

```json
{
  "name": "Hackeado",
  "price": 1,
  "stock": 1,
  "categoryIds": [1]
}
```

Resultado esperado

```
403 Forbidden
```

Mensaje esperado

```json
{
   "message":"No puedes modificar productos ajenos"
}
```

![Modificar](/src/assets/modificar.png)

---

# Prueba 4 - Usuario B intenta eliminar el producto

```
DELETE /products/{id}
```

Resultado esperado

```
403 Forbidden
```
![Delete](/src/assets/delete.png)

---

# Prueba 5 - Usuario A modifica su producto

```
PUT /products/{id}
```

```json
{
  "name": "Laptop A Actualizada",
  "price": 950,
  "stock": 8,
  "categoryIds": [1,2]
}
```

Resultado esperado

```
200 OK
```

---

# Prueba 6 - Administrador modifica el producto

```
PUT /products/{id}
```

```json
{
  "name": "Modificado por ADMIN",
  "price": 1100,
  "stock": 20,
  "categoryIds": [1,2]
}
```

Resultado esperado

```
200 OK
```

---

# Conclusiones

La implementación de la validación de ownership permitió garantizar que únicamente el propietario de un recurso pueda modificarlo o eliminarlo. El propietario del producto se obtiene directamente del usuario autenticado mediante el token JWT, evitando que el cliente pueda manipular el identificador del usuario.

Las pruebas realizadas demostraron que un usuario autenticado únicamente puede administrar sus propios productos, mientras que los usuarios con privilegios de administrador mantienen acceso completo a todos los recursos del sistema.

Esta práctica fortalece la seguridad de la API al combinar autenticación mediante JWT, autorización basada en roles y control de acceso basado en ownership, siguiendo las buenas prácticas para el desarrollo de servicios REST seguros.

---



