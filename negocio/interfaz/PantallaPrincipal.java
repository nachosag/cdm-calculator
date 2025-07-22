package interfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import datos.ArchivoJSON;
import grafo.Vecindario;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.Scrollbar;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;

import conjuntoDominanteMinimo.Sorter;
import conjuntoDominanteMinimo.SorterAleatorio;
import conjuntoDominanteMinimo.SorterAscendente;
import conjuntoDominanteMinimo.SorterDescendente;
import java.awt.SystemColor;

public class PantallaPrincipal implements ListenerGrafo {

	private JFrame framePrincipal;
	private JPanel contentPane;
	private JPanel panelIzquierdo;
	private JLabel labelListaVecinos;
	private Scrollbar scrollListaVecinos;
	private List listaEnPantallaVecinos;
	public JPanelGrafo panelDerecho;
	public int widthPanelDerecho, heightPanelDerecho;
	private JButton btnAgregarVertice;
	private JButton btnEliminarVertice;
	private JButton btnGuardarGrafo;
	private JButton btnResolverConBacktracking;
	private JButton btnResolverConGoloso;
	private JButton btnResolverConGoloso_1;
	private JButton btnResolverConGoloso_2;
	private JButton btnCargarGrafo;
	private Sorter<Vecindario> sorterAscendente = new SorterAscendente();
	private Sorter<Vecindario> sorterDescendente = new SorterDescendente();
	private Sorter<Vecindario> sorterAleatorio = new SorterAleatorio();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal window = new PantallaPrincipal();
					window.framePrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	public PantallaPrincipal() {
		initialize();
	}

	private void initialize() {
		inicializarFramePrincipal();
		inicializarContentPane();
		inicializarPanelIzquierdo();
		inicializarPanelDerecho();
	}

