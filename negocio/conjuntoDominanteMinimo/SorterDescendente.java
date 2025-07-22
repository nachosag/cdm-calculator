package conjuntoDominanteMinimo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import grafo.Vecindario;

public class SorterDescendente implements Sorter<Vecindario> {

	@Override
	public void sort(List<Vecindario> _listaDeVecinos) {
		Collections.sort(_listaDeVecinos, new Comparator<Vecindario>() {

			@Override
			public int compare(Vecindario vecindario1, Vecindario vecindario2) {
				return vecindario2.getCantidadDeVecinos() - vecindario1.getCantidadDeVecinos();
			}
		});
	}
}