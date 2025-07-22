package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class VerticeGrafico {
	private int indice;
	private int x, y;
	public static final int diametro = 60;
	private Color color;

	public VerticeGrafico(int x, int y, int indice) {
		this.x = x;
		this.y = y;
		this.indice = indice;
		this.color = Color.WHITE;
	}

	public void pintar(Graphics g) {
		Font font = new Font("MS Reference Sans Serif", Font.BOLD, 15);
		String indiceString = Integer.toString(indice);
		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setColor(color);
		g2d.fillOval(this.x - (diametro / 2), this.y - (diametro / 2), diametro, diametro);

		g2d.setColor(Color.black);
		g2d.drawOval(this.x - (diametro / 2), this.y - (diametro / 2), diametro, diametro);

		g2d.setColor(Color.black);
		g2d.setFont(font);

		FontMetrics metrics = g2d.getFontMetrics(font);
		int posX = this.x - metrics.stringWidth(indiceString) / 2;
		int posY = this.y + metrics.getHeight() / 4;
		g2d.drawString(indiceString, posX, posY);
	}

	protected int getX() {
		return x;
	}

	protected void setX(int x) {
		this.x = x;
	}

	protected int getY() {
		return y;
	}

	protected void setY(int y) {
		this.y = y;
	}

	protected int getIndice() {
		return indice;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