	private void inicializarFramePrincipal() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Image icono = Toolkit.getDefaultToolkit().getImage("images\\grafoIcon.png");
		framePrincipal = new JFrame();
		framePrincipal.setSize(screenSize.width + 15, screenSize.height - 30);
		framePrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
		framePrincipal.setLocationRelativeTo(null);
		framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePrincipal.setIconImage(icono);
	}

	private void inicializarContentPane() {
		contentPane = new JPanel();
		contentPane.setSize(framePrincipal.getSize());
		contentPane.setLayout(null);
		framePrincipal.setContentPane(contentPane);
	}

	private void inicializarPanelDerecho() {
		widthPanelDerecho = 1039;
		heightPanelDerecho = 699;

		Image imagenFondo = null;
		try {
			imagenFondo = ImageIO.read(new File("images\\fondo.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		panelDerecho = new JPanelGrafo(imagenFondo, widthPanelDerecho, heightPanelDerecho);
		panelDerecho.addListenerGrafo(this);
		panelDerecho.setBounds(331, 0, widthPanelDerecho, heightPanelDerecho);
		panelDerecho.setLayout(null);
		contentPane.add(panelDerecho);
	}

	private void inicializarPanelIzquierdo() {
		panelIzquierdo = new JPanel();
		panelIzquierdo.setBackground(new Color(0, 0, 51));
		panelIzquierdo.setBounds(0, 0, 332, 699);
		contentPane.add(panelIzquierdo);
		panelIzquierdo.setLayout(null);

		labelListaVecinos = new JLabel("Lista de Vecinos:");
		labelListaVecinos.setForeground(SystemColor.inactiveCaptionBorder);
		labelListaVecinos.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 22));
		labelListaVecinos.setBounds(10, 11, 210, 27);
		panelIzquierdo.add(labelListaVecinos);

		listaEnPantallaVecinos = new List();
		listaEnPantallaVecinos.setBackground(new Color(0, 51, 102));
		listaEnPantallaVecinos.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		listaEnPantallaVecinos.setBounds(10, 44, 312, 337);
		panelIzquierdo.add(listaEnPantallaVecinos);

		scrollListaVecinos = new Scrollbar();
		scrollListaVecinos.setBounds(305, 44, 17, 337);
		panelIzquierdo.add(scrollListaVecinos);
		inicializarBotonera();
	}

	private void actualizarListaEnPantallaVecinos() {
		listaEnPantallaVecinos.removeAll();
		listaEnPantallaVecinos.setForeground(Color.WHITE);
		for (Vecindario vecindario : panelDerecho.getGrafoActualizado().getListaDeAdyacencia()) {
			listaEnPantallaVecinos.add(vecindario.getVertice() + " = " + vecindario.getVecinos().toString());
		}
	}

	private void inicializarBotonera() {
		btnAgregarVertice = new JButton("Agregar Vertice");
		btnAgregarVertice.setBounds(10, 704 - 312, 312, 23);
		panelIzquierdo.add(btnAgregarVertice);
		btnAgregarVertice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelDerecho.agregarNodo();
				actualizarListaEnPantallaVecinos();
			}
		});

		btnEliminarVertice = new JButton("Eliminar Vertice");
		btnEliminarVertice.setBackground(Color.white);
		btnEliminarVertice.setBounds(10, 738 - 312, 312, 23);
		panelIzquierdo.add(btnEliminarVertice);
		btnEliminarVertice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelDerecho.eliminarNodo();
				actualizarListaEnPantallaVecinos();
			}
		});

		btnGuardarGrafo = new JButton("Guardar Grafo");
		btnGuardarGrafo.setBackground(Color.white);
		btnGuardarGrafo.setBounds(10, 806 - 312, 312, 23);
		panelIzquierdo.add(btnGuardarGrafo);
		btnGuardarGrafo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreGrafo = JOptionPane.showInputDialog(null, "Cómo quieres nombrar el grafo?", null,
						JOptionPane.PLAIN_MESSAGE);
				if (nombreGrafo != null) {
					ArchivoJSON.generarJSON(nombreGrafo, panelDerecho.getGrafoActualizado());
				}
			}
		});

		btnCargarGrafo = new JButton("Cargar Grafo");
		btnCargarGrafo.setBackground(Color.white);
		btnCargarGrafo.setBounds(10, 772 - 312, 312, 23);
		panelIzquierdo.add(btnCargarGrafo);
		btnCargarGrafo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] opciones = ArchivoJSON.obtenerArrayStringNombresGrafos();
				String nombreGrafo = (String) JOptionPane.showInputDialog(null, "Selecciona una opción:", null,
						JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
				if (nombreGrafo != null) {
					reiniciarPanelDerecho();
					panelDerecho.setGrafoJSON(ArchivoJSON.obtenerGrafo(nombreGrafo));
					panelDerecho.graficarGrafoJSON();
					panelDerecho.repaint();
					actualizarListaEnPantallaVecinos();
				}

			}
		});

		btnResolverConBacktracking = new JButton("Resolver con Backtracking");
		btnResolverConBacktracking.setBackground(Color.white);
		btnResolverConBacktracking.setBounds(10, 840 - 312, 312, 23);
		panelIzquierdo.add(btnResolverConBacktracking);
		btnResolverConBacktracking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelDerecho.resolverConBacktracking();
			}
		});

		btnResolverConGoloso = new JButton("Resolver con Goloso Aleatorio");
		btnResolverConGoloso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelDerecho.resolverConAlgoritmo(sorterAleatorio);
			}
		});
		btnResolverConGoloso.setBackground(Color.white);
		btnResolverConGoloso.setBounds(10, 874 - 312, 312, 23);
		panelIzquierdo.add(btnResolverConGoloso);

		btnResolverConGoloso_1 = new JButton("Resolver con Goloso Descendente");
		btnResolverConGoloso_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelDerecho.resolverConAlgoritmo(sorterDescendente);
			}
		});
		btnResolverConGoloso_1.setBackground(Color.white);
		btnResolverConGoloso_1.setBounds(10, 908 - 312, 312, 23);
		panelIzquierdo.add(btnResolverConGoloso_1);

		btnResolverConGoloso_2 = new JButton("Resolver con Goloso Ascendente");
		btnResolverConGoloso_2.setBackground(Color.white);
		btnResolverConGoloso_2.setBounds(10, 942 - 312, 312, 23);
		panelIzquierdo.add(btnResolverConGoloso_2);
		btnResolverConGoloso_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelDerecho.resolverConAlgoritmo(sorterAscendente);
			}
		});

		JButton btnReiniciarAlgoritmo = new JButton("Reiniciar algoritmo");
		btnReiniciarAlgoritmo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelDerecho.reiniciarAlgoritmo();
			}
		});
		btnReiniciarAlgoritmo.setBounds(10, 977 - 312, 312, 23);
		panelIzquierdo.add(btnReiniciarAlgoritmo);
		btnCargarGrafo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}

	public void reiniciarPanelDerecho() {
		framePrincipal.remove(panelDerecho);
		framePrincipal.revalidate();
		inicializarPanelDerecho();
		framePrincipal.repaint();
	}

	@Override
	public void aristaAgregada() {
		actualizarListaEnPantallaVecinos();
	}
}
