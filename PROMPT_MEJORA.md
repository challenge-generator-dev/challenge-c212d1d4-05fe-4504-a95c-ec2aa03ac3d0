# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `Dockerfile` — El topic pide contenedores/orquestacion: este archivo es el ejercicio, no scaffolding.
- `docker-compose.yml` — El topic pide contenedores/orquestacion: este archivo es el ejercicio, no scaffolding.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/backend/infrastructure/adapter/ProductJpaRepository.java` — `com.pragma.backend.infrastructure.entity.ProductEntity`: El import com.pragma.backend.infrastructure.entity.ProductEntity usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/pragma/backend/infrastructure/adapter/ProductJpaRepository.java` — `com.pragma.backend.infrastructure.mapper.ProductMapper`: El import com.pragma.backend.infrastructure.mapper.ProductMapper usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/pragma/backend/application/service/ProductService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/backend/infrastructure/controller/ProductController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/backend/infrastructure/config/LoggingConfig.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/backend/infrastructure/exception/GlobalExceptionHandler.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/backend/infrastructure/controller/ProductControllerTest.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/backend/application/service/ProductService.java` — `Product.id`: Se invoca `id` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/application/service/ProductService.java` — `Product.name`: Se invoca `name` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/application/service/ProductService.java` — `Product.description`: Se invoca `description` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/application/service/ProductService.java` — `Product.price`: Se invoca `price` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/application/service/ProductService.java` — `Product.category`: Se invoca `category` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/application/service/ProductService.java` — `Product.stock`: Se invoca `stock` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/application/service/ProductService.java` — `Product.active`: Se invoca `active` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/application/service/ProductService.java` — `Product.createdAt`: Se invoca `createdAt` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/infrastructure/controller/ProductController.java` — `Product.id`: Se invoca `id` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/infrastructure/controller/ProductController.java` — `Product.name`: Se invoca `name` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/infrastructure/controller/ProductController.java` — `Product.description`: Se invoca `description` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/infrastructure/controller/ProductController.java` — `Product.price`: Se invoca `price` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/infrastructure/controller/ProductController.java` — `Product.category`: Se invoca `category` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/infrastructure/controller/ProductController.java` — `Product.stock`: Se invoca `stock` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/infrastructure/controller/ProductController.java` — `Product.active`: Se invoca `active` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/infrastructure/controller/ProductController.java` — `Product.createdAt`: Se invoca `createdAt` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/infrastructure/controller/ProductController.java` — `Product.updatedAt`: Se invoca `updatedAt` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/infrastructure/controller/ProductController.java` — `UpdateProductRequest.name`: Se invoca `name` sobre `UpdateProductRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/infrastructure/controller/ProductController.java` — `UpdateProductRequest.description`: Se invoca `description` sobre `UpdateProductRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/infrastructure/controller/ProductController.java` — `UpdateProductRequest.price`: Se invoca `price` sobre `UpdateProductRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/infrastructure/controller/ProductController.java` — `UpdateProductRequest.category`: Se invoca `category` sobre `UpdateProductRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/infrastructure/controller/ProductController.java` — `UpdateProductRequest.stock`: Se invoca `stock` sobre `UpdateProductRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setError`: Se invoca `setError` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setMessage`: Se invoca `setMessage` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setStatus`: Se invoca `setStatus` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setTimestamp`: Se invoca `setTimestamp` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setPath`: Se invoca `setPath` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setCorrelationId`: Se invoca `setCorrelationId` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/backend/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setFieldErrors`: Se invoca `setFieldErrors` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced

