package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;


public class Recorde extends JFrame {

	private JLabel lblSeuRecorde;
	private JButton btnVolta;

	String linha = "0";
	// construtor janela recorde
	String pontos;

	public Recorde() {
		this.setFocusable(true);
		// define o tamanho da Janela
		this.setSize(240, 275);

		this.setTitle("Recorde");
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		setResizable(false);
		
		ler();

		// label

		lblSeuRecorde = new JLabel("Seu recorde");
		lblSeuRecorde.setForeground(Color.WHITE);
		lblSeuRecorde.setFont(new Font("Monospaced", Font.BOLD, 18));
		lblSeuRecorde.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeuRecorde.setVerticalAlignment(SwingConstants.TOP);
		
		JLabel record = new JLabel("Score: " + linha, JLabel.CENTER);
		record.setForeground(Color.WHITE);
		record.setFont(new Font("Monospaced", Font.BOLD, 18));

		// botão
		btnVolta = new JButton("Voltar");
		btnVolta.setForeground(Color.WHITE);
		btnVolta.setBackground(Color.DARK_GRAY);
		btnVolta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		});

		GridLayout grid = new GridLayout(3, 1);

		this.setLayout(grid);
		this.add(lblSeuRecorde);
		this.add(record);
		this.add(btnVolta);

		this.setVisible(true);
	}
	
	public void ler(){
		 try {
		      FileReader arq = new FileReader("C:\\PONTUACAO.txt");
		      BufferedReader lerArq = new BufferedReader(arq);
		 
		    linha = lerArq.readLine(); // lê a primeira linha
		// a variável "linha" recebe o valor "null" quando o processo
		// de repetição atingir o final do arquivo texto
		 
		      arq.close();
	}catch (IOException e) {
		// TODO Auto-generated catch block
		linha = "0";
	}
	}
}
