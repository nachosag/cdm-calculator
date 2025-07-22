package grafo;

import static org.junit.Assert.*;
import org.junit.Test;

public class AristasTest {

	// Agregar arista
	@Test(expected = IndexOutOfBoundsException.class)
	public void agregarAristaConVerticeNegativo() {
		Grafo g = new Grafo(2);
		g.agregarArista(-1, 0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void agregarAristaConVerticeNegativoOpuesto() {
		Grafo g = new Grafo(2);
		g.agregarArista(0, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarAristaConVerticesIdenticos() {
		Grafo g = new Grafo(2);
		g.agregarArista(0, 0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void agregarAristaConVerticeExcedido() {
		Grafo g = new Grafo(2);
		g.agregarArista(0, 2);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void agregarAristaConVerticeExcedidoOpuesta() {
		Grafo g = new Grafo(2);
		g.agregarArista(2, 0);
	}

	@Test
	public void agregarAristaCorrectamente() {
		Grafo g = new Grafo(2);
		g.agregarArista(1, 0);
		assertTrue(g.existeArista(1, 0));
	}

	// Eliminar arista
	@Test(expected = IndexOutOfBoundsException.class)
	public void eliminarAristaConVerticeNegativo() {
		Grafo g = new Grafo(3);
		g.eliminarArista(-1, 0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void eliminarAristaConVerticeNegativoOpuesto() {
		Grafo g = new Grafo(3);
		g.eliminarArista(0, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void eliminarAristaConVerticeIdenticos() {
		Grafo g = new Grafo(3);
		g.eliminarArista(1, 1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void eliminarAristaConVerticeExcedido() {
		Grafo g = new Grafo(3);
		g.eliminarArista(0, 3);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void eliminarAristaConVerticeExcedidoOpuesto() {
		Grafo g = new Grafo(3);
		g.eliminarArista(3, 0);
	}

	@Test
	public void eliminarAristaCorrectamente() {
		Grafo g = new Grafo(3);
		g.agregarArista(1, 2);
		g.eliminarArista(1, 2);
		assertFalse(g.existeArista(1, 2));
	}

	@Test
	public void eliminarAristaCorrectamenteConOrdenOpuesto() {
		Grafo g = new Grafo(3);
		g.agregarArista(1, 2);
		g.eliminarArista(2, 1);
		assertFalse(g.existeArista(1, 2));
	}

	// Existe arista
	@Test
	public void existeAristaNegativaTest() {
		Grafo g = new Grafo(2);
		assertFalse(g.existeArista(0, -1));
	}

	@Test
	public void existeLoopTest() {
		Grafo g = new Grafo(2);
		assertFalse(g.existeArista(0, 0));
	}

	@Test
	public void aristaEspejoExistenteTest() {
		Grafo g = new Grafo(2);
		g.agregarArista(1, 0);
		assertTrue(g.existeArista(0, 1));
	}

	@Test
	public void existeAristaConVerticeExcedidoTest() {
		Grafo g = new Grafo(2);
		assertFalse(g.existeArista(0, 2));
	}

}
