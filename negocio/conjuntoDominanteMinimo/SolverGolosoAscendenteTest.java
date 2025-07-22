package conjuntoDominanteMinimo;

import org.junit.Test;

import grafo.Auxiliar;
import grafo.Grafo;
import grafo.Vecindario;

public class SolverGolosoAscendenteTest {
	private Sorter<Vecindario> _sorter = new SorterAscendente();
	private SolverGoloso _solver;
	private Grafo _grafo;

	@Test
	public void resolverConGrafoNormal() {
		_grafo = Auxiliar.grafoNormal();
		_solver = new SolverGoloso(_grafo, _sorter);
		_solver.resolver();
		Auxiliar.verificarTamanio(3, _solver.getConjuntoDominanteMinimo());
	}

	@Test
	public void resolverConGrafoDeVerticeAislado() {
		_grafo = Auxiliar.grafoConVerticeAislado();
		_solver = new SolverGoloso(_grafo, _sorter);
		_solver.resolver();
		Auxiliar.verificarTamanio(3, _solver.getConjuntoDominanteMinimo());
	}

	@Test
	public void resolverConGrafoCompleto() {
		_grafo = Auxiliar.grafoCompleto();
		_solver = new SolverGoloso(_grafo, _sorter);
		_solver.resolver();
		Auxiliar.verificarTamanio(1, _solver.getConjuntoDominanteMinimo());
	}

	@Test
	public void resolverConGrafoDeConjuntoDominanteMinimoDeTamanioTres() {
		_grafo = Auxiliar.grafoPalmera();
		_solver = new SolverGoloso(_grafo, _sorter);
		_solver.resolver();
		Auxiliar.verificarTamanio(9, _solver.getConjuntoDominanteMinimo());
	}

	@Test
	public void resolverConGrafoSinAristas() {
		_grafo = new Grafo(5);
		_solver = new SolverGoloso(_grafo, _sorter);
		_solver.resolver();
		Auxiliar.verificarTamanio(5, _solver.getConjuntoDominanteMinimo());
	}

	@Test
	public void resolverConGrafoDeTresComponentesConexas() {
		_grafo = Auxiliar.grafoPartidoEnTresPartes();
		_solver = new SolverGoloso(_grafo, _sorter);
		_solver.resolver();
		Auxiliar.verificarTamanio(5, _solver.getConjuntoDominanteMinimo());
	}

	@Test
	public void resolverConGrafoConMuchaDensidad() {
		_grafo = Auxiliar.grafoMuyDenso();
		_solver = new SolverGoloso(_grafo, _sorter);
		_solver.resolver();
		Auxiliar.verificarTamanio(3, _solver.getConjuntoDominanteMinimo());
	}

}
