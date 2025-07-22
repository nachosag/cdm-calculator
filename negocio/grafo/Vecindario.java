package grafo;

import java.util.HashSet;

public class Vecindario {
	private int _vertice;
	private HashSet<Integer> _vecinos;

	public Vecindario(HashSet<Integer> vecinos, int indice) {
		_vecinos = vecinos;
		_vertice = indice;
	}

	public HashSet<Integer> getVecinos() {
		return _vecinos;
	}

	public int getVertice() {
		return _vertice;
	}

	public void agregarVertice(int vertice) {
		_vecinos.add(vertice);
	}

	public void eliminarVertice(int vertice) {
		_vecinos.remove(vertice);
	}

	public boolean existeVertice(int vertice) {
		return _vecinos.contains(vertice);
	}

	public int getCantidadDeVecinos() {
		return _vecinos.size();
	}

}
