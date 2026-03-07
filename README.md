# CDM Calculator

Desktop application for building undirected graphs and solving the **Minimum Dominating Set (MDS / CDM)** problem using both an exact approach (backtracking) and heuristic approaches (greedy orderings).

## Overview

This project combines an interactive Swing graph editor with algorithmic solvers for a classic NP-hard graph problem.

It was built to make two things observable in the same environment:

1. **Graph modeling workflows** (create vertices/edges, inspect adjacency, persist and reload scenarios).
2. **Algorithm behavior trade-offs** between optimal search and faster heuristics.

The tool is aimed at students and engineers who want to experiment with graph instances, compare solver outputs, and understand how implementation choices affect solution quality.

## Key Features

- Interactive graph editor:
  - add/remove vertices,
  - create edges by selecting two nodes,
  - drag vertices while keeping incident edges synchronized,
  - block duplicated edges in the visual layer.
- Real-time adjacency list visualization in the side panel.
- JSON persistence of multiple named graphs in a single file (`grafos.json`).
- Three greedy strategies selectable at runtime:
  - random ordering,
  - ascending degree,
  - descending degree.
- Exact solver with backtracking that evaluates subsets and keeps the best dominating set found.
- Visual highlighting of the resulting dominating set directly on the graph.

## Architecture

The codebase follows a modular package split with clear responsibilities:

- `negocio/grafo` (domain model)
  - `Grafo`: undirected graph implemented as adjacency lists (`List<Vecindario>`).
  - `Vecindario`: vertex + neighbor set abstraction (`HashSet<Integer>`).
  - responsibility: graph invariants, adjacency operations, validation.

- `negocio/conjuntoDominanteMinimo` (algorithm layer)
  - `SolverConBacktracking`: exhaustive search with pruning by current best set size.
  - `SolverGoloso`: generic greedy pipeline.
  - `Sorter` + concrete implementations: strategy pattern to define vertex ordering independently from solver logic.
  - responsibility: MDS solving strategies and extensibility point for new heuristics.

- `negocio/interfaz` (presentation + interaction)
  - `PantallaPrincipal`: main window, controls, algorithm triggers, persistence actions.
  - `JPanelGrafo`: drawing surface, mouse interaction, graph manipulation, result coloring.
  - `VerticeGrafico` / `AristaGrafica`: rendering primitives.
  - responsibility: user interaction and visualization.

- `negocio/datos` (persistence)
  - `ArchivoJSON`: JSON read/write via Gson for named graph storage.

### Interaction flow

1. UI events mutate `JPanelGrafo`.
2. `JPanelGrafo` updates `Grafo` (domain model) and redraws visual elements.
3. Solver execution receives current `Grafo` and returns a dominating set.
4. UI paints selected vertices in a different color.
5. Save/load operations serialize/deserialize graphs through `ArchivoJSON`.

## Tech Stack

- **Language:** Java
- **UI Framework:** Swing/AWT (desktop-native event-driven UI)
- **Serialization:** Gson (`lib/gson-2.6.2.jar`)
- **Testing:** JUnit 4 style tests (included in source tree)
- **Project tooling:** Eclipse project metadata (`.project`, `.classpath`) plus CLI-compatible Java sources

## Notable Implementation Details

- **Adjacency representation optimized for membership checks:**
  - Neighbor lists use `HashSet<Integer>`, so edge existence and insert/remove operations are constant-time on average.

- **Graph invariant enforcement in the model layer:**
  - `Grafo` validates negative indices, out-of-range vertices, and loops before mutating adjacency.
  - This keeps correctness rules centralized instead of duplicating checks in UI or solvers.

- **Backtracking with branch-and-bound style pruning:**
  - The solver explores inclusion/exclusion of each vertex.
  - Recursive branches are only expanded when the partial solution can still beat the current best.
  - This does not change worst-case complexity but reduces unnecessary exploration.

- **Strategy pattern for greedy experiments:**
  - `Sorter<Vecindario>` decouples ordering policy from greedy selection logic.
  - Enables swapping heuristics without changing solver internals.

- **Dominance verification design:**
  - `esDominante` builds a `marcados` set and removes dominated vertices (selected vertices + neighbors).
  - A set is dominating iff no vertex remains unmarked.

- **UI synchronization details:**
  - Dragging a node updates all incident edge coordinates.
  - Loading from JSON rebuilds both model and visual graph state.

## How to Run the Project

### Prerequisites

- Java 8+ (Java 11+ recommended)
- Gson jar included at `lib/gson-2.6.2.jar`

### Option 1: Eclipse

1. Import as an existing Java project.
2. Ensure `lib/gson-2.6.2.jar` is on the classpath.
3. Run:

```java
interfaz.PantallaPrincipal
```

### Option 2: Command line

Compile application classes (excluding tests):

```bash
mkdir -p out
javac -cp lib/gson-2.6.2.jar -d out $(find negocio -name "*.java" ! -name "*Test.java" ! -name "Auxiliar.java" ! -name "StressTest.java")
```

Run:

```bash
java -cp out:lib/gson-2.6.2.jar interfaz.PantallaPrincipal
```

On Windows, replace `:` with `;` in the classpath.

## Example Usage

1. Start the app.
2. Add several vertices.
3. Create edges by clicking two vertices.
4. Execute:
   - **Resolver con Backtracking** for an exact solution, or
   - any **Resolver con Goloso** variant for heuristic solutions.
5. Compare highlighted vertices (orange) and adjacency list output.
6. Save the graph with a custom name and reload it later.

## Project Structure

```text
.
├── negocio/
│   ├── conjuntoDominanteMinimo/
│   │   ├── SolverConBacktracking.java
│   │   ├── SolverGoloso.java
│   │   ├── Sorter.java
│   │   └── Sorter*.java
│   ├── datos/
│   │   └── ArchivoJSON.java
│   ├── grafo/
│   │   ├── Grafo.java
│   │   ├── Vecindario.java
│   │   └── *Test.java / Auxiliar.java
│   └── interfaz/
│       ├── PantallaPrincipal.java
│       ├── JPanelGrafo.java
│       ├── VerticeGrafico.java
│       └── AristaGrafica.java
├── images/
├── lib/
│   └── gson-2.6.2.jar
└── grafos.json
```

## Future Improvements

- Add reproducible benchmarking suite comparing greedy variants vs exact solver by graph family.
- Introduce immutable graph snapshots for safer persistence and solver isolation.
- Add import/export formats beyond JSON (e.g., edge list, GraphML).
- Improve greedy quality with tie-breakers and local search post-processing.
- Separate UI and application services further to ease headless testing.
- Add CI workflow to compile and execute unit tests automatically.

## Author

Developed as a graph algorithms and software design project focused on combining interactive visualization with algorithmic experimentation.

