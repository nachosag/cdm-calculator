package grafo;

import static org.junit.Assert.*;
import org.junit.Test;

public class GrafoTest {

	// Constructor
	@Test(expected = IllegalArgumentException.class)
	public void constructorConCantidadNegativaTest() {
		@SuppressWarnings("unused")
		Grafo g = new Grafo(-1);
	}

	@Test // (expected = IllegalArgumentException.class)
	public void constructorConCeroVerticesTest() {
		@SuppressWarnings("unused")
		Grafo g = new Grafo(0);
	}

	@Test
	public void constructorConCantidadPositivaTest() {
		@SuppressWarnings("unused")
		Grafo g = new Grafo(1);
	}

	// Grado
	@Test(expected = IndexOutOfBoundsException.class)
	public void gradoDeVerticeNegativoTest() {
		Grafo grafo = Auxiliar.grafoConVerticeAislado();
		grafo.grado(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void gradoDeVerticeExcedidoTest() {
		Grafo grafo = Auxiliar.grafoConVerticeAislado();
		grafo.grado(5);
	}

	@Test
	public void gradoTest() {
		Grafo grafo = Auxiliar.grafoConVerticeAislado();
		assertEquals(2, grafo.grado(0));
		assertEquals(0, grafo.grado(1));
		assertEquals(3, grafo.grado(2));
	}
	
	@Test
	public void agregarUnicoVerticeTest() {
		Grafo grafo = new Grafo(0);
		grafo.agregarVertice();
		assertEquals(1, grafo.tamano());
	}
	
	@Test
	public void gradoPostAgregarVerticeTest() {
		Grafo grafo = new Grafo(3);
		grafo.agregarVertice();
		assertEquals(4, grafo.tamano());
	}
	
	@Test
	public void eliminarVerticeTest() {
		Grafo grafo = new Grafo(3);
		grafo.eliminarUltimoVertice();
		assertEquals(2, grafo.tamano());
	}
	
	@Test
	public void eliminarUnicoVerticeTest() {
		Grafo grafo = new Grafo(1);
		grafo.eliminarUltimoVertice();
		assertEquals(0, grafo.tamano());
	}
}
