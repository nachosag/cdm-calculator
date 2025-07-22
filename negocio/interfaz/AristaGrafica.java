package interfaz;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class AristaGrafica {
	private int x1, y1, x2, y2;
	private int grosor;
	private int indiceN1, indiceN2;

	public AristaGrafica(int x1, int y1, int x2, int y2, int indice1, int indice2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.grosor = 2;
		this.indiceN1 = indice1;
		this.indiceN2 = indice2;
	}

	public void pintar(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		BasicStroke stroke = new BasicStroke(grosor);

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(stroke);
		g2d.setColor(Color.black);
		g2d.drawLine(x1, y1, x2, y2);
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public int getX1() {
		return x1;
	}

	public int getY1() {
		return y1;
	}

	public int getX2() {
		return x2;
	}

	public int getY2() {
		return y2;
	}

	public int getIndiceN1() {
		return indiceN1;
	}

	public int getIndiceN2() {
		return indiceN2;
	}
}
