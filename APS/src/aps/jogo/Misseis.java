package aps.jogo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Misseis extends Caracteristica {

	public Misseis(int x, int y) {
		this.x = x;
		this.y = y;

		// instanciando a imagem do Míssel
		ImageIcon refMissel = new ImageIcon(getClass().getResource("missel.png"));
		img = refMissel.getImage();

		this.larg = img.getWidth(null); // Método para pegar largura da Imagem
		this.alt = img.getHeight(null); // Método para pegar a altura da Imagem

		// Confirmação de quando for efetuar o disparo, há um booleano
		// confirmando que há um missel para disparo
		isVisivel = true;
	}
	
	@Override
	public void movimento() {
		// TODO Auto-generated method stub
		// Para o míssel andar o x será somado a constante VELOCIDADE_MISSEL
		// para progreção no plano cartesiano
		this.x += VELOCIDADE;
		
		// Condição if se o missel passar da Largura da tela
		if (this.x > LARGURA_TELA) {
			isVisivel = false;
		}
		
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean visivel) {
		isVisivel = visivel;
	}

	public Image getImg() {
		return img;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// Colisão
	/*
	 * Um retângulo que cobre a área de cada elemento da tela, como se fosse um
	 * casulo para cada um Quando cada área se choca ocorre uma colisão, para
	 * isso cada método Rectangle tem que estar em cada classe
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, larg, alt);
	}

}
