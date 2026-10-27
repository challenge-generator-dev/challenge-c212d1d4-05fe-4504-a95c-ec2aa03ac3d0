# Implementación de un Servicio Backend con Framework

Como desarrollador backend avanzado, debes implementar un servicio que utilice un framework en Java para manejar solicitudes, controlar variables de entorno, generar logs y conectarse a una base de datos usando un ORM. El servicio debe ser escalable, mantener la consistencia de datos y estar preparado para manejar picos de carga.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Experiencia en Frameworks |
| **Nivel** | advanced-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 15 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Configuración del Entorno

**Objetivo:** Configurar el entorno de desarrollo y el framework seleccionado para el proyecto.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Establecer el entorno de desarrollo con las dependencias necesarias.
- Configurar el framework para manejar solicitudes y variables de entorno.

**Entregable:** Entorno de desarrollo configurado con el framework y variables de entorno establecidas.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la estructura de proyecto típica para el framework seleccionado.
- Investiga las mejores prácticas para manejar variables de entorno en aplicaciones backend.

</details>

### Fase 2: Implementación del Servicio

**Objetivo:** Implementar el servicio backend que maneje solicitudes y genere logs.

**Tiempo estimado:** 5 horas

**Instrucciones:**

- Crear endpoints para manejar diferentes tipos de solicitudes.
- Implementar la lógica para generar logs de rastros.

**Entregable:** Servicio backend con endpoints funcionales y generación de logs.

<details>
<summary>Pistas de conocimiento</summary>

- Revisa la documentación del framework para entender cómo crear y manejar endpoints.
- Explora las opciones disponibles para la generación de logs y elige la que mejor se adapte a tus necesidades.

</details>

### Fase 3: Conexión con Base de Datos

**Objetivo:** Conectar el servicio a una base de datos utilizando un ORM.

**Tiempo estimado:** 5 horas

**Instrucciones:**

- Configurar la conexión a la base de datos.
- Implementar la lógica para realizar operaciones CRUD utilizando el ORM.

**Entregable:** Servicio backend conectado a una base de datos con operaciones CRUD funcionales.

<details>
<summary>Pistas de conocimiento</summary>

- Investiga las mejores prácticas para conectar tu aplicación a una base de datos utilizando un ORM.
- Prueba diferentes configuraciones para asegurarte de que la conexión sea estable y eficiente.

</details>

### Fase 4: Escalabilidad y Manejo de Picos de Carga

**Objetivo:** Asegurar que el servicio sea escalable y pueda manejar picos de carga.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Implementar medidas para mejorar la escalabilidad del servicio.
- Simular picos de carga y ajustar el servicio para manejarlos de manera eficiente.

**Entregable:** Servicio backend escalable y preparado para manejar picos de carga.

<details>
<summary>Pistas de conocimiento</summary>

- Investiga técnicas para mejorar la escalabilidad de aplicaciones backend.
- Utiliza herramientas para simular carga y medir el rendimiento del servicio.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es un framework y por qué es importante en el desarrollo de aplicaciones backend?
- **paraQueSirve**: ¿Para qué sirve un ORM y cómo ayuda en la conexión con bases de datos?
- **comoSeUsa**: ¿Cómo se usa un framework para manejar solicitudes y variables de entorno?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar un servicio backend con un framework y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica la elección de un framework y un ORM para un proyecto backend?

## Criterios de Evaluacion

- Configuración correcta del entorno de desarrollo y del framework.
- Implementación funcional de endpoints y generación de logs.
- Conexión exitosa a la base de datos y operaciones CRUD funcionales.
- Servicio escalable y preparado para manejar picos de carga.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