### Brecha de conocimiento
Experiencia comprobable en al menos un marco de trabajo (Framework) en su lenguaje de programación principal (por ejemplo, Spring Framework en Java o ASP .net core en C#) y puede realizar el enrutamiento de solicitudes, controlar las variables de entorno, generar registros de rastros (Logs) y conectarse con bases de datos utilizando un Mapeador Relacional de Objetos (ORM).

### Misión / candidato
El candidato es un desarrollador Backend Advanced con experiencia en estructuras de datos y patrones GRASP. Está listo para profundizar en frameworks como Spring Boot.

### Reto
- Tema: Experiencia en Frameworks
- Seniority: advanced-l2
- Tipo: practical
- Título: Implementación de un Servicio Backend con Framework
- Tiempo estimado: 15 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Configuración del Entorno — objetivo: Configurar el entorno de desarrollo y el framework seleccionado para el proyecto. — entregable (NO resolver): Entorno de desarrollo configurado con el framework y variables de entorno establecidas.
- Fase 2: Implementación del Servicio — objetivo: Implementar el servicio backend que maneje solicitudes y genere logs. — entregable (NO resolver): Servicio backend con endpoints funcionales y generación de logs.
- Fase 3: Conexión con Base de Datos — objetivo: Conectar el servicio a una base de datos utilizando un ORM. — entregable (NO resolver): Servicio backend conectado a una base de datos con operaciones CRUD funcionales.
- Fase 4: Escalabilidad y Manejo de Picos de Carga — objetivo: Asegurar que el servicio sea escalable y pueda manejar picos de carga. — entregable (NO resolver): Servicio backend escalable y preparado para manejar picos de carga.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>backend</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>backend</name>
    <description>Servicio backend para gestion de productos</description>

    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Database -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
            <version>42.7.3</version>
        </dependency>

        <!-- Flyway -->
        <dependency>
            <groupId>org.flywaydb</groupId>
            <artifactId>flyway-core</artifactId>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.34</version>
            <scope>provided</scope>
        </dependency>

        <!-- Configuration Processor -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <scope>provided</scope>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>postgresql</artifactId>
            <scope>test</scope>
            <version>1.20.1</version>
        </dependency>

        <!-- Monitoring -->
        <dependency>
            <groupId>io.micrometer</groupId>
            <artifactId>micrometer-registry-prometheus</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

    <repositories>
        <repository>
            <id>spring-milestones</id>
            <name>Spring Milestones</name>
            <url>https://repo.spring.io/milestone</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>
    </repositories>
</project>

//

// === ARCHIVO: src/main/java/com/pragma/backend/BackendApplication.java ===
package com.pragma.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(true);
        filter.setAfterMessagePrefix("REQUEST DATA: ");
        return filter;
    }
}

//

// === ARCHIVO: src/main/resources/application.yml ===
server:
  port: 8080
  servlet:
    context-path: /api/v1

spring:
  application:
    name: backend-service
  profiles:
    active: dev
  datasource:
    url: jdbc:postgresql://localhost:5432/backend_db
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 10
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
  jpa:
    hibernate:
      ddl-auto: validate
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
        show_sql: true
        jdbc:
          batch_size: 20
          order_inserts: true
          order_updates: true
  flyway:
    enabled: true
    locations: classpath:db/migration
    baseline-on-migrate: true
    validate-on-migrate: true

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      show-details: always
  metrics:
    tags:
      application: ${spring.application.name}

logging:
  level:
    root: INFO
    org.springframework.web: DEBUG
    com.pragma.backend: DEBUG
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} - %msg%n"
    file: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
  file:
    name: ./logs/backend-service.log

// === ARCHIVO: src/main/java/com/pragma/backend/domain/model/Product.java ===
package com.pragma.backend.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record Product(
    UUID id,
    String name,
    String description,
    BigDecimal price,
    Integer stock,
    String category,
    LocalDate createdAt,
    LocalDate updatedAt,
    boolean active
) {
    public Product {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be positive");
        }
        if (stock == null || stock < 0) {
            throw new IllegalArgumentException("Product stock cannot be negative");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Product category cannot be null or empty");
        }
    }

    public Product withId(UUID id) {
        return new Product(id, this.name, this.description, this.price, this.stock, this.category, this.createdAt, this.updatedAt, this.active);
    }

    public Product withUpdatedAt(LocalDate updatedAt) {
        return new Product(this.id, this.name, this.description, this.price, this.stock, this.category, this.createdAt, updatedAt, this.active);
    }

    public Product activate() {
        return new Product(this.id, this.name, this.description, this.price, this.stock, this.category, this.createdAt, this.updatedAt, true);
    }

    public Product deactivate() {
        return new Product(this.id, this.name, this.description, this.price, this.stock, this.category, this.createdAt, this.updatedAt, false);
    }
}

// === ARCHIVO: src/main/java/com/pragma/backend/domain/port/ProductRepository.java ===
package com.pragma.backend.domain.port;

import com.pragma.backend.domain.model.Product;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    Product save(Product product);
    
    Optional<Product> findById(UUID id);
    
    List<Product> findAll();
    
    List<Product> findByCategory(String category);
    
    List<Product> findByActive(boolean active);
    
    void deleteById(UUID id);
    
    boolean existsById(UUID id);
}

// === ARCHIVO: src/main/java/com/pragma/backend/infrastructure/adapter/ProductJpaRepository.java ===
package com.pragma.backend.infrastructure.adapter;

