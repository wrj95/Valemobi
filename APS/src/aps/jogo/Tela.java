package aps.jogo;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import Menu.Recorde;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import java.util.ArrayList;
import java.util.List;

public class Tela extends JPanel implements ActionListener {
	// Instanciando as vari�veis
	private Image fundo;
	private Nave nave;

	// O timer � para atualiza��o frequente da tela, devido a movimenta��o da nave
	private Timer atualiza;

	// INIMIGOS

	// Lista de Inimigos
	private List<Inimigos> enemy;

	// Vari�veis de contagem para o Recorde e para Asteroids destruidos
	int score = 0, asteroidDest = 0;
	int addTempo = 0;

	public Tela() {

		setFocusable(true);
		setDoubleBuffered(true);

		// Adicionar os comando de teclado da SubClasse Adaptador
		addKeyListener(new Adaptador());

		// Instanciando a imagem que ficar� de fundo.
		ImageIcon refFundo = new ImageIcon(getClass().getResource("plano de fundo.jpg"));
		fundo = refFundo.getImage();

		// Instanciando a Nave
		nave = new Nave();

		// Inicializar os Asteroids, mas primeiro tem que "pintar" no Graphics
		// Colocar num Array para aparecer varios
			enemy = new ArrayList<Inimigos>();

		/*
		 * o Timer atualiza a aplica��o, na primeira coordenada coloca-se em
		 * quantos, MILISSEGUNDOS ele chama uma a��o. na segunda coordenada,
		 * ser� a a��o a ser chamada. N�o aceita uma a��o que � a Classe em si,
		 * por isso implementei a ActionListener
		 */

		atualiza = new Timer(5, this);
		// Iniciar a atualiza��o
		atualiza.start();

	}

	// Action implementada para atualiza��o da aplica��o
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Condi��o para iniciar o jogo
		addTempo++;

