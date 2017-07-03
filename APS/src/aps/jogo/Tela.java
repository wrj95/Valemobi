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
	// Instanciando as variáveis
	private Image fundo;
	private Nave nave;

	// O timer é para atualização frequente da tela, devido a movimentação da nave
	private Timer atualiza;

	// INIMIGOS

	// Lista de Inimigos
	private List<Inimigos> enemy;

	// Variáveis de contagem para o Recorde e para Asteroids destruidos
	int score = 0, asteroidDest = 0;
	int addTempo = 0;

	public Tela() {

		setFocusable(true);
		setDoubleBuffered(true);

		// Adicionar os comando de teclado da SubClasse Adaptador
		addKeyListener(new Adaptador());

		// Instanciando a imagem que ficará de fundo.
		ImageIcon refFundo = new ImageIcon(getClass().getResource("plano de fundo.jpg"));
		fundo = refFundo.getImage();

		// Instanciando a Nave
		nave = new Nave();

		// Inicializar os Asteroids, mas primeiro tem que "pintar" no Graphics
		// Colocar num Array para aparecer varios
			enemy = new ArrayList<Inimigos>();

		/*
		 * o Timer atualiza a aplicação, na primeira coordenada coloca-se em
		 * quantos, MILISSEGUNDOS ele chama uma ação. na segunda coordenada,
		 * será a ação a ser chamada. Não aceita uma ação que é a Classe em si,
		 * por isso implementei a ActionListener
		 */

		atualiza = new Timer(5, this);
		// Iniciar a atualização
		atualiza.start();

	}

	// Action implementada para atualização da aplicação
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Condição para iniciar o jogo
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

				// isvisivel se o missel estiver já engatilhado
				if (m.isVisivel()) {
					m.movimento();
				} else {
					// Quando passar da tela, e não será mais visível, portanto
					// remover o missel
					missel.remove(i);
				}
			}

			// Asteroids
			// Fazer o Asteroids se movimentar
			for (int i = 0; i < enemy.size(); i++) {
				Inimigos in = (Inimigos) enemy.get(i);

				// isVisivel se o Asteroid estiver já engatilhado para se
				// movimentar na tela até o fim da tela

				if (in.isVisivel()) {
					in.movimento();
				} else {
					// Quando passar da tela, e não será mais visível, portanto
					// remover o Asteroid
					enemy.remove(i);
				}
			}

			// Irá chamar o método de movimentação da nave, que foi escrito na
			// classe Nave
			nave.movimento();

			// Atualização de colisões
			checarColisao();

			// E logo após irá executar o método repaint(), que é para atualizar
			// a pintura da tela
			repaint();
		}
		// Fim de jogo
		else {
			// Parar o Timer
			atualiza.stop();

			// Este método recupera a JFrame JanelaBase para consegui fechar a
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
			// Instanciando so mísseis no Loop
			Misseis m = (Misseis) missel.get(i);
			//Pintar Mísseis
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
		

		// Método dispose é para o tratamento de atualizações, para usar a
		// imagem atualizada e descarta a última
		pintar.dispose();
	}


	// COLISÕES
	public void checarColisao() {
		// Onde pega o retângulo de cada objeto
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
			// irá pegar o missel da lista e a forma do retângulo dele
			Misseis tempMissel = missel.get(i);
			formMissel = tempMissel.getBounds();

			for (int j = 0; j < enemy.size(); j++) {
				/*
				 * Após pegar a forma do Míssel entra no Looping do Asteroid com
				 * a forma do retângulo do asteroid
				 */
				Inimigos tempEnemy2 = enemy.get(j);
				formAsteroid = tempEnemy2.getBounds();

				/*
				 * Se o retangulo do missel intersecta o retangulo do asteroids
				 * há uma colisão Quando ocorre a colisão não fica visivel na
				 * tela, logo o setVisivel fica FALSE
				 */
				if (formMissel.intersects(formAsteroid)) {
					// Acrescentando Pontuação
					score += 100;
					asteroidDest++;

					// inimigos e míssel desaparece
					tempEnemy2.setVisivel(false);
					tempMissel.setVisivel(false);
				}
			}
		}

	}

	// De pegar as ações do teclado na Classe Nave para ser efetuado na Tela
	private class Adaptador extends KeyAdapter {

		public void pause() {
			// JOptionPane para o PAUSE, se YES, retornar ao Jogo, se NO,
			// termina o jogo
			Object[] options = { "Sim", "Não" };
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
