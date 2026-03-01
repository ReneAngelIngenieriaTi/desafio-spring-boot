# Documentación del Proyecto

## 📌 Descripción

Proyecto desarrollado con Spring Boot, Java y Maven. Aplicación backend con integración a base de datos embebida H2.
Implementa **autenticación JWT**, operaciones CRUD y está documentada con OpenAPI.

##  ✍️‍ Autor
- **Nombre:** Rene Angel Silva Zuñiga
- **Correo:** reneangel.silva@gmail.com
- **Cargo:** Software Engineer


## 🛠️ Requisitos Previos

- **Java 21 o superior**
- **Spring Boot 3.5.11** (última versión estable para 3.5.x)
- **Maven 3.6+**
- **Base de datos SQL (H2 embebida)**
- **IDE: IntelliJ IDEA (recomendado)**
- **Git para control de versiones**

## ⚙️ Instalación

### 0. Instalar Java JDK 21

Descarga e instala desde [java.oracle.com](https://www.oracle.com/java/technologies/downloads/#java21) o usa un gestor de versiones como SDKMAN:

```bash
sdk install java 21.0.1-oracle
sdk use java 21.0.1-oracle
```

Confirmar instalación:

```bash
java -version
```

### 1. Clonar el repositorio

```bash
git clone <url-del-repositorio>
cd <nombre-del-proyecto>
```

### 2. Configurar la base de datos

Edita el archivo `application.properties` o `application.yml` con tus credenciales:

```properties
# ConfiguraciÃ³n de la base de datos H2
spring.datasource.url=jdbc:h2:mem:devdb;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=*****
spring.datasource.password=*****
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

⚠️ **la username y el password de la base de datos deben ser reemplazados por los valores correspondientes a tu entorno local.**
y en ningun caso deben ser compartidos en un repositorio público.


### 2.1 [Port] Configurar de el puerto de la aplicacion ⚠️

Para configurar el puerto de la aplicación, edita el archivo src/main/resources/application.properties y añade o modifica la siguiente línea:

```properties
server.port=8080
```

O si usas application.yml:

```properties
server:
    port: 8088
```

### 3. Instalar dependencias

```bash
mvn clean install
```

## 🚀 Ejecutar la Aplicación

### Desde Maven

```bash
mvn spring-boot:run
```

### Desde IntelliJ IDEA

1. Click derecho en la clase principal con anotación `@SpringBootApplication`
2. Selecciona "Run"

La aplicación estará disponible en `http://localhost:8080`

# Pruebas

# 📡 Endpoints

| Módulo | Método | Endpoint                  | Descripción           | Auth |
|--------|--------|---------------------------|-----------------------|------|
| Auth   | POST   | `/api/v1/auth/register`   | Registra usuario      | -    |
| Auth   | POST   | `/api/v1/auth/login`      | Autentica usuario     | -    |
| Tasks  | GET    | `/api/v1/tareas`          | Lista tareas          | 🛡️  |
| Tasks  | POST   | `/api/v1/tareas`          | Crea tarea            | 🛡️  |
| Tasks  | GET    | `/api/v1/tareas/{id}`     | Obtener tarea x id    | 🛡️  |
| Tasks  | PUT    | `/api/v1/tareas/{id}`     | Actualizar tarea x id | 🛡️  |
| Tasks  | DELETE | `/api/v1/tareas/{id}`     | Eliminar tarea        | 🛡️  |


------------------------------------------------------------------------

# Ejemplos cURL
## Ejemplos de Requests


## Create un nuevo usuario (si es necesario)

```bash
curl -X POST http://localhost:8080/api/v1/auth/register \
    -H "Content-Type: application/json" \
    -d '{
        "username": "usuario",
        "password": "contraseña",
        }'
```

### Respuesta esperada:
```bash
{
"success": true,
"message": "Usuario registrado con éxito",
"data": "usuario"
}
```      

## Autenticación de usuario (JWT)

```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "usuario",
    "password": "contraseña"
  }'
```

### Respuesta esperada:

```json
{
    "success": true,
    "message": "Inicio de sesion con éxito",
    "data": "YOUR_JWT_TOKEN..."
}
``` 
### !Importante - El token JWT obtenido en la respuesta  "token":YOUR_JWT_TOKEN... debe ser incluido en el header de autorización para acceder a los endpoints protegidos.

-------------------------------------------------------------------------

### GET - Obtener todas las tareas 

```bash
curl -X GET http://localhost:8080/api/v1/tareas \
  -H "Authorization: Bearer YOUR_JWT_TOKEN..."
```

**Respuesta esperada:**

```json
[
  {
    "id": 1,
    "description": "Tarea1",
    "state": {
      "id": 1,
      "nameState": "On Progress"
    }
  },
  {
        "id": 2,
        "description": "Tarea2",
        "state": {
        "id": 2,
        "nameState": "Completed"
        }
    },
  {
        "id": 3,
        "description": "Tarea3",
        "state": {
        "id": 3,
        "nameState": "Incompleted"
        }
    }
]
```

## GET - Obtener Tarea por ID

```bash
curl -X GET http://localhost:8080/api/v1/tareas/{id_tarea} \
  -H "Authorization: Bearer YOUR_JWT_TOKEN..."
```

**Respuesta esperada:**

```bash
{
  "id": 2,
  "description": "Tarea2",
  "state": {
    "id": 2,
    "nameState": "Completed"
  }
}
```


## POST - Crear nueva Tarea
```bash
curl -X POST http://localhost:8080/api/v1/tareas \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN..." \
  -d '{
    "nombre": "Nuevo Tarea",
    "descripcion": "Descripción del nuevo recurso"
  }'
```

**Respuesta esperada:**
```bash
TareaAgregadaconéxito
```

## PUT - Actualizar Tarea existente

```bash
curl -X PUT http://localhost:8080/api/v1/tareas/{id_tarea} \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN..." \
  -d '{
    "nombre": "Tarea Actualizado",
    "descripcion": "Nueva descripción"
  }'
```

**Respuesta esperada:**

```bash
{
  "success": false,
  "message": "Ocurrió un error inesperado.",
  "data": "Request method 'PUT' is not supported"
}
```

## DELETE - Eliminar Tarea

```bash
curl -X DELETE http://localhost:8080/api/v1/tareas/{id_tarea} \
  -H "Authorization: Bearer YOUR_JWT_TOKEN..."
```

**Respuesta esperada:**
```bash
204 No Content (sin cuerpo de respuesta)
```

-------------------------------------------------------------------------

## 📁 Estructura del Proyecto

```
src/
├── main/
│   ├── java/
│   │   └── com/previred.desafioGestionTareas/
│   │       ├── configs/
│   │       ├── controller/
│   │       ├── dtos/
│   │       ├── entities/
│   │       ├── exceptions/
│   │       ├── persistence/
│   │       ├── repositories/
│   │       ├── security/
│   │       ├── services/
│   │       ├── utiles/
│   │       └── DesafioGestionTareasApplication.java
│   └── resources/
│       ├── application.properties
│       ├── application-dev.properties
│       └── Import.sql
└── test/
    └── java/
```

------------------------------------------------------------------------

## 🏗️ Arquitectura del Proyecto Patron Repository

``` plaintext
┌─────────────────────────┐
│       Controller        │  → Maneja solicitudes HTTP (Request/Response)
│       (usa DTO)         │
└───────────┬─────────────┘
            │
┌───────────▼─────────────┐
│         Service         │  → Lógica de negocio
│  (convierte DTO ↔ Entity)│
└───────────┬─────────────┘
            │
┌───────────▼─────────────┐
│           DAO           │  → Interfaz de acceso a datos
│  (implementado por      │
│     Repository JPA)     │
└───────────┬─────────────┘
            │
┌───────────▼─────────────┐
│        Repository        │  → JpaRepository / Spring Data
└───────────┬─────────────┘
            │
┌───────────▼─────────────┐
│        Database          │ → Base de datos (H2 en este caso)
└──────────────────────────┘
```

------------------------------------------------------------------------

# 🛡️ Flujo de Autenticación JWT

``` plaintext
[Cliente]
    |
    | POST /auth/login (username + password)
    v
[API - AuthController]
    |
    | Recibe Petición
    v
[AuthService]
    |
    | Valida Credenciales,Genera JWT
    v
[API responde token]
    |
    | Cliente envía: Authorization: Bearer <token>
    v
[Filtro JWT valida token en cada request]
```

------------------------------------------------------------------------

## 📮 Importar Colección de Postman

### Paso 1: Descargar la colección

La colección de Postman se encuentra en el repositorio en la ruta:

📁 postman/ └── desafioGestionTareas.postman_collection.json


### Paso 2: Abrir Postman

1. Abre la aplicación **Postman** (descárgala desde [postman.com](https://www.postman.com/downloads/) si no la tienes)
2. Inicia sesión o salta este paso

### Paso 3: Importar la colección

**Opción A: Desde el menú**

1. Click en el botón **Import** (esquina superior izquierda)
2. Selecciona la pestaña **File**
3. Click en **Upload Files**
4. Navega a `postman/desafioGestionTareas.postman_collection.json`
5. Click en **Abrir**
6. Click en **Import**

**Opción B: Arrastra y suelta**

1. Abre la carpeta que contiene el archivo `desafioGestionTareas.postman_collection.json`
2. Arrastra el archivo directamente a la ventana de Postman
3. Click en **Import**

### Paso 4: Configurar variables de entorno (Opcional)

Para no repetir la URL base en cada request:

1. Click en **Environments** (lado izquierdo)
2. Click en el botón **+** para crear un nuevo ambiente
3. Asigna el nombre: `desarrollo`
4. Añade las siguientes variables:

| Variable      | Valor              |
|---------------|------------------|
| `base_url`    | `http://localhost:8080` |
| `token`       | *(se llena al hacer login)* |

5. Click en **Save**
6. Selecciona el ambiente `desarrollo` en el dropdown superior derecho

### Paso 5: Usar la colección

1. En el panel izquierdo verás todas las carpetas de endpoints
2. Expande **Auth** → Click en **Login**
3. Click en el botón **Send**
4. Copia el valor del `token` de la respuesta
5. Ve a **Variables de entorno** → **desarrollo** → pega el token en la variable `token`
6. Ahora puedes usar los otros endpoints protegidos

### 💡 Tip: Auto-capturar el token

Para automatizar la captura del token tras el login:

1. Abre el request **Login** en la colección
2. Click en la pestaña **Tests**
3. Añade el siguiente script:

```javascript
if (pm.response.code === 200) {
    var jsonData = pm.response.json();
    pm.environment.set("token", jsonData.token);
}
```

------------------------------------------------------------------------

## 📚 Documentación OpenAPI / Swagger

### ¿Qué es OpenAPI?

OpenAPI (antes conocido como Swagger) es una especificación estándar para describir APIs REST. Permite:
- Visualizar todos los endpoints de forma interactiva
- Probar requests directamente desde el navegador
- Generar documentación automáticamente
- Facilitar la integración con herramientas de terceros

### Paso 1: Acceder a Swagger UI

Una vez que la aplicación está corriendo en `http://localhost:8080/swagger-ui/index.html`, accede a: