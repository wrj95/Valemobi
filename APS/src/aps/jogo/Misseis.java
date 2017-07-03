package aps.jogo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Misseis extends Caracteristica {

	public Misseis(int x, int y) {
		this.x = x;
		this.y = y;

		// instanciando a imagem do M�ssel
		ImageIcon refMissel = new ImageIcon(getClass().getResource("missel.png"));
		img = refMissel.getImage();

		this.larg = img.getWidth(null); // M�todo para pegar largura da Imagem
		this.alt = img.getHeight(null); // M�todo para pegar a altura da Imagem

		// Confirma��o de quando for efetuar o disparo, h� um booleano
		// confirmando que h� um missel para disparo
		isVisivel = true;
	}
	
	@Override
	public void movimento() {
		// TODO Auto-generated method stub
		// Para o m�ssel andar o x ser� somado a constante VELOCIDADE_MISSEL
		// para progre��o no plano cartesiano
		this.x += VELOCIDADE;
		
		// Condi��o if se o missel passar da Largura da tela
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

	// Colis�o
	/*
	 * Um ret�ngulo que cobre a �rea de cada elemento da tela, como se fosse um
	 * casulo para cada um Quando cada �rea se choca ocorre uma colis�o, para
	 * isso cada m�todo Rectangle tem que estar em cada classe
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, larg, alt);
	}

}
