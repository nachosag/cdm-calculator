package conjuntoDominanteMinimo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import grafo.Grafo;
import grafo.Vecindario;

public class SolverConBacktracking {

	private Grafo _grafo;
	private Set<Integer> _menor;
	private Set<Integer> _actual;
	private List<Vecindario> _listaDeVecinos;
	private int _tamano;

	public SolverConBacktracking(Grafo grafo) {
		if (grafo == null)
			throw new NullPointerException("El grafo pasado por parámetro no debe ser null.");
		_grafo = grafo;
		_tamano = grafo.tamano();
		_listaDeVecinos = _grafo.getListaDeAdyacencia();
	}

	public void resolver() {
		iniciarlizarVariables();
		generarDesde(0);
	}

	boolean esDominante(Set<Integer> conjunto) {
		Set<Integer> marcados = new HashSet<>();
		for (int i = 0; i < _tamano; i++) {
			marcados.add(i);
		}

		for (Integer vertice : conjunto) {
			marcados.remove(vertice);
			for (Integer vecino : susVecinos(vertice)) {
				marcados.remove(vecino);
			}
		}
		return marcados.isEmpty();
	}

	public Set<Integer> getConjuntoDominanteMinimo() {
		return _menor;
	}

	private void iniciarlizarVariables() {
		_actual = new HashSet<>();
		_menor = new HashSet<>();

		for (int i = 0; i < _grafo.tamano(); i++) {
			_menor.add(i);
		}
	}

//	CON BACKTRACKING
	private void generarDesde(int vertice) {
		if (vertice == _tamano) {
			if (esDominante(_actual) && encontreUnConjuntoMenor())
				reemplazo();
			return;
		}

		generarDesde(vertice + 1);
		_actual.add(vertice);

		if (encontreUnConjuntoMenor()) {
			generarDesde(vertice + 1);
		}

		_actual.remove(vertice);
	}

	private void reemplazo() {
		_menor.clear();
		_menor.addAll(_actual);
	}

	private boolean encontreUnConjuntoMenor() {
		return _actual.size() < _menor.size();
	}

	private HashSet<Integer> susVecinos(Integer vertice) {
		return _listaDeVecinos.get(vertice).getVecinos();
	}
}
