package aps.jogo;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Menu.MenuJogo;

public class GameOver extends JFrame {
	private JButton sim;
	private JButton nao;
	private JPanel janFim;
	private AudioClip audio;

	public GameOver(int score, int asteroidDest) {
		this.setSize(500, 470);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		janFim= new JPanel();
		janFim.setBackground(Color.DARK_GRAY);
		janFim.setLayout(new GridLayout(6, 0));
		
		this.add(janFim);
		apresentaResults(score, asteroidDest);
		apresentaBotoes();

		this.setVisible(true);
	}
	
	public void apresentaResults(int score, int asteroidDest){
		//String para apresentar se bateu o recorde ou não
		String recBatido;
		
		// Label de Game Over
		JLabel gOver = new JLabel("Game Over", JLabel.CENTER);
		gOver.setFont(new Font("Bauhaus 93", Font.BOLD, 50));
		gOver.setForeground(Color.LIGHT_GRAY);
		
		// Se bateu o recorde apresenta a mensagem de Recorde Batido, se não, um Loser
		if(gravar(score) == true){
			recBatido = "Recorde batido ";
			// SOM DO DISPARO
			audio = Applet.newAudioClip(getClass().getResource("win.wav"));
			audio.play();
		}
		else{
			audio = Applet.newAudioClip(getClass().getResource("gameover.mid"));
			audio.play();
			recBatido = "Loser";
		}
		
		JLabel apRecord = new JLabel(recBatido, JLabel.CENTER);
		apRecord.setFont(new Font("Bauhaus 93", Font.BOLD, 50));
		apRecord.setForeground(Color.LIGHT_GRAY);
		
		
		JLabel record = new JLabel("Pontuação: " + score, JLabel.CENTER);
		record.setFont(new Font("Cooper Black", Font.BOLD, 30));
		record.setForeground(Color.LIGHT_GRAY);
		
		// Label de quantos Asteroids foram destruídos
		JLabel contAsteroid = new JLabel("Asteroids destruidos: " + asteroidDest, JLabel.CENTER);
		contAsteroid.setFont(new Font("Cooper Black", Font.BOLD, 30));
		contAsteroid.setForeground(Color.LIGHT_GRAY);
		
		
		JLabel tentNov = new JLabel("Tentar Novamente?", JLabel.CENTER);
		tentNov.setForeground(Color.LIGHT_GRAY);
		
		janFim.add(gOver);
		janFim.add(apRecord);
		janFim.add(record);
		janFim.add(contAsteroid);
		janFim.add(tentNov);
	}
	
	public void apresentaBotoes(){
		JPanel fimBotao = new JPanel();
		fimBotao.setLayout(new GridLayout(1,2));
		
		sim = new JButton("Sim");
		nao = new JButton("Não");
		
		// Ação do Sim
		sim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Sim pressionado, reinicia o jogo
				JanelaBase restart = new JanelaBase();
				restart.setVisible(true);
				audio.stop();
				dispose();
			}
		});

		// Ação do Sim
		nao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Não clickado, volta ao Menu inicial
				MenuJogo mj = new MenuJogo();
				mj.setVisible(true);
				audio.stop();
				dispose();
			}
		});
		
		sim.setForeground(Color.WHITE);
		sim.setBackground(Color.DARK_GRAY);
		
		nao.setForeground(Color.WHITE);
		nao.setBackground(Color.DARK_GRAY);
		
	
		

		fimBotao.add(sim);
		fimBotao.add(nao);
		
		janFim.add(fimBotao);
	}

	public boolean gravar(int n) {
		
		//Primeiro vai ler o arquivo
		String linha = null;
		try {
			FileReader arq = new FileReader("C:\\PONTUACAO.txt");
			BufferedReader lerArq = new BufferedReader(arq);

			linha = lerArq.readLine(); // lê a primeira linha
			// a variável "linha" recebe o valor "null" quando o processo
			// de repetição atingir o final do arquivo texto

			arq.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			linha = "0";
		}
		
		int aux = Integer.parseInt(linha);
		
		//Se o Score for maior que o 
		if (n > aux) {
			String pt = Integer.toString(n);
			FileWriter arq;
			try {
				arq = new FileWriter("C:\\PONTUACAO.txt");
				PrintWriter gravarArq = new PrintWriter(arq);

				gravarArq.printf(pt);

				arq.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Falha ao salvar seu recorde", "Erro", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
			return true;
		}
		else{
			return false;
		}
	}

}
