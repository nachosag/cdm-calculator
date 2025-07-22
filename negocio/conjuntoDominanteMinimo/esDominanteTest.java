package conjuntoDominanteMinimo;

import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.junit.Test;

import grafo.Auxiliar;
import grafo.Grafo;

public class esDominanteTest {
	private SolverConBacktracking _solver;
	private Set<Integer> _conjunto;

	@Test
	public void trianguloTest() {
		Grafo g = new Grafo(3);
		g.agregarArista(1, 0);
		g.agregarArista(1, 2);
		g.agregarArista(0, 2);
		_solver = new SolverConBacktracking(g);
		_conjunto = agregarElementos(new int[] { 2 });
		assertTrue(_solver.esDominante(_conjunto));
	}

	@Test
	public void unicaAristaTest() {
		Grafo g = new Grafo(2);
		g.agregarArista(1, 0);
		_solver = new SolverConBacktracking(g);
		_conjunto = agregarElementos(new int[] { 1 });
		assertTrue(_solver.esDominante(_conjunto));
	}

	@Test
	public void todosSonDominantesTest() {
		_solver = new SolverConBacktracking(Auxiliar.grafoCompleto());
		_conjunto = agregarElementos(new int[] { 0, 1, 2, 3, 4 });
		assertTrue(_solver.esDominante(_conjunto));
	}

	@Test
	public void conjuntoVacioTest() {
		_solver = new SolverConBacktracking(Auxiliar.grafoCompleto());
		_conjunto = agregarElementos(new int[] {});
		assertFalse(_solver.esDominante(_conjunto));
	}

	@Test
	public void cualquierVerticeTest() {
		Random random = new Random();
		_solver = new SolverConBacktracking(Auxiliar.grafoCompleto());
		_conjunto = agregarElementos(new int[] { random.nextInt(5) });
		assertTrue(_solver.esDominante(_conjunto));
	}

	@Test
	public void esDominantePeroNoElMenor() {
		_solver = new SolverConBacktracking(Auxiliar.grafoPalmera());
		_conjunto = agregarElementos(new int[] { 3, 4, 5, 6, 7, 8, 9, 10, 11 });
		assertTrue(_solver.esDominante(_conjunto));
	}

	@Test
	public void encuentraAlVerticeAislado() {
		_solver = new SolverConBacktracking(Auxiliar.grafoConVerticeAislado());
		_conjunto = agregarElementos(new int[] { 2, 1 });
		assertTrue(_solver.esDominante(_conjunto));
	}

	@Test
	public void encuentraAlMenorConjuntoDominante() {
		_solver = new SolverConBacktracking(Auxiliar.grafoPalmera());
		_conjunto = agregarElementos(new int[] { 0, 1, 2 });
		assertTrue(_solver.esDominante(_conjunto));
	}

	@Test
	public void encuentraLasTresComponentesConexas() {
		_solver = new SolverConBacktracking(Auxiliar.grafoPartidoEnTresPartes());
		_conjunto = agregarElementos(new int[] { 0, 2, 3 });
		assertTrue(_solver.esDominante(_conjunto));
	}

	@Test
	public void encuentraAlMenorConjuntoDominanteEnUnGrafoMuyDenso() {
		_solver = new SolverConBacktracking(Auxiliar.grafoMuyDenso());
		_conjunto = agregarElementos(new int[] { 0, 5 });
		assertTrue(_solver.esDominante(_conjunto));
	}

	private Set<Integer> agregarElementos(int[] conjunto) {
		Set<Integer> ret = new HashSet<Integer>();
		for (Integer elem : conjunto) {
			ret.add(elem);
		}
		return ret;
	}

}
