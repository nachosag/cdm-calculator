package grafo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Grafo {
	private List<Vecindario> _listaAdyacencia;
	private int _tamano;

	// Constructor
	public Grafo(int cantidadDeVertices) {
		verificar(cantidadDeVertices);
		instanciar(cantidadDeVertices);
	}
	
	// Agregado de vertices
	public void agregarVertice() {
		_listaAdyacencia.add(new Vecindario(new HashSet<Integer>(), _tamano));
		_tamano++;
	}
	
	// Eliminacion de vertices
	public void eliminarUltimoVertice() {
		if (_listaAdyacencia.get(_tamano - 1).getCantidadDeVecinos() != 0) {
			_listaAdyacencia.get(_tamano - 1).eliminarVertice(_tamano);
		}
		_listaAdyacencia.remove(_tamano - 1);
		_tamano--;
	}

	// Agregado de aristas
	public void agregarArista(int origen, int destino) {
		verificarVertices(origen, destino);

		_listaAdyacencia.get(origen).agregarVertice(destino);
		_listaAdyacencia.get(destino).agregarVertice(origen);
	}

	// Eliminacion de aristas
	public void eliminarArista(int origen, int destino) {
		verificarVertices(origen, destino);
		_listaAdyacencia.get(origen).eliminarVertice(destino);
		_listaAdyacencia.get(destino).eliminarVertice(origen);
	}

	// Informa si existe la arista especificada
	public boolean existeArista(int origen, int destino) {
		return _listaAdyacencia.get(origen).existeVertice(destino);
	}

	// Cantidad de vertices
	public int tamano() {
		return _tamano;
	}

	// Cantidad de vecinos
	public int grado(int vertice) {
		verificarVertice(vertice);
		return _listaAdyacencia.get(vertice).getCantidadDeVecinos();
	}

	// Vecinos de un vertice
	public HashSet<Integer> getVecinos(int vertice) {
		verificarVertice(vertice);
		return _listaAdyacencia.get(vertice).getVecinos();
	}

	// Vertices ordenados por cantidad de vecinos
	public List<Vecindario> getListaDeAdyacencia() {
		List<Vecindario> ret = new ArrayList<>(_listaAdyacencia);
		return ret;
	}

	// Verificaciones
	private void verificarVertices(int origen, int destino) {
		verificarLoops(origen, destino);
		verificarVerticesNegativos(origen, destino);
		verificarVerticeExcedido(origen, destino);
	}

	private void verificarVerticeExcedido(int origen, int destino) {
		if (origen >= _tamano)
			throw new IndexOutOfBoundsException("El vértice " + origen + " está excedido.");
		if (destino >= _tamano)
			throw new IndexOutOfBoundsException("El vértice " + destino + " está excedido.");
	}

	private void verificarLoops(int origen, int destino) {
		if (origen == destino)
			throw new IllegalArgumentException("No se permiten loops.");
	}

	private void verificarVerticesNegativos(int origen, int destino) {
		if (origen < 0)
			throw new IndexOutOfBoundsException("No se permiten vértices negativos: " + origen);

		if (destino < 0)
			throw new IndexOutOfBoundsException("No se permiten vértices negativos: " + destino);
	}

	private void verificarVertice(int vertice) {
		if (vertice < 0)
			throw new IndexOutOfBoundsException("El vertice no puede ser negativo: " + vertice);

		if (vertice >= _tamano)
			throw new IndexOutOfBoundsException("Los vertices deben estar entre 0 y |V|-1: " + vertice);
	}

	private void instanciar(int cantidadDeVertices) {
		_listaAdyacencia = new ArrayList<>(cantidadDeVertices);
		_tamano = cantidadDeVertices;

		for (int i = 0; i < cantidadDeVertices; i++) {
			_listaAdyacencia.add(new Vecindario(new HashSet<Integer>(), i));
		}
	}

	private void verificar(int cantidadDeVertices) {
		if (cantidadDeVertices < 0) {
			throw new IllegalArgumentException("La cantidad de vertices no puede ser negativa: " + cantidadDeVertices);
		}
	}
}
