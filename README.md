# CDM Calculator

Aplicación de escritorio en **Java + Swing** para modelar grafos no dirigidos y resolver el problema del **Conjunto Dominante Mínimo (CDM)** con distintos enfoques: **backtracking** y variantes **golosas** (ascendente, descendente y aleatoria).

## ¿Qué problema resuelve?

En teoría de grafos, un conjunto dominante es un subconjunto de vértices tal que cada vértice del grafo pertenece al conjunto o es adyacente a alguno de sus vértices. El objetivo del CDM es encontrar uno de tamaño mínimo.

Este proyecto permite:
- Dibujar/editar grafos visualmente.
- Guardar y cargar instancias desde JSON.
- Ejecutar diferentes estrategias para obtener un conjunto dominante.
- Visualizar resultados directamente en la interfaz.

## Funcionalidades principales

- **Editor de grafos en GUI (Swing)**
  - Agregar/eliminar vértices.
  - Crear/eliminar aristas desde el panel gráfico.
  - Visualizar lista de adyacencia en tiempo real.

- **Persistencia en JSON**
  - Guardado de grafos por nombre en `grafos.json`.
  - Carga de grafos previamente almacenados.

- **Resolución del CDM**
  - **Backtracking** (explora combinaciones y conserva la mejor).
  - **Goloso** con tres estrategias de ordenamiento:
    - Aleatorio.
    - Por grado ascendente.
    - Por grado descendente.

## Arquitectura del proyecto

El código está organizado en paquetes dentro de `negocio/`:

- `grafo/`
  - Modelo de grafo no dirigido mediante lista de adyacencia (`Grafo`, `Vecindario`).
- `conjuntoDominanteMinimo/`
  - Solvers del CDM (`SolverConBacktracking`, `SolverGoloso`) y estrategias `Sorter`.
- `interfaz/`
  - Interfaz Swing (`PantallaPrincipal`, `JPanelGrafo`, componentes gráficos de vértices/aristas).
- `datos/`
  - Lectura/escritura de JSON (`ArchivoJSON`) usando Gson.

## Requisitos

- **Java 8+** (recomendado 11 o superior).
- Dependencia incluida localmente:
  - `lib/gson-2.6.2.jar`
- (Opcional para tests) JUnit 4 en el classpath.

## Ejecución

### Opción 1: desde Eclipse (recomendada para este repo)

El repositorio incluye archivos de proyecto Eclipse (`.project` y `.classpath`).

1. Importar como proyecto Java existente.
2. Verificar que `lib/gson-2.6.2.jar` esté en el Build Path.
3. Ejecutar la clase principal:

```java
interfaz.PantallaPrincipal
```

### Opción 2: por línea de comandos

Compilar fuentes (sin tests):

```bash
mkdir -p out
javac -cp lib/gson-2.6.2.jar -d out $(find negocio -name "*.java" ! -name "*Test.java" ! -path "negocio/grafo/Auxiliar.java")
```

Ejecutar:

```bash
java -cp out:lib/gson-2.6.2.jar interfaz.PantallaPrincipal
```

> En Windows usa `;` en lugar de `:` en el classpath.

## Tests

El repositorio incluye tests unitarios para estructuras de grafo y solvers en:
- `negocio/grafo/*Test.java`
- `negocio/conjuntoDominanteMinimo/*Test.java`

Para ejecutarlos por CLI necesitarás agregar JUnit 4 al classpath (el JAR no está versionado en este repo).

> Nota: `negocio/grafo/Auxiliar.java` también usa utilidades de JUnit, por eso la compilación de la app sin tests lo excluye explícitamente.

## Vista previa

https://github.com/user-attachments/assets/3967a42e-e3f2-48c6-8aa4-fec356adc52b

## Estado del proyecto

Proyecto académico/experimental orientado a visualización y comparación de estrategias para CDM.