import com.pragma.backend.domain.model.Product;
import com.pragma.backend.domain.port.ProductRepository;
import com.pragma.backend.infrastructure.entity.ProductEntity;
import com.pragma.backend.infrastructure.mapper.ProductMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public interface ProductJpaRepository extends ProductRepository, JpaRepository<ProductEntity, UUID> {
    @Override
    default Product save(Product product) {
        ProductEntity entity = ProductMapper.INSTANCE.toEntity(product);
        ProductEntity savedEntity = save(entity);
        return ProductMapper.INSTANCE.toDomain(savedEntity);
    }
    
    @Override
    default Optional<Product> findById(UUID id) {
        return findById(id).map(ProductMapper.INSTANCE::toDomain);
    }
    
    @Override
    default List<Product> findAll() {
        return findAll().stream()
                .map(ProductMapper.INSTANCE::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    default List<Product> findByCategory(String category) {
        return findByCategory(category).stream()
                .map(ProductMapper.INSTANCE::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    default List<Product> findByActive(boolean active) {
        return findByActive(active).stream()
                .map(ProductMapper.INSTANCE::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    default void deleteById(UUID id) {
        deleteById(id);
    }
    
    @Override
    default boolean existsById(UUID id) {
        return existsById(id);
    }
    
    List<ProductEntity> findByCategory(String category);
    
    List<ProductEntity> findByActive(boolean active);
}

// === ARCHIVO: src/main/java/com/pragma/backend/application/service/ProductService.java ===
package com.pragma.backend.application.service;

import com.pragma.backend.domain.model.Product;
import com.pragma.backend.domain.port.ProductRepository;
import com.pragma.backend.infrastructure.exception.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product createProduct(String name, String description, BigDecimal price, 
                                  String category, int stock) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Iniciando creacion de producto - nombre: {}, categoria: {}", name, category);

        Product product = new Product(
            UUID.randomUUID(),
            name,
            description,
            price,
            category,
            stock,
            true,
            LocalDate.now(),
            LocalDate.now()
        );

        Product savedProduct = productRepository.save(product);
        logger.info("Producto creado exitosamente - id: {}, nombre: {}", savedProduct.id(), savedProduct.name());
        MDC.clear();
        return savedProduct;
    }

    @Transactional(readOnly = true)
    public Product getProductById(UUID id) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Buscando producto por id: {}", id);

        Product product = productRepository.findById(id)
            .orElseThrow(() -> {
                logger.warn("Producto no encontrado - id: {}", id);
                return new ProductNotFoundException("Producto no encontrado con id: " + id);
            });

        logger.info("Producto encontrado - id: {}, nombre: {}", product.id(), product.name());
        MDC.clear();
        return product;
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Obteniendo todos los productos");

        List<Product> products = productRepository.findAll();
        logger.info("Total de productos encontrados: {}", products.size());
        MDC.clear();
        return products;
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsByCategory(String category) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Buscando productos por categoria: {}", category);

        List<Product> products = productRepository.findByCategory(category);
        logger.info("Productos encontrados en categoria {}: {}", category, products.size());
        MDC.clear();
        return products;
    }

    @Transactional(readOnly = true)
    public List<Product> getActiveProducts(boolean active) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Buscando productos activos: {}", active);

        List<Product> products = productRepository.findByActive(active);
        logger.info("Productos encontrados con estado {}: {}", active, products.size());
        MDC.clear();
        return products;
    }

    @Transactional
    public Product updateProduct(UUID id, String name, String description, 
                                  BigDecimal price, String category, int stock) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Actualizando producto - id: {}, nombre: {}", id, name);

        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> {
                logger.warn("Producto no encontrado para actualizacion - id: {}", id);
                return new ProductNotFoundException("Producto no encontrado con id: " + id);
            });

        Product updatedProduct = new Product(
            existingProduct.id(),
            name != null ? name : existingProduct.name(),
            description != null ? description : existingProduct.description(),
            price != null ? price : existingProduct.price(),
            category != null ? category : existingProduct.category(),
            stock >= 0 ? stock : existingProduct.stock(),
            existingProduct.active(),
            existingProduct.createdAt(),
            LocalDate.now()
        );

        Product savedProduct = productRepository.save(updatedProduct);
        logger.info("Producto actualizado exitosamente - id: {}", savedProduct.id());
        MDC.clear();
        return savedProduct;
    }

    @Transactional
    public Product activateProduct(UUID id) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Activando producto - id: {}", id);

        Product product = productRepository.findById(id)
            .orElseThrow(() -> {
                logger.warn("Producto no encontrado para activacion - id: {}", id);
                return new ProductNotFoundException("Producto no encontrado con id: " + id);
            });

        Product activatedProduct = product.activate();
        Product savedProduct = productRepository.save(activatedProduct);
        logger.info("Producto activado - id: {}", savedProduct.id());
        MDC.clear();
        return savedProduct;
    }

    @Transactional
    public Product deactivateProduct(UUID id) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Desactivando producto - id: {}", id);

        Product product = productRepository.findById(id)
            .orElseThrow(() -> {
                logger.warn("Producto no encontrado para desactivacion - id: {}", id);
                return new ProductNotFoundException("Producto no encontrado con id: " + id);
            });

        Product deactivatedProduct = product.deactivate();
        Product savedProduct = productRepository.save(deactivatedProduct);
        logger.info("Producto desactivado - id: {}", savedProduct.id());
        MDC.clear();
        return savedProduct;
    }

    @Transactional
    public void deleteProduct(UUID id) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Eliminando producto - id: {}", id);

        if (!productRepository.existsById(id)) {
            logger.warn("Producto no encontrado para eliminacion - id: {}", id);
            throw new ProductNotFoundException("Producto no encontrado con id: " + id);
        }

        productRepository.deleteById(id);
        logger.info("Producto eliminado - id: {}", id);
        MDC.clear();
    }

    @Transactional(readOnly = true)
    public boolean existsProduct(UUID id) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.debug("Verificando existencia de producto - id: {}", id);

        boolean exists = productRepository.existsById(id);
        logger.debug("Producto existe - id: {}, resultado: {}", id, exists);
        MDC.clear();
        return exists;
    }
}

