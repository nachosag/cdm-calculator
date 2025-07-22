package conjuntoDominanteMinimo;

import org.junit.Test;

import grafo.Auxiliar;

public class SolverConBacktrackingTest {
	private SolverConBacktracking _solver;

	@Test(expected = NullPointerException.class)
	public void resolverConGrafoNull() {
		_solver = new SolverConBacktracking(null);
		_solver.resolver();
	}

	@Test
	public void resolverConGrafoSinAristas() {
		_solver = new SolverConBacktracking(Auxiliar.grafoSinAristas());
		_solver.resolver();
		Auxiliar.verificarTamanio(5, _solver.getConjuntoDominanteMinimo());
	}

	@Test
	public void resolverConGrafoNormal() {
		_solver = new SolverConBacktracking(Auxiliar.grafoNormal());
		_solver.resolver();
		Auxiliar.verificarTamanio(2, _solver.getConjuntoDominanteMinimo());
	}

	@Test
	public void resolverConGrafoCompleto() {
		_solver = new SolverConBacktracking(Auxiliar.grafoCompleto());
		_solver.resolver();
		Auxiliar.verificarTamanio(1, _solver.getConjuntoDominanteMinimo());
	}

	@Test
	public void resolverConGrafoDeVerticeAislado() {
		_solver = new SolverConBacktracking(Auxiliar.grafoConVerticeAislado());
		_solver.resolver();
		Auxiliar.verificarTamanio(2, _solver.getConjuntoDominanteMinimo());
	}

	@Test
	public void resolverConGrafoPalmera() {
		_solver = new SolverConBacktracking(Auxiliar.grafoPalmera());
		_solver.resolver();
		Auxiliar.verificarTamanio(3, _solver.getConjuntoDominanteMinimo());
	}

	@Test
	public void resolverConGrafoPartidoEnTres() {
		_solver = new SolverConBacktracking(Auxiliar.grafoPartidoEnTresPartes());
		_solver.resolver();
		Auxiliar.verificarTamanio(3, _solver.getConjuntoDominanteMinimo());
	}

	@Test
	public void resolverConGrafoMuyDenso() {
		_solver = new SolverConBacktracking(Auxiliar.grafoMuyDenso());
		_solver.resolver();
		Auxiliar.verificarTamanio(2, _solver.getConjuntoDominanteMinimo());
	}
}
