package view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Sobre extends JDialog {
	public Sobre() {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/logo.png")));
		getContentPane().setBackground(new Color(192, 192, 192));
		getContentPane().setLayout(null);
		
		JLabel titulo = new JLabel("Sobre o software");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setForeground(new Color(0, 0, 0));
		titulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		titulo.setBackground(new Color(255, 255, 255));
		titulo.setBounds(0, 61, 543, 20);
		getContentPane().add(titulo);
		
		JLabel descricao1 = new JLabel("possibilitar o gerenciamento de reserva de salas em um espaço colaborativo.\r\n");
		descricao1.setHorizontalAlignment(SwingConstants.CENTER);
		descricao1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		descricao1.setBounds(0, 158, 518, 34);
		getContentPane().add(descricao1);
		
		JLabel descricao2 = new JLabel("O software CoWorking trata-se de um protótipo cujo objetivo é");
		descricao2.setHorizontalAlignment(SwingConstants.CENTER);
		descricao2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		descricao2.setBounds(0, 119, 518, 34);
		getContentPane().add(descricao2);
		
		JLabel versao = new JLabel("Versão 1.0.0\r\n");
		versao.setHorizontalAlignment(SwingConstants.CENTER);
		versao.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		versao.setBounds(0, 256, 518, 27);
		getContentPane().add(versao);
		
		JLabel atualizacao = new JLabel("Última atualização: 31/01/2024");
		atualizacao.setHorizontalAlignment(SwingConstants.CENTER);
		atualizacao.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		atualizacao.setBounds(0, 283, 518, 34);
		getContentPane().add(atualizacao);
		
		JLabel imgMIT = new JLabel("");
		imgMIT.setIcon(new ImageIcon(Sobre.class.getResource("/img/mitLicense.png")));
		imgMIT.setBounds(460, 294, 48, 48);
		getContentPane().add(imgMIT);
	}

	public static void main(String[] args) {
	       EventQueue.invokeLater(new Runnable() {
	    	   public void run() {
	    		   try {
	    			   Sobre dialog = new Sobre();
	    			   dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    			   dialog.setVisible(true);
	    			   
	    		    }
	    		   
	    		   catch (Exception e) {
	    			   e.printStackTrace();
	    		   }
	    	   }
	    	  
	    	   
	       });

	}
}
