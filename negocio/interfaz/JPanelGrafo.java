package interfaz;

import javax.swing.JPanel;

import conjuntoDominanteMinimo.SolverConBacktracking;
import conjuntoDominanteMinimo.SolverGoloso;
import conjuntoDominanteMinimo.Sorter;
import grafo.Grafo;
import grafo.Vecindario;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@SuppressWarnings("serial")
public class JPanelGrafo extends JPanel implements MouseListener, MouseMotionListener {
	private Image imagenDeFondo;
	private Grafo grafo;

	private SolverGoloso solver;
	private SolverConBacktracking solverBacktracking;
	private Set<Integer> conjuntoDominante = new HashSet<>();

	private ListenerGrafo listener;

	ArrayList<VerticeGrafico> nodos;
	ArrayList<AristaGrafica> aristas;

	Point p1, p2;
	Integer indiceP1, indiceP2;

	VerticeGrafico nodoMover;
	int nodoIndice;
	int inicioArrastreX;
	int inicioArrastreY;
	boolean arrastrando = false;

	int widthPanelDerecho;
	int heightPanelDerecho;

	Random aleatorio = new Random();

	public JPanelGrafo(Image imagenDeFondo, int widthPanelDerecho, int heightPanelDerecho) {
		this.grafo = new Grafo(0);
		this.imagenDeFondo = imagenDeFondo;
		nodos = new ArrayList<VerticeGrafico>();
		aristas = new ArrayList<AristaGrafica>();
		this.widthPanelDerecho = widthPanelDerecho;
		this.heightPanelDerecho = heightPanelDerecho;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	@Override
	protected void paintComponent(Graphics fondo) {
		super.paintComponent(fondo);

		if (imagenDeFondo != null) {
			fondo.drawImage(imagenDeFondo, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		for (AristaGrafica arista : aristas) {
			arista.pintar(g);
		}
		for (VerticeGrafico nodo : nodos) {
			nodo.pintar(g);
		}
	}

	public void enlazar(int mouseX, int mouseY) {
		int contNodo = 0;
		for (VerticeGrafico nodo : nodos) {
			if (new Rectangle(nodo.getX() - VerticeGrafico.diametro / 2, nodo.getY() - VerticeGrafico.diametro / 2,
					VerticeGrafico.diametro, VerticeGrafico.diametro).contains(mouseX, mouseY)) {
				if (p1 == null) {
					p1 = new Point(nodo.getX(), nodo.getY());
					indiceP1 = contNodo;
					break;
				}
				if (indiceP1 == contNodo) {
					p1 = null;
					indiceP1 = null;
					p2 = null;
					indiceP2 = null;
					break;
				} 
				else {
					if(!aristaYaGraficada(indiceP1, contNodo)) {
						p2 = new Point(nodo.getX(), nodo.getY());
						indiceP2 = contNodo;
						this.aristas.add(new AristaGrafica(p1.x, p1.y, p2.x, p2.y, indiceP1, indiceP2));
						agregarArista(indiceP1, indiceP2);
					}
					p1 = null;
					indiceP1 = null;
					p2 = null;
					indiceP2 = null;
					break;
				}
			}
			contNodo++;
		}
	}

	private boolean aristaYaGraficada(int indiceP1, int indiceP2) {
		for (AristaGrafica aristaGrafica : aristas) {
			if (aristaGrafica.getIndiceN1() == indiceP1 && aristaGrafica.getIndiceN2() == indiceP2 || 
					aristaGrafica.getIndiceN1() == indiceP2 && aristaGrafica.getIndiceN2() == indiceP1) {
				return true;
			}
		}
		return false;
	}

	public void agregarNodo() {
		grafo.agregarVertice();
		int x = aleatorio.nextInt(widthPanelDerecho - 60);
		int y = aleatorio.nextInt(heightPanelDerecho - 60);
		this.nodos.add(new VerticeGrafico(x + 30, y + 30, grafo.tamano() - 1));
		repaint();
	}

	private void agregarArista(int n1, int n2) {
		grafo.agregarArista(n1, n2);
		listener.aristaAgregada();
	}

	public void eliminarNodo() {
		int indiceUltimoNodo = nodos.size() - 1;
		if (nodos.size() != 0) {
			grafo.eliminarUltimoVertice();
			eliminarAristasRelacionadas(indiceUltimoNodo); // Elimina las aristas relacionadas al nodo que se grafican
			nodos.remove(indiceUltimoNodo);
		}
		for (Vecindario vecindario : grafo.getListaDeAdyacencia()) { // aristas del nodo
			if (vecindario.getVecinos().contains(indiceUltimoNodo)) {
				vecindario.getVecinos().remove(indiceUltimoNodo);
			}
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			enlazar(e.getX(), e.getY());
		}
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		nodoMover = encontrarVertice(e.getX(), e.getY());
		if (nodoMover != null) {
			inicioArrastreX = e.getX();
			inicioArrastreY = e.getY();
			arrastrando = true;
		}
	}

	private VerticeGrafico encontrarVertice(int x, int y) {
		for (VerticeGrafico nodo : nodos) {
			if (new Rectangle(nodo.getX() - VerticeGrafico.diametro / 2, nodo.getY() - VerticeGrafico.diametro / 2,
					VerticeGrafico.diametro, VerticeGrafico.diametro).contains(x, y)) {
				return nodo;
			}
		}
		return null;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		arrastrando = false;
		nodoMover = null;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (arrastrando && nodoMover != null) {
			int dx = e.getX() - inicioArrastreX;
			int dy = e.getY() - inicioArrastreY;
			nodoMover.setX(nodoMover.getX() + dx);
			nodoMover.setY(nodoMover.getY() + dy);
			inicioArrastreX = e.getX();
			inicioArrastreY = e.getY();
			actualizarPosicionesAristas(nodoMover);
			repaint();
		}
		repaint();
	}

	private void actualizarPosicionesAristas(VerticeGrafico nodoMovido) {
		for (AristaGrafica arista : aristas) {
			if (arista.getIndiceN1() == nodoMovido.getIndice()) {
				arista.setX1(nodoMovido.getX());
				arista.setY1(nodoMovido.getY());
			} else if (arista.getIndiceN2() == nodoMovido.getIndice()) {
				arista.setX2(nodoMovido.getX());
				arista.setY2(nodoMovido.getY());
			}
		}
	}

	private void eliminarAristasRelacionadas(int indiceNodo) {
		ArrayList<AristaGrafica> aristasAEliminar = new ArrayList<>();

		for (AristaGrafica arista : aristas) {
			if (arista.getIndiceN1() == indiceNodo || arista.getIndiceN2() == indiceNodo) {
				aristasAEliminar.add(arista);
			}
		}
		aristas.removeAll(aristasAEliminar);
	}

	public Grafo getGrafoActualizado() {
		return grafo;
	}

	public void setGrafoJSON(Grafo grafo) {
		this.grafo = grafo;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	public void addListenerGrafo(ListenerGrafo listener) {
		this.listener = listener;
	}

	public void resolverConAlgoritmo(Sorter<Vecindario> sorter) {
		if (nodos.size() > 0) {
			for (VerticeGrafico nodo : nodos) { // PINTO EN SU COLOR ORIGINAL TODOS LOS NODOS SI ES QUE HICE UN
												// ALGORITMO ANTERIORMENTE
				nodo.setColor(Color.WHITE);
			}

			solver = new SolverGoloso(grafo, sorter); // RESUELVO ALGORITMO
			solver.resolver();
			conjuntoDominante = solver.getConjuntoDominanteMinimo();

			for (int indiceVertice : conjuntoDominante) { // PINTO EN SU COLOR ORIGINAL TODOS LOS NODOS SI ES QUE HICE
															// UN ALGORITMO ANTERIORMENTE
				nodos.get(indiceVertice).setColor(Color.ORANGE);
			}

			repaint();
		}
	}

	public void resolverConBacktracking() {
		if (nodos.size() > 0) { // PINTO EN SU COLOR ORIGINAL TODOS LOS NODOS SI ES QUE HICE UN ALGORITMO
								// ANTERIORMENTE
			for (VerticeGrafico nodo : nodos) {
				nodo.setColor(Color.WHITE);
			}

			solverBacktracking = new SolverConBacktracking(grafo); // RESUELVO ALGORITMO
			solverBacktracking.resolver();
			conjuntoDominante = solverBacktracking.getConjuntoDominanteMinimo();

			for (int indiceVertice : conjuntoDominante) { // PINTO EN NARANJA SOLO LOS NODOS DEL CONJUNTO DOMINANTE
				nodos.get(indiceVertice).setColor(Color.ORANGE);
			}

			repaint();
		}
	}

	public void reiniciarAlgoritmo() {
		if (nodos.size() > 0) {
			for (VerticeGrafico nodo : nodos) {
				nodo.setColor(Color.WHITE);
			}
		}
		repaint();
	}

	public void graficarGrafoJSON() {
		graficarVerticeJSON();
		graficarAristasJSON();
	}

	private void graficarVerticeJSON() {
		for (int i = 0; i < grafo.tamano(); i++) {
			int x = aleatorio.nextInt(widthPanelDerecho - 60);
			int y = aleatorio.nextInt(heightPanelDerecho - 60);
			nodos.add(new VerticeGrafico(x + 30, y + 30, i));
		}
		repaint();
	}

	private void graficarAristasJSON() {
		for (int i = 0; i < grafo.tamano(); i++) {
			for (Integer vecino : grafo.getVecinos(i)) {
				if (vecino > i) {
					VerticeGrafico nodo1 = nodos.get(i);
					VerticeGrafico nodo2 = nodos.get(vecino);
					aristas.add(new AristaGrafica(nodo1.getX(), nodo1.getY(), nodo2.getX(), nodo2.getY(), i, vecino));
				}
			}
		}
		repaint();
	}
}
