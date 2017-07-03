package Menu;

import aps.jogo.JanelaBase;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MenuJogo extends JFrame {

	// Criando os botões da tela menu
	private Image fundo;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JPanel panel1;
	private JLabel lbl1;
	
	// Construtor da Janela Menu
	public MenuJogo() {
		// Fechar pelo X
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setFocusable(true);
		// define o tamanho da Janela
		this.setSize(441, 500);
		// Nome da janela
		this.setTitle("   Broken Asteroids   ");
		// Define a posição onde a janela Inicia
		this.setLocationRelativeTo(null);
		setResizable(false);
		// Instanciando a imagem que ficará de fundo
		ImageIcon refFundo = new ImageIcon(getClass().getResource(""));
		fundo = refFundo.getImage();

		// criando label

		lbl1 = new JLabel(" Imagem ");
		lbl1.setBounds(0, 0,0,0); 
		ImageIcon logo = new ImageIcon(getClass().getResource("logo.jpg"));
		lbl1.setIcon(logo);
		


		// criando os botões
		btn1 = new JButton("Jogar");
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JanelaBase j = new JanelaBase();
				j.setVisible(true);
				dispose();
			}

		});

		//B
		btn2 = new JButton("Como Jogar");
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				comoJogar();

			}
		});
		btn3 = new JButton("Recorde");
		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				Recorde score = new Recorde();
			}
		});
		btn4 = new JButton("Sair");
		btn4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

		GridLayout gridJanela = new GridLayout(2, 2);
		this.setLayout(gridJanela);

		
		

		// escolher Layout
		GridLayout gridBotoes = new GridLayout(2, 4);
		Panel painelBotoes = new Panel();
		painelBotoes.setLayout(gridBotoes);

		// adicionar os botões
		painelBotoes.add(btn1);
		painelBotoes.add(btn2);
		painelBotoes.add(btn3);
		painelBotoes.add(btn4);

		// Escolhendo tipo de fonte do botão e tamanho
		btn1.setFont(new Font("Monospaced", Font.ITALIC, 15));
		btn2.setFont(new Font("Monospaced", Font.ITALIC, 15));
		btn3.setFont(new Font("Monospaced", Font.ITALIC, 15));
		btn4.setFont(new Font("Monospaced", Font.ITALIC, 15));

		// definindo cor de fundo no botão, e cor da fonte
		btn1.setBackground(Color.DARK_GRAY);
		btn1.setForeground(Color.WHITE);

		btn2.setBackground(Color.DARK_GRAY);
		btn2.setForeground(Color.WHITE);

		btn3.setBackground(Color.DARK_GRAY);
		btn3.setForeground(Color.WHITE);

		btn4.setBackground(Color.DARK_GRAY);
		btn4.setForeground(Color.WHITE);

		this.add(lbl1);
		this.add(painelBotoes);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		MenuJogo mj = new MenuJogo();
	}

	public void comoJogar(){
		JFrame cj = new JFrame();
		cj.setBackground(Color.DARK_GRAY);
		cj.setSize(435,460);
		cj.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		cj.setLocationRelativeTo(null);
		cj.setResizable(false);
		cj.setTitle("Como Jogar");
		
		ImageIcon imgLabel = new ImageIcon(getClass().getResource("Teclado.png"));
		JLabel lbImg = new JLabel();
		lbImg.setBounds(0, 0,0,0); 
		lbImg.setIcon(imgLabel);
		
		JPanel instrucao = new JPanel();
		instrucao.setBackground(Color.DARK_GRAY);
		instrucao.setLayout(new GridLayout(5,0));
		
		
		JLabel lbInst1 = new JLabel("SETA PARA CIMA: Move a Nave para Cima");
		lbInst1.setForeground(Color.YELLOW);
		JLabel lbInst2 = new JLabel("SETA PARA BAIXO: Move a Nave para Baixo");
		lbInst2.setForeground(Color.YELLOW);
		
		JLabel lbInst3 = new JLabel("ESPAÇO: Atira");
		lbInst3.setForeground(Color.RED);
		
		JLabel lbInst4 = new JLabel("ENTER: Pausa o jogo");
		lbInst4.setForeground(Color.CYAN);
		
		JButton volta = new JButton("Voltar ao Menu");
		volta.setFont(new Font("Monospaced", Font.ITALIC, 15));
		volta.setBackground(Color.DARK_GRAY);
		volta.setForeground(Color.WHITE);
		volta.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			cj.dispose();	
			}
			
		});
		
		
		instrucao.add(lbInst1);
		instrucao.add(lbInst2);
		instrucao.add(lbInst3);
		instrucao.add(lbInst4);
		instrucao.add(volta);
		
		cj.setLayout(new GridLayout(2,0));
		
		cj.add(lbImg);
		cj.add(instrucao);
		
		
		cj.setVisible(true);
	
		
		
	}
}
