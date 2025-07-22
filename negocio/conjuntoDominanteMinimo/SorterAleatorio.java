package conjuntoDominanteMinimo;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import grafo.Vecindario;

public class SorterAleatorio implements Sorter<Vecindario> {

	@Override
	public void sort(List<Vecindario> _listaDeVecinos) {
		Collections.shuffle(_listaDeVecinos, new Random());
	}
}
