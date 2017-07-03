package aps.jogo;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Nave extends Caracteristica {

	private int dx, dy; // a vari�vel dx e dy s�o complementares a x e y

	// Instanciar uma List para um n�mero infinito de balas
	private List<Misseis> missel;

	public Nave() {
		// Instanciando a imagem da nave
		ImageIcon imagemNave = new ImageIcon(getClass().getResource("nave 2.png"));
		img = imagemNave.getImage();

		alt = img.getHeight(null);
		larg = img.getWidth(null);

		isVisivel = true;
		
		// Lista de Misseis
		missel = new ArrayList<Misseis>();

		// Coordenadas, x e y, da nave
		this.x = -3;
		this.y = 100;
	}

	public void movimento() {
		y += dy;

		// PARA N�O SAIR DA TELA
		/*
		 * Se y for menor que 5, ir� valer 5, logo se passar, ele volta a ser 5
		 * Mesma coisa se y for maior que 405, vai voltar a ser 405
		 */
		if (this.y < 5) {
			y = 5;
		}

		if (this.y > 405) {
			y = 405;
		}
	}

	// Getter e Setter para atualiza��o da localiza��o da Nave
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImg() {
		return img;
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	// Parte de M�sseis

	// Para pegar a lista de m�sseis quando precisar
	public List<Misseis> getMissel() {
		return missel;
	}

	public void atira() {
		// O m�ssel tem que aparecer depois da nave e no centro dela
		this.missel.add(new Misseis(x + larg, y + alt / 3));
	}

	// Parte das Colis�es

	/*
	 * Um ret�ngulo que cobre a �rea de cada elemento da tela, como se fosse um
	 * casulo para cada um Quando cada �rea se choca ocorre uma colis�o, para
	 * isso cada m�todo Rectangle tem que estar em cada classe
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, larg, alt);
	}
	
	//PARTE DE A��ES PELO TECLADO

	public void KeyPressed(KeyEvent tec) {

		if (tec.getKeyCode() == KeyEvent.VK_UP) {
			// No computador o f(x) come�a de cima do monitor, logo se eu quero
			// subir a nave, subtraia o valor de dy;
			dy -= 5;
		}
		if (tec.getKeyCode() == KeyEvent.VK_DOWN) {
			// No computador o f(x) come�a de cima do monitor, logo se eu quero
			// subir a nave, subtraia o valor de dy;
			dy += 5;
		}

	}

	public void keyReleased(KeyEvent stop) {
		// TODO Auto-generated method stub
		
		if (stop.getKeyCode() == KeyEvent.VK_UP) {
			// Para parar a nave a partir do momento que eu soltar a seta para
			// cima
			dy = 0;
		}
		if (stop.getKeyCode() == KeyEvent.VK_DOWN) {
			// Para parar a nave a partir do momento que eu soltar a seta para
			// cima
			dy = 0;
		}
		if (stop.getKeyCode() == KeyEvent.VK_SPACE) {
			// Chama o m�todo de disparo de m�sseis
			atira();
			
			// SOM DO DISPARO
			AudioClip audio = Applet.newAudioClip(getClass().getResource("laser.wav"));
			audio.play();
		}

	}


}
