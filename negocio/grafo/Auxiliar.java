package grafo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Set;

public class Auxiliar {

	public static Grafo grafoSinAristas() {
		Grafo g = new Grafo(5);
		return g;
	}

	public static Grafo grafoNormal() {
		Grafo g = new Grafo(5);
		g.agregarArista(0, 1);
		g.agregarArista(3, 2);
		g.agregarArista(2, 1);
		g.agregarArista(4, 3);
		g.agregarArista(1, 4);

		return g;
	}

	public static Grafo grafoCompleto() {
		Grafo grafo = new Grafo(5);
		for (int i = 0; i < 5; i++) {
			for (int j = i + 1; j < 5; j++) {
				grafo.agregarArista(i, j);
			}
		}
		return grafo;
	}

	public static Grafo grafoConVerticeAislado() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(0, 2);
		grafo.agregarArista(0, 3);
		grafo.agregarArista(2, 3);
		grafo.agregarArista(2, 4);
		grafo.agregarArista(3, 4);
		return grafo;
	}

	public static Grafo grafoPalmera() {
		Grafo g = new Grafo(12);
		g.agregarArista(0, 1);
		g.agregarArista(0, 2);
		g.agregarArista(1, 2);

		g.agregarArista(0, 3);
		g.agregarArista(0, 4);
		g.agregarArista(0, 5);

		g.agregarArista(1, 6);
		g.agregarArista(1, 7);
		g.agregarArista(1, 8);

		g.agregarArista(2, 9);
		g.agregarArista(2, 10);
		g.agregarArista(2, 11);

		return g;
	}

	public static Grafo grafoPartidoEnTresPartes() {
		Grafo g = new Grafo(8);
		g.agregarArista(0, 7);

		g.agregarArista(1, 2);
		g.agregarArista(2, 6);

		g.agregarArista(3, 4);
		g.agregarArista(3, 5);
		return g;
	}

	public static Grafo grafoMuyDenso() {
		Grafo g = new Grafo(8);

		g.agregarArista(0, 2);
		g.agregarArista(0, 4);
		g.agregarArista(0, 6);
		g.agregarArista(0, 7);

		g.agregarArista(4, 3);
		g.agregarArista(4, 6);
		g.agregarArista(4, 5);

		g.agregarArista(6, 7);
		g.agregarArista(6, 2);

		g.agregarArista(2, 1);

		g.agregarArista(3, 7);
		g.agregarArista(3, 5);

		g.agregarArista(5, 1);
		return g;
	}

	public static void verificarTamanio(int tamanio, Set<Integer> obtenido) {
		assertEquals(tamanio, obtenido.size());
	}

	public static void verificarElementos(int[] esperado, Set<Integer> obtenido) {
		for (Integer elemento : esperado)
			assertTrue(obtenido.contains(elemento));
	}

	public static void verElementos(Set<Integer> obtenido) {
		StringBuilder sb = new StringBuilder();
		for (Integer elemento : obtenido) {
			sb.append(elemento + " - ");
		}
		sb.deleteCharAt(sb.length() - 2);
		System.out.println(sb.toString());
	}
}
