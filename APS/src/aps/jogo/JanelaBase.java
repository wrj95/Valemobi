package aps.jogo;

import javax.swing.JFrame;

public class JanelaBase extends JFrame {

	public JanelaBase() {
		// Definições da Janela
		setTitle("Broken Asteroids");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 500);
		setLocationRelativeTo(null);

		// Para não redimensionar a tela
		setResizable(false);
		add(new Tela());
		setVisible(true);
	}
}