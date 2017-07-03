package aps.jogo;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class Caracteristica {

	protected Image img;
	protected int x, y; //Localização
	protected int larg;
	protected int alt;
	protected boolean isVisivel;
	
	// Para o missel não passar da tela
	protected static final int LARGURA_TELA = 800;
	
	// Velocidade do Míssel
	protected static final int VELOCIDADE = 2;
	
	//Movimento de cada Objeto
	public abstract void movimento();

	public boolean isVisivel() {
		return isVisivel;
	}
	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
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
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, larg, alt);
	}
}
