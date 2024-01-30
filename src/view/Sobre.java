package view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class Sobre extends JDialog {
	public Sobre() {
		getContentPane().setLayout(null);
		
		JLabel titulo = new JLabel("Sobre o software");
		titulo.setBounds(143, 65, 110, 20);
		getContentPane().add(titulo);
	}

	public static void main(String[] args) {
	

	}

}