// === ARCHIVO: src/main/java/com/pragma/backend/infrastructure/controller/ProductController.java ===
package com.pragma.backend.infrastructure.controller;

import com.pragma.backend.application.service.ProductService;
import com.pragma.backend.domain.model.Product;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public record CreateProductRequest(
        @NotBlank(message = "El nombre es obligatorio") String name,
        @NotBlank(message = "La descripcion es obligatoria") String description,
        @NotNull(message = "El precio es obligatorio") @Positive(message = "El precio debe ser positivo") BigDecimal price,
        @NotBlank(message = "La categoria es obligatoria") String category,
        @NotNull(message = "El stock es obligatorio") @Positive(message = "El stock debe ser positivo") Integer stock
    ) {}

    public record UpdateProductRequest(
        String name,
        String description,
        BigDecimal price,
        String category,
        Integer stock
    ) {}

    public record ProductResponse(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        String category,
        int stock,
        boolean active,
        String createdAt,
        String updatedAt
    ) {
        public static ProductResponse fromDomain(Product product) {
            return new ProductResponse(
                product.id(),
                product.name(),
                product.description(),
                product.price(),
                product.category(),
                product.stock(),
                product.active(),
                product.createdAt().toString(),
                product.updatedAt().toString()
            );
        }
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest request) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Peticion REST - POST /api/v1/products - Creando producto: {}", request.name());

        Product product = productService.createProduct(
            request.name(),
            request.description(),
            request.price(),
            request.category(),
            request.stock()
        );

        logger.info("Producto creado exitosamente - id: {}", product.id());
        MDC.clear();
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductResponse.fromDomain(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable UUID id) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Peticion REST - GET /api/v1/products/{}", id);

        Product product = productService.getProductById(id);
        logger.info("Producto obtenido - id: {}", product.id());
        MDC.clear();
        return ResponseEntity.ok(ProductResponse.fromDomain(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean active) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Peticion REST - GET /api/v1/products - category: {}, active: {}", category, active);

        List<Product> products;
        if (category != null && !category.isBlank()) {
            products = productService.getProductsByCategory(category);
        } else if (active != null) {
            products = productService.getActiveProducts(active);
        } else {
            products = productService.getAllProducts();
        }

        List<ProductResponse> responses = products.stream()
            .map(ProductResponse::fromDomain)
            .toList();

        logger.info("Productos obtenidos - cantidad: {}", responses.size());
        MDC.clear();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateProductRequest request) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Peticion REST - PUT /api/v1/products/{} - Actualizando producto", id);

        Product product = productService.updateProduct(
            id,
            request.name(),
            request.description(),
            request.price(),
            request.category(),
            request.stock()
        );

        logger.info("Producto actualizado - id: {}", product.id());
        MDC.clear();
        return ResponseEntity.ok(ProductResponse.fromDomain(product));
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<ProductResponse> activateProduct(@PathVariable UUID id) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Peticion REST - PATCH /api/v1/products/{}/activate", id);

        Product product = productService.activateProduct(id);
        logger.info("Producto activado - id: {}", product.id());
        MDC.clear();
        return ResponseEntity.ok(ProductResponse.fromDomain(product));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ProductResponse> deactivateProduct(@PathVariable UUID id) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Peticion REST - PATCH /api/v1/products/{}/deactivate", id);

        Product product = productService.deactivateProduct(id);
        logger.info("Producto desactivado - id: {}", product.id());
        MDC.clear();
        return ResponseEntity.ok(ProductResponse.fromDomain(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Peticion REST - DELETE /api/v1/products/{}", id);

        productService.deleteProduct(id);
        logger.info("Producto eliminado - id: {}", id);
        MDC.clear();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existsProduct(@PathVariable UUID id) {
        String correlationId = UUID.randomUUID().toString();
        MDC.put("correlationId", correlationId);
        logger.info("Peticion REST - GET /api/v1/products/{}/exists", id);

        boolean exists = productService.existsProduct(id);
        logger.info("Verificacion de existencia - id: {}, resultado: {}", id, exists);
        MDC.clear();
        return ResponseEntity.ok(exists);
    }
}

// === ARCHIVO: src/main/java/com/pragma/backend/infrastructure/config/LoggingConfig.java ===
package com.pragma.backend.infrastructure.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Configuration
public class LoggingConfig {

    private static final Logger logger = LoggerFactory.getLogger(LoggingConfig.class);
    private static final String CORRELATION_ID_HEADER = "X-Correlation-ID";
    private static final String CORRELATION_ID_MDC_KEY = "correlationId";
    private static final String USER_ID_MDC_KEY = "userId";
    private static final String REQUEST_URI_KEY = "requestUri";
    private static final String HTTP_METHOD_KEY = "httpMethod";

    @Bean
    public FilterRegistrationBean<OncePerRequestFilter> loggingFilter() {
        FilterRegistrationBean<OncePerRequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, 
                                            HttpServletResponse response, 
                                            FilterChain filterChain) 
                    throws ServletException, IOException {
                String correlationId = getOrGenerateCorrelationId(request);
                MDC.put(CORRELATION_ID_MDC_KEY, correlationId);
                MDC.put(REQUEST_URI_KEY, request.getRequestURI());
                MDC.put(HTTP_METHOD_KEY, request.getMethod());

                long startTime = System.currentTimeMillis();
                logger.info("Peticion recibida - metodo: {}, uri: {}, correlationId: {}", 
                    request.getMethod(), request.getRequestURI(), correlationId);

                try {
                    filterChain.doFilter(request, response);
                } finally {
                    long duration = System.currentTimeMillis() - startTime;
                    logger.info("Peticion completada - metodo: {}, uri: {}, status: {}, duracion: {}ms, correlationId: {}", 
                        request.getMethod(), 
                        request.getRequestURI(), 
                        response.getStatus(), 
                        duration, 
                        correlationId);
                    MDC.clear();
                }
            }

            private String getOrGenerateCorrelationId(HttpServletRequest request) {
                String correlationId = request.getHeader(CORRELATION_ID_HEADER);
                if (correlationId == null || correlationId.isBlank()) {
                    correlationId = UUID.randomUUID().toString();
                }
                return correlationId;
            }
        });

        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setName("loggingFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<OncePerRequestFilter> mdcFilter() {
        FilterRegistrationBean<OncePerRequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, 
                                            HttpServletResponse response, 
                                            FilterChain filterChain) 
                    throws ServletException, IOException {
                String correlationId = request.getHeader(CORRELATION_ID_HEADER);
                if (correlationId == null || correlationId.isBlank()) {
                    correlationId = UUID.randomUUID().toString();
                }
                response.setHeader(CORRELATION_ID_HEADER, correlationId);
                filterChain.doFilter(request, response);
            }
        });

        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("mdcFilter");
        registrationBean.setOrder(2);
        return registrationBean;
    }
}

