package conjuntoDominanteMinimo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import grafo.Grafo;
import grafo.Vecindario;

public class SolverGoloso {
	private Grafo _grafo;
	private Sorter<Vecindario> _sorter;
	private List<Vecindario> _listaDeVecinos;
	private boolean[] _marcados;
	private Set<Integer> _conjuntoDominanteMinimo;
	private int posicion;

	public SolverGoloso(Grafo grafo, Sorter<Vecindario> sorter) {
		if (grafo == null) {
			throw new NullPointerException("El grafo pasado no puede ser null.");
		}

		if (sorter == null) {
			throw new NullPointerException("El comparador pasado no puede ser null.");
		}

		_grafo = grafo;
		_sorter = sorter;
	}

	public void resolver() {
		setVariables();
		ordenarListaDeVecinos();

		while (todosVisitados() != true) {
			agregarAlConjuntoDominante(posicion);
			marcar(vertice(posicion));
			marcar(susVecinos(posicion));
			posicion++;
		}
	}

	public Set<Integer> getConjuntoDominanteMinimo() {
		return _conjuntoDominanteMinimo;
	}

	private void agregarAlConjuntoDominante(int posicion) {
		_conjuntoDominanteMinimo.add(vertice(posicion));
	}

	private void setVariables() {
		_listaDeVecinos = new ArrayList<>(_grafo.getListaDeAdyacencia());
		_marcados = new boolean[_grafo.tamano()];
		_conjuntoDominanteMinimo = new HashSet<>();
		posicion = 0;
	}

	private HashSet<Integer> susVecinos(int posicion) {
		return _listaDeVecinos.get(posicion).getVecinos();
	}

	private void marcar(HashSet<Integer> vecinos) {
		for (Integer elem : vecinos) {
			_marcados[elem] = true;
		}
	}

	private void marcar(int vertice) {
		_marcados[vertice] = true;
	}

	private int vertice(int posicion) {
		return _listaDeVecinos.get(posicion).getVertice();
	}

	private boolean todosVisitados() {
		for (boolean elem : _marcados) {
			if (elem == false)
				return false;
		}
		return true;
	}

	private void ordenarListaDeVecinos() {
		_sorter.sort(_listaDeVecinos);
	}
}
