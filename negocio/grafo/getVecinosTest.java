package grafo;

import org.junit.Test;

public class getVecinosTest {

	// Vecinos
	@Test(expected = IndexOutOfBoundsException.class)
	public void vecinosConVerticeNegativo() {
		Grafo g = new Grafo(3);
		g.getVecinos(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void vecinosConVerticeExcedido() {
		Grafo g = new Grafo(3);
		g.getVecinos(3);
	}

	@Test
	public void vecinosConVerticesDesconectados() {
		Grafo g = new Grafo(3);
		Auxiliar.verificarElementos(new int[] {}, g.getVecinos(0));
	}

	@Test
	public void vecinosConVerticesConectados() {
		Grafo g = Auxiliar.grafoNormal();
		Auxiliar.verificarElementos(new int[] { 2, 4 }, g.getVecinos(3));
	}

	@Test
	public void vecinosTest() {
		Grafo g = Auxiliar.grafoConVerticeAislado();
		Auxiliar.verificarElementos(new int[] { 0, 3, 4 }, g.getVecinos(2));
	}

	@Test
	public void vecinosVaciosTest() {
		Grafo g = Auxiliar.grafoConVerticeAislado();
		Auxiliar.verificarElementos(new int[] {}, g.getVecinos(1));
	}

	@Test
	public void unSoloVecinoTest() {
		Grafo g = Auxiliar.grafoConVerticeAislado();
		g.eliminarArista(0, 3);
		Auxiliar.verificarElementos(new int[] { 2 }, g.getVecinos(0));
	}

}