// === ARCHIVO: src/main/java/com/pragma/backend/infrastructure/exception/ProductNotFoundException.java ===
package com.pragma.backend.infrastructure.exception;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {

    private final UUID productId;
    private final String productName;
    private final long timestamp;

    public ProductNotFoundException(UUID productId) {
        super(buildMessage(null, productId, null));
        this.productId = productId;
        this.productName = null;
        this.timestamp = System.currentTimeMillis();
        validateInvariant();
    }

    public ProductNotFoundException(String productName) {
        super(buildMessage(productName, null, null));
        this.productId = null;
        this.productName = productName;
        this.timestamp = System.currentTimeMillis();
        validateInvariant();
    }

    public ProductNotFoundException(UUID productId, String additionalContext) {
        super(buildMessage(null, productId, additionalContext));
        this.productId = productId;
        this.productName = null;
        this.timestamp = System.currentTimeMillis();
        validateInvariant();
    }

    private static String buildMessage(String productName, UUID productId, String context) {
        StringBuilder sb = new StringBuilder("Producto no encontrado");
        if (productId != null) {
            sb.append(": ID=").append(productId);
        } else if (productName != null && !productName.isBlank()) {
            sb.append(": nombre='").append(productName).append("'");
        }
        if (context != null && !context.isBlank()) {
            sb.append(" (").append(context).append(")");
        }
        return sb.toString();
    }

    private void validateInvariant() {
        if (productId == null && (productName == null || productName.isBlank())) {
            throw new IllegalStateException("ProductNotFoundException debe tener al menos productId o productName");
        }
    }

    public UUID getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getMessageId() {
        if (productId != null) {
            return "PROD_NOT_FOUND_" + productId.toString().substring(0, 8).toUpperCase();
        }
        return "PROD_NOT_FOUND_" + (productName != null ? productName.toUpperCase().replace(" ", "_") : "UNKNOWN");
    }
}

