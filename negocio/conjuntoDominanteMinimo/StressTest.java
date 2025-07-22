package conjuntoDominanteMinimo;

import java.util.Random;

import grafo.Grafo;

public class StressTest {

	public static void main(String[] args) {
		for (int n = 2; n < 500; n++) {
			long inicio = System.currentTimeMillis();

			SolverConBacktracking solver = new SolverConBacktracking(aleatorio(n));
			solver.resolver();

			long fin = System.currentTimeMillis();

			double tiempo = (fin - inicio) / 1000.0;

			System.out.println(n + ": " + tiempo + " seg.");
		}
	}

	private static Grafo aleatorio(int n) {
		Grafo g = new Grafo(n);
		Random random = new Random(0);
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (random.nextDouble() < 0.3) {
					g.agregarArista(i, j);
				}
			}
		}
		return g;
	}
}
