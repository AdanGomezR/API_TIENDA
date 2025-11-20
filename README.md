# API Tienda - Sistema de Gestión de Productos y Clientes

API REST desarrollada con Spring Boot para la gestión de productos y clientes de una tienda.

## 🚀 Características

- ✅ CRUD completo para Productos y Clientes
- ✅ Validación de datos con Bean Validation
- ✅ Manejo global de errores
- ✅ Documentación interactiva con Swagger/OpenAPI
- ✅ Base de datos H2 (desarrollo) y MySQL (producción)
- ✅ Logging configurado
- ✅ Arquitectura en capas (Controller, Service, Repository, Entity, DTO)

## 🛠️ Tecnologías

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **Spring Validation**
- **Lombok**
- **H2 Database** (desarrollo)
- **MySQL** (producción)
- **SpringDoc OpenAPI** (Swagger UI)
- **Maven**

## 📦 Estructura del Proyecto

```
com.miempresa.api_tienda
├── controller/      # Controladores REST
├── service/         # Lógica de negocio
├── repository/      # Acceso a datos (JPA)
├── entity/          # Entidades JPA
├── dto/             # Objetos de transferencia de datos
├── exception/       # Manejo de excepciones
├── config/          # Configuraciones
└── security/        # Configuración de seguridad
```

## 🚦 Ejecución

### Requisitos
- Java 17 o superior
- Maven 3.6+

### Ejecutar la aplicación

```bash
mvn spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

### Compilar el proyecto

```bash
mvn clean install
```

### Ejecutar tests

```bash
mvn test
```

## 📚 Documentación API

Una vez que la aplicación esté en ejecución, puedes acceder a la documentación interactiva de la API:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs (JSON)**: http://localhost:8080/api-docs

## 🗄️ Base de Datos

### H2 Console (Desarrollo)
- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:tienda_db`
- Usuario: `sa`
- Contraseña: (vacía)

### MySQL (Producción)

Para usar MySQL, ejecuta la aplicación con el perfil `mysql`:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```

Configura la base de datos MySQL en `application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/tienda_db
    username: root
    password: root
```

## 🔌 Endpoints de la API

### Productos
- `GET /api/productos` - Listar todos los productos
- `GET /api/productos/{id}` - Obtener producto por ID
- `POST /api/productos` - Crear nuevo producto
- `PUT /api/productos/{id}` - Actualizar producto
- `DELETE /api/productos/{id}` - Eliminar producto
- `GET /api/productos/buscar/nombre?nombre=` - Buscar por nombre
- `GET /api/productos/buscar/categoria?categoria=` - Buscar por categoría

### Clientes
- `GET /api/clientes` - Listar todos los clientes
- `GET /api/clientes/{id}` - Obtener cliente por ID
- `POST /api/clientes` - Crear nuevo cliente
- `PUT /api/clientes/{id}` - Actualizar cliente
- `DELETE /api/clientes/{id}` - Eliminar cliente
- `GET /api/clientes/buscar/email?email=` - Buscar por email
- `GET /api/clientes/buscar/nombre?nombre=` - Buscar por nombre

## 📝 Ejemplos de Uso

### Crear un Producto

```bash
curl -X POST http://localhost:8080/api/productos \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Laptop HP",
    "descripcion": "Laptop HP 15 pulgadas",
    "precio": 799.99,
    "stock": 10,
    "categoria": "Electrónica"
  }'
```

### Crear un Cliente

```bash
curl -X POST http://localhost:8080/api/clientes \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan",
    "apellido": "Pérez",
    "email": "juan.perez@example.com",
    "telefono": "+34 123 456 789",
    "direccion": "Calle Principal 123",
    "ciudad": "Madrid",
    "pais": "España"
  }'
```

## 🔒 Seguridad

El paquete `security` está preparado para futuras implementaciones de seguridad como:
- Autenticación JWT
- Control de acceso basado en roles
- Spring Security
- OAuth2

## 📊 Logging

Los logs están configurados con nivel DEBUG para el paquete de la aplicación y pueden consultarse en la consola.

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu funcionalidad (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -m 'Añadir nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia Apache 2.0.
