# TaesMotors Backend

## Descripción
TaesMotors es un sistema backend diseñado para gestionar las operaciones de un concesionario de vehículos. Este proyecto está construido utilizando Java y Spring Boot, ofreciendo una API robusta para interactuar con la aplicación del cliente.

## Cómo Empezar

### Requisitos Previos
- Java JDK (versión recomendada 11 o superior)
- Maven (para la gestión de dependencias y construcción del proyecto)
- Postman (opcional, para pruebas de API)

### Instalación y Ejecución
1. Clonar el repositorio: git clone https://github.com/Javivzk/TaesMotors.git
2. Navegar al directorio del proyecto: cd taesmotors
3. Ejecutar el proyecto usando Maven: mvn spring-boot:run
4. El proyecto se ejecutará en el puerto 8080 por defecto. Para cambiar el puerto, modificar el archivo application.properties.
5. Para probar la coleccion de postman es necesario que generes un token y lo agregues en la carpeta raiz en auth , Bearer Token.
6. Añado un archivo sql para que puedas probar la api con datos de prueba.

## Estructura del Proyecto
- **.git/.gitignore**: Configuraciones de control de versiones.
- **.idea**: Configuraciones del IDE.
- **.mvn, mvnw, mvnw.cmd**: Scripts y herramientas para Maven Wrapper.
- **cert**: Certificados de seguridad.
- **docs**: Documentación del proyecto.
- **database**: Base de datos del proyecto.
- **logs**: Archivos de registro.
- **pom.xml**: Configuración de Maven.
- **postman**: Colecciones de Postman para pruebas de API.
- **src**: Código fuente del proyecto.
- **system.properties**: Configuraciones del sistema.
- **target**: Artefactos compilados por Maven.

## Arquitectura del Proyecto
La arquitectura del backend de "taesmotors" se basa en un diseño modular y escalable, utilizando Java y Spring Boot para implementar una API RESTful. A continuación, se describen los componentes clave:

- API RESTful: La interfaz principal para la interacción con el frontend, proporcionando endpoints para las operaciones CRUD (Crear, Leer, Actualizar, Eliminar), autenticación, y otras funciones del negocio.
- Capa de Servicios: Contiene la lógica de negocios y se comunica con la capa de persistencia de datos. Se encarga de procesar las solicitudes de la API, ejecutar la lógica del negocio y retornar los resultados.
- Capa de Datos: Gestiona la persistencia de datos, incluyendo el acceso a bases de datos y la manipulación de entidades. Utiliza ModelMapper para mapear objetos a registros de base de datos.
- Seguridad: Implementa mecanismos de autenticación y autorización con JWT para proteger los endpoints de la API.
- Logging y Monitoreo: Registra las actividades del sistema y monitorea el rendimiento y los posibles errores para facilitar el mantenimiento y la depuración.

## Tecnologías
- Java
- Spring Boot
- Maven
- MySQL
- Hibernate
- Spring Security
- JWT
- Lombok
- Log4j
- Postman
- Git
## Autor
- Javier Vizcaino Olivares