// === ARCHIVO: src/main/java/com/pragma/backend/infrastructure/exception/GlobalExceptionHandler.java ===
package com.pragma.backend.infrastructure.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String CORRELATION_ID_HEADER = "X-Correlation-ID";

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(
            ProductNotFoundException ex, WebRequest request) {
        String correlationId = getOrGenerateCorrelationId(request);
        MDC.put("correlationId", correlationId);
        MDC.put("exception", "ProductNotFoundException");

        log.warn("Producto no encontrado - CorrelationId: {}, ProductId: {}, Message: {}",
                correlationId, ex.getProductId(), ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("PRODUCT_NOT_FOUND")
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(Instant.now().toString())
                .path(request.getDescription(false).replace("uri=", ""))
                .correlationId(correlationId)
                .build();

        MDC.clear();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex, WebRequest request) {
        String correlationId = getOrGenerateCorrelationId(request);
        MDC.put("correlationId", correlationId);
        MDC.put("exception", "ValidationException");

        log.warn("Error de validacion - CorrelationId: {}, Errors: {}",
                correlationId, ex.getBindingResult().getFieldErrors().size());

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage())
        );

        ValidationErrorResponse response = ValidationErrorResponse.builder()
                .error("VALIDATION_ERROR")
                .message("Error en los datos enviados")
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(Instant.now().toString())
                .path(request.getDescription(false).replace("uri=", ""))
                .correlationId(correlationId)
                .fieldErrors(fieldErrors)
                .build();

        MDC.clear();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex, WebRequest request) {
        String correlationId = getOrGenerateCorrelationId(request);
        MDC.put("correlationId", correlationId);
        MDC.put("exception", "IllegalArgumentException");

        log.error("Argumento invalido - CorrelationId: {}, Message: {}",
                correlationId, ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("BAD_REQUEST")
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(Instant.now().toString())
                .path(request.getDescription(false).replace("uri=", ""))
                .correlationId(correlationId)
                .build();

        MDC.clear();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, WebRequest request) {
        String correlationId = getOrGenerateCorrelationId(request);
        MDC.put("correlationId", correlationId);
        MDC.put("exception", ex.getClass().getSimpleName());

        log.error("Error interno del servidor - CorrelationId: {}, Type: {}, Message: {}",
                correlationId, ex.getClass().getSimpleName(), ex.getMessage(), ex);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("INTERNAL_SERVER_ERROR")
                .message("Error interno del servidor. Por favor, contacte al administrador.")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(Instant.now().toString())
                .path(request.getDescription(false).replace("uri=", ""))
                .correlationId(correlationId)
                .build();

        MDC.clear();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    private String getOrGenerateCorrelationId(WebRequest request) {
        String correlationId = request.getHeader(CORRELATION_ID_HEADER);
        return correlationId != null ? correlationId : UUID.randomUUID().toString();
    }

    public static class ErrorResponse {
        private String error;
        private String message;
        private int status;
        private String timestamp;
        private String path;
        private String correlationId;

        public static ErrorResponseBuilder builder() {
            return new ErrorResponseBuilder();
        }

        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }
        public String getTimestamp() { return timestamp; }
        public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }
        public String getCorrelationId() { return correlationId; }
        public void setCorrelationId(String correlationId) { this.correlationId = correlationId; }

        public static class ErrorResponseBuilder {
            private String error;
            private String message;
            private int status;
            private String timestamp;
            private String path;
            private String correlationId;

            public ErrorResponseBuilder error(String error) { this.error = error; return this; }
            public ErrorResponseBuilder message(String message) { this.message = message; return this; }
            public ErrorResponseBuilder status(int status) { this.status = status; return this; }
            public ErrorResponseBuilder timestamp(String timestamp) { this.timestamp = timestamp; return this; }
            public ErrorResponseBuilder path(String path) { this.path = path; return this; }
            public ErrorResponseBuilder correlationId(String correlationId) { this.correlationId = correlationId; return this; }

            public ErrorResponse build() {
                ErrorResponse response = new ErrorResponse();
                response.error = this.error;
                response.message = this.message;
                response.status = this.status;
                response.timestamp = this.timestamp;
                response.path = this.path;
                response.correlationId = this.correlationId;
                return response;
            }
        }
    }

    public static class ValidationErrorResponse extends ErrorResponse {
        private Map<String, String> fieldErrors;

        public static ValidationErrorResponseBuilder builder() {
            return new ValidationErrorResponseBuilder();
        }

        public Map<String, String> getFieldErrors() { return fieldErrors; }
        public void setFieldErrors(Map<String, String> fieldErrors) { this.fieldErrors = fieldErrors; }

        public static class ValidationErrorResponseBuilder extends ErrorResponseBuilder {
            private Map<String, String> fieldErrors;

            public ValidationErrorResponseBuilder fieldErrors(Map<String, String> fieldErrors) {
                this.fieldErrors = fieldErrors;
                return this;
            }

            public ValidationErrorResponse build() {
                ValidationErrorResponse response = new ValidationErrorResponse();
                response.setError(error);
                response.setMessage(message);
                response.setStatus(status);
                response.setTimestamp(timestamp);
                response.setPath(path);
                response.setCorrelationId(correlationId);
                response.setFieldErrors(fieldErrors);
                return response;
            }
        }
    }
}

