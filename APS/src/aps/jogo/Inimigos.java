package aps.jogo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Inimigos extends Caracteristica{
	static int cont = 0;
	public Inimigos(int x, int y){
		this.x = x;
		this.y = y;
		//instanciando a imagem do M�ssel
		ImageIcon refEnemy;
		if(cont++ % 3 == 0){
			refEnemy = new ImageIcon(getClass().getResource("Asteroid.png"));
		}
		else{
			refEnemy = new ImageIcon(getClass().getResource("Rock_Asteroid.png"));
		}
		img = refEnemy.getImage();
	
		this.larg = img.getWidth(null); //M�todo para pegar largura da Imagem
		this.alt = img.getHeight(null); //M�todo para pegar a altura da Imagem
		
		isVisivel = true;
	}
	
	@Override
	public void movimento() {
		// TODO Auto-generated method stub

		//se o asteroids chega no fim da tela, seu Booleano fica false e � removido
		if(x < 0){
			isVisivel = false;
		}
		else{
			//movimento do Asteroids
			this.x -= VELOCIDADE;
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

	//Colis�o
	
	/*Um ret�ngulo que cobre a �rea de cada elemento da tela, como se fosse um casulo para cada um
	 * Quando cada �rea se choca ocorre uma colis�o, para isso cada m�todo Rectangle tem que estar em cada classe
	 */
	public Rectangle getBounds(){
		return new Rectangle(x, y, larg, alt);
	}

}