		if (nave.isVisivel() == true) {

			if (addTempo == 50) {
				int m = (int) (Math.random() * 400);
				this.enemy.add(new Inimigos(800, m));
				addTempo = 0;
			}
			score++;

			List<Misseis> missel = nave.getMissel();
			for (int i = 0; i < missel.size(); i++) {

				Misseis m = (Misseis) missel.get(i);

				// isvisivel se o missel estiver j� engatilhado
				if (m.isVisivel()) {
					m.movimento();
				} else {
					// Quando passar da tela, e n�o ser� mais vis�vel, portanto
					// remover o missel
					missel.remove(i);
				}
			}

			// Asteroids
			// Fazer o Asteroids se movimentar
			for (int i = 0; i < enemy.size(); i++) {
				Inimigos in = (Inimigos) enemy.get(i);

				// isVisivel se o Asteroid estiver j� engatilhado para se
				// movimentar na tela at� o fim da tela

				if (in.isVisivel()) {
					in.movimento();
				} else {
					// Quando passar da tela, e n�o ser� mais vis�vel, portanto
					// remover o Asteroid
					enemy.remove(i);
				}
			}

			// Ir� chamar o m�todo de movimenta��o da nave, que foi escrito na
			// classe Nave
			nave.movimento();

			// Atualiza��o de colis�es
			checarColisao();

			// E logo ap�s ir� executar o m�todo repaint(), que � para atualizar
			// a pintura da tela
			repaint();
		}
		// Fim de jogo
		else {
			// Parar o Timer
			atualiza.stop();

			// Este m�todo recupera a JFrame JanelaBase para consegui fechar a
			// Janela
			JanelaBase n = (JanelaBase) SwingUtilities.getWindowAncestor(this);
			// Remove o Painel
			n.remove(this);
			// Invalida o JPanel
			n.invalidate();
			// Dispose fecha
			n.dispose();
			
		GameOver go = new GameOver(score, asteroidDest);

		}
	}

	public void paint(Graphics pintar) {

		// A classe Graphics para pintar na tela
		Graphics2D grafico = (Graphics2D) pintar;

		// Score, converter para String para ser pintado na tela
		String S = Integer.toString(score);

		// IMAGEM DE FUNDO
		grafico.drawImage(fundo, 0, 0, null);

		// IMAGEM NAVE
		grafico.drawImage(nave.getImg(), nave.getX(), nave.getY(), this);

		// PINTAR OS MISSEIS NA TELA
		List<Misseis> missel = nave.getMissel();
		for (int i = 0; i < missel.size(); i++) {
			// Instanciando so m�sseis no Loop
			Misseis m = (Misseis) missel.get(i);
			//Pintar M�sseis
			grafico.drawImage(m.getImg(), m.getX(), m.getY(), this);
		}

		// PINTAR OS INIMIGOS NA TELA
		for (int i = 0; i < enemy.size(); i++) {
			// Intanciando so inimigos dentro do loop
			Inimigos inpaint = (Inimigos) enemy.get(i);
			//Pintar Asteroids
			grafico.drawImage(inpaint.getImg(), inpaint.getX(), inpaint.getY(), this);
		}

			Font ftgmOver = new Font("Bauhaus 93", Font.BOLD, 50);
			grafico.setFont(ftgmOver);
			grafico.setColor(Color.WHITE);
			grafico.drawString("Score: " + S, 260, 50);
		

		// M�todo dispose � para o tratamento de atualiza��es, para usar a
		// imagem atualizada e descarta a �ltima
		pintar.dispose();
	}


	// COLIS�ES
	public void checarColisao() {
		// Onde pega o ret�ngulo de cada objeto
		Rectangle formNave = nave.getBounds();
		Rectangle formMissel;
		Rectangle formAsteroid;

		// Teste nave com o Inimigo
		for (int i = 0; i < enemy.size(); i++) {
			// Recebe todos os elementos da Lista de Inimigos
			Inimigos tempEnemy = enemy.get(i);
			formAsteroid = tempEnemy.getBounds();

			if (formNave.intersects(formAsteroid)) {
				nave.setVisivel(false);
				tempEnemy.setVisivel(false);
			}
		}

		// Teste missel com Asteroid

		List<Misseis> missel = nave.getMissel();
		for (int i = 0; i < missel.size(); i++) {
			// ir� pegar o missel da lista e a forma do ret�ngulo dele
			Misseis tempMissel = missel.get(i);
			formMissel = tempMissel.getBounds();

			for (int j = 0; j < enemy.size(); j++) {
				/*
				 * Ap�s pegar a forma do M�ssel entra no Looping do Asteroid com
				 * a forma do ret�ngulo do asteroid
				 */
				Inimigos tempEnemy2 = enemy.get(j);
				formAsteroid = tempEnemy2.getBounds();

				/*
				 * Se o retangulo do missel intersecta o retangulo do asteroids
				 * h� uma colis�o Quando ocorre a colis�o n�o fica visivel na
				 * tela, logo o setVisivel fica FALSE
				 */
				if (formMissel.intersects(formAsteroid)) {
					// Acrescentando Pontua��o
					score += 100;
					asteroidDest++;

					// inimigos e m�ssel desaparece
					tempEnemy2.setVisivel(false);
					tempMissel.setVisivel(false);
				}
			}
		}

	}

	// De pegar as a��es do teclado na Classe Nave para ser efetuado na Tela
	private class Adaptador extends KeyAdapter {

		public void pause() {
			// JOptionPane para o PAUSE, se YES, retornar ao Jogo, se NO,
			// termina o jogo
			Object[] options = { "Sim", "N�o" };
			int opcao = JOptionPane.showOptionDialog(null, "PAUSE \nRetornar ao Jogo ?", "Pause",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

			if (opcao == 0)
				atualiza.restart();
			if (opcao == 1) {
				atualiza.restart();
				nave.setVisivel(false);
			}
			if (opcao == -1)
				atualiza.restart();
		}

		@Override
		public void keyPressed(KeyEvent arg0) {

			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				// Pausa o jogo
				atualiza.stop();
				pause();
			}
			// Instancia da Nave no Construtor
			nave.KeyPressed(arg0);
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			nave.keyReleased(arg0);

		}

	}
}