// === ARCHIVO: src/main/resources/db/migration/V1__Create_products_table.sql ===
-- Script de migracion Flyway para crear la tabla de productos
-- Version: V1
-- Descripcion: Crea la tabla products con todas las columnas necesarias para el dominio

CREATE TABLE products (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    category VARCHAR(100) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Indice para busqueda por categoria
CREATE INDEX idx_products_category ON products(category);

-- Indice para busqueda por estado activo
CREATE INDEX idx_products_active ON products(active);

-- Indice compuesto para consultas frecuentes: categoria + estado
CREATE INDEX idx_products_category_active ON products(category, active);

-- Comentario para documentacion
COMMENT ON TABLE products IS 'Tabla principal para gestion de productos del sistema';
COMMENT ON COLUMN products.id IS 'Identificador unico del producto (UUID)';
COMMENT ON COLUMN products.name IS 'Nombre del producto';
COMMENT ON COLUMN products.description IS 'Descripcion detallada del producto';
COMMENT ON COLUMN products.price IS 'Precio del producto con dos decimales de precision';
COMMENT ON COLUMN products.category IS 'Categoria a la que pertenece el producto';
COMMENT ON COLUMN products.active IS 'Indica si el producto esta activo o inactivo para su uso';
COMMENT ON COLUMN products.created_at IS 'Fecha y hora de creacion del registro';
COMMENT ON COLUMN products.updated_at IS 'Fecha y hora de la ultima modificacion del registro';

--插入一些 datos iniciales para pruebas
INSERT INTO products (id, name, description, price, category, active, created_at, updated_at) VALUES
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Laptop Pro 15', 'Laptop de alta gama con procesador Intel i7', 1299.99, 'Electronics', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('b1ffcd99-9d1c-5ef9-cb7c-7cc0ce491b22', 'Wireless Mouse', 'Mouse inalambrico ergonomico', 49.99, 'Electronics', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('c2ggde99-9e2d-6fg0-dc8d-8dd1df502c33', 'Organic Coffee', 'Cafe organico premium de Colombia', 24.99, 'Food', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

// === ARCHIVO: src/test/java/com/pragma/backend/infrastructure/controller/ProductControllerTest.java ===
package com.pragma.backend.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.backend.application.service.ProductService;
import com.pragma.backend.domain.model.Product;
import com.pragma.backend.infrastructure.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@Import(GlobalExceptionHandler.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    private UUID productId;
    private Product product;
    private String productJson;

    @BeforeEach
    void setUp() {
        productId = UUID.randomUUID();
        product = new Product(
            productId,
            "Test Product",
            "Test Description",
            new BigDecimal("99.99"),
            "ELECTRONICS",
            100,
            true,
            LocalDate.now(),
            LocalDate.now()
        );
        productJson = """
            {
                "name": "Test Product",
                "description": "Test Description",
                "price": 99.99,
                "category": "ELECTRONICS",
                "stock": 100
            }
            """;
    }

    @Test
    @DisplayName("GET /api/products debe retornar todos los productos")
    void getAllProducts_ReturnsProductList() throws Exception {
        when(productService.getAllProducts()).thenReturn(List.of(product));

        mockMvc.perform(get("/api/products"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].id").value(productId.toString()))
            .andExpect(jsonPath("$[0].name").value("Test Product"))
            .andExpect(jsonPath("$[0].category").value("ELECTRONICS"));

        verify(productService).getAllProducts();
    }

    @Test
    @DisplayName("GET /api/products/{id} debe retornar un producto por su ID")
    void getProductById_ReturnsProduct() throws Exception {
        when(productService.getProductById(productId)).thenReturn(Optional.of(product));

        mockMvc.perform(get("/api/products/{id}", productId))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(productId.toString()))
            .andExpect(jsonPath("$.name").value("Test Product"))
            .andExpect(jsonPath("$.price").value(99.99));

        verify(productService).getProductById(productId);
    }

    @Test
    @DisplayName("GET /api/products/{id} debe retornar 404 cuando el producto no existe")
    void getProductById_NotFound() throws Exception {
        when(productService.getProductById(productId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/products/{id}", productId))
            .andExpect(status().isNotFound());

        verify(productService).getProductById(productId);
    }

    @Test
    @DisplayName("GET /api/products/category/{category} debe filtrar productos por categoría")
    void getProductsByCategory_ReturnsFilteredList() throws Exception {
        when(productService.getProductsByCategory("ELECTRONICS")).thenReturn(List.of(product));

        mockMvc.perform(get("/api/products/category/{category}", "ELECTRONICS"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].category").value("ELECTRONICS"))
            .andExpect(jsonPath("$[0].name").value("Test Product"));

        verify(productService).getProductsByCategory("ELECTRONICS");
    }

    @Test
    @DisplayName("GET /api/products/active debe retornar solo productos activos")
    void getActiveProducts_ReturnsOnlyActiveProducts() throws Exception {
        when(productService.getActiveProducts()).thenReturn(List.of(product));

        mockMvc.perform(get("/api/products/active"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].active").value(true));

        verify(productService).getActiveProducts();
    }

    @Test
    @DisplayName("POST /api/products debe crear un nuevo producto")
    void createProduct_ReturnsCreated() throws Exception {
        when(productService.createProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value("Test Product"))
            .andExpect(header().string("Location", "/api/products/" + productId));

        verify(productService).createProduct(any(Product.class));
    }

    @Test
    @DisplayName("POST /api/products debe retornar 400 cuando el nombre está vacío")
    void createProduct_ValidationError_NameBlank() throws Exception {
        String invalidJson = """
            {
                "name": "",
                "description": "Test Description",
                "price": 99.99,
                "category": "ELECTRONICS",
                "stock": 100
            }
            """;

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /api/products debe retornar 400 cuando el precio es negativo")
    void createProduct_ValidationError_NegativePrice() throws Exception {
        String invalidJson = """
            {
                "name": "Test Product",
                "description": "Test Description",
                "price": -10.00,
                "category": "ELECTRONICS",
                "stock": 100
            }
            """;

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("PUT /api/products/{id} debe actualizar un producto existente")
    void updateProduct_ReturnsOk() throws Exception {
        Product updatedProduct = new Product(
            productId,
            "Updated Product",
            "Updated Description",
            new BigDecimal("149.99"),
            "ELECTRONICS",
            200,
            true,
            LocalDate.now(),
            LocalDate.now()
        );

        when(productService.updateProduct(eq(productId), any(Product.class))).thenReturn(Optional.of(updatedProduct));

        String updateJson = """
            {
                "name": "Updated Product",
                "description": "Updated Description",
                "price": 149.99,
                "category": "ELECTRONICS",
                "stock": 200
            }
            """;

        mockMvc.perform(put("/api/products/{id}", productId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value("Updated Product"))
            .andExpect(jsonPath("$.price").value(149.99));

        verify(productService).updateProduct(eq(productId), any(Product.class));
    }

    @Test
    @DisplayName("PUT /api/products/{id} debe retornar 404 cuando el producto no existe")
    void updateProduct_NotFound() throws Exception {
        when(productService.updateProduct(eq(productId), any(Product.class))).thenReturn(Optional.empty());

        String updateJson = """
            {
                "name": "Updated Product",
                "description": "Updated Description",
                "price": 149.99,
                "category": "ELECTRONICS",
                "stock": 200
            }
            """;

        mockMvc.perform(put("/api/products/{id}", productId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /api/products/{id} debe eliminar un producto")
    void deleteProduct_ReturnsNoContent() throws Exception {
        doNothing().when(productService).deleteProduct(productId);

        mockMvc.perform(delete("/api/products/{id}", productId))
            .andExpect(status().isNoContent());

        verify(productService).deleteProduct(productId);
    }

    @Test
    @DisplayName("DELETE /api/products/{id} debe retornar 404 cuando el producto no existe")
    void deleteProduct_NotFound() throws Exception {
        doNothing().when(productService).deleteProduct(productId);

        mockMvc.perform(delete("/api/products/{id}", productId))
            .andExpect(status().isNoContent());

        verify(productService).deleteProduct(productId);
    }

    @Test
    @DisplayName("PATCH /api/products/{id}/activate debe activar un producto")
    void activateProduct_ReturnsOk() throws Exception {
        Product activatedProduct = product.activate();
        when(productService.activateProduct(productId)).thenReturn(Optional.of(activatedProduct));

        mockMvc.perform(patch("/api/products/{id}/activate", productId))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.active").value(true));

        verify(productService).activateProduct(productId);
    }

    @Test
    @DisplayName("PATCH /api/products/{id}/deactivate debe desactivar un producto")
    void deactivateProduct_ReturnsOk() throws Exception {
        Product deactivatedProduct = product.deactivate();
        when(productService.deactivateProduct(productId)).thenReturn(Optional.of(deactivatedProduct));

        mockMvc.perform(patch("/api/products/{id}/deactivate", productId))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.active").value(false));

        verify(productService).deactivateProduct(productId);
    }
}

// === ARCHIVO: Dockerfile ===
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN apk add --no-cache maven && mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

// === ARCHIVO: docker-compose.yml ===
version: '3.8'

services:
  backend:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/bddpragma
      - SPRING_DATASOURCE_USERNAME=postgres
      - SPRING_DATASOURCE_PASSWORD=postgres
      - SPRING_JPA_HIBERNATE_DDL_AUTO=validate
    depends_on:
      - postgres

  postgres:
    image: postgres:16-alpine
    ports:
      - "5432:5432"
    environment:
      - POSTGRES_DB=bddpragma
      - POSTGRES_USER=postgres
      - POSTGRES_PASSWORD=postgres
    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:
```
